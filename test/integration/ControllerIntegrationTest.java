package integration;

import controller.Controller;
import model.*;
import model.enums.CaskLiquids;
import model.enums.GrainVariety;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerIntegrationTest {

    private IStorage storage;
    private Controller controller;
    private Path filePath;

    @BeforeEach
    void setUp() {
        storage = new Storage(); // Using the actual storage for integration test
        controller = new Controller(storage);
        filePath = Paths.get("src/storage/BottleBatchInformation.txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(filePath); // Clean up the file after each test
    }
    

    @Test
    void showWhiskyHistory_generatesCorrectFileContent() throws IOException {
        // Arrange
        // Create Distillate
        Distillate distillate = controller.createDistillate(GrainVariety.EVERGREEN, "MaltBatchA");

        // Create Distillation and add to Distillate
        LocalDate distillationStartDate = LocalDate.of(2023, 1, 1);
        Distillation distillation = controller.createDistillationAndAddToDistillate(1, distillationStartDate, "Emp1", "Dist1", distillate);
        controller.endDistillation(distillation, LocalDate.of(2023, 1, 10), 100.0, 60.0, "Ended");

        // Create Cask
        ArrayList<CaskLiquids> previousLiquids = new ArrayList<>();
        previousLiquids.add(CaskLiquids.BOURBON);
        Cask cask = controller.createCask(1, 200, previousLiquids, "Scotland", "SupplierA");

        // Simulate 3.5 years aging
        LocalDate liquidFillingDate = LocalDate.of(2020, 5, 18); // Liquid put into cask
        LocalDate bottleBatchLiquidCreationDate = liquidFillingDate.plusYears(3).plusMonths(6); // Liquid taken from cask for batch

        // Create Liquid directly with a specific filling date and add to cask
        Liquid liquidInCask = new Liquid(liquidFillingDate, 50.0, cask, distillate);
        // Directly add to cask's liquids list, bypassing controller.pourDistillateIntoCask
        // as we need to control the fillingDate of the Liquid.
        cask.getLiquids().add(liquidInCask);

        // Create BottleBatch
        BottleBatch bottleBatch = controller.createBottleBatch("TestBatch", "A test batch");

        // Create BottleBatchLiquid directly with a specific creation date and add to bottleBatch
        BottleBatchLiquid bbl = new BottleBatchLiquid(20.0, liquidInCask, bottleBatchLiquidCreationDate);
        bottleBatch.addLiquid(bbl);

        String expectedFormattedTimeOnCask = "3 years and 6 months";
        String expectedFormattedGrainVariety = GrainVariety.EVERGREEN.toString(); // "EVERGREEN"
        String expectedFormattedCaskLiquids = CaskLiquids.BOURBON.toString(); // "BOURBON"
        String expectedFormattedCask = "Cask #1 (Supplier: SupplierA, Origin: Scotland)";

        String expectedContent = "TestBatch lavet af byg: " + expectedFormattedGrainVariety +
                ", Destilleret hos Sall Whisky. Destillat(erne) har ligget på fad i " + expectedFormattedTimeOnCask +
                " på fadene " + expectedFormattedCask + " som tidligere har indeholdt " + expectedFormattedCaskLiquids + "\n";


        // Act
        controller.showWhiskyHistory(bottleBatch);

        // Assert
        assertTrue(Files.exists(filePath));
        String actualContent = Files.readString(filePath);
        assertEquals(expectedContent, actualContent);
    }

}
