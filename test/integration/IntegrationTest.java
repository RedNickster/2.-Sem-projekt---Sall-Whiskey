package integration;

import controller.Controller;
import model.*;
import storage.IStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import model.enums.GrainVariety;
import model.enums.CaskLiquids;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller(new storage.Storage());
    }

    @Test
    void testIntegration_UC1_UC2() {
        // 1. Create Distillation
        Distillation distillation = controller.createDistillationAndAddToDistillate(1, LocalDate.now(),
                "Employee", "Comment", controller.createDistillate(GrainVariety.EVERGREEN,
                        "malt batch"));
        
        // 2. Create Cask (using null for previousLiquids as allowed by model)
        Cask cask = controller.createCask(1, 100, null, "Denmark",
                "Supplier");
        
        // 3. Register end of distillation
        controller.endDistillation(distillation, LocalDate.now().plusDays(1), 50.0,
                45.0, "Finished");
        
        // 4. Verify in storage
        IStorage controllerStorage = controller.getStorage();
        assertTrue(controllerStorage.getDistillations().contains(distillation), "Distillation should be in storage");
        assertTrue(controllerStorage.getCasks().contains(cask), "Cask should be in storage");
    }

    @Test
    void TC9_UseCase3_AddDistillateToCaskAndStoreInWarehouseThenTap() {
        // Arrange
        Warehouse warehouse = new Warehouse("Warehouse 1, Main St", 100.0, 2);
        Cask cask1 = new Cask(1, 500, new ArrayList<>(Arrays.asList(CaskLiquids.BOURBON)),
                "USA", "Supplier A");
        Distillate distillate1 = new Distillate(1, GrainVariety.EVERGREEN, "Malt Batch");
        
        double litersToAdd = 300;
        double litersToTap = 50;
        
        // Act + Assert
        assertDoesNotThrow(() -> cask1.addDistillateToCask(distillate1, litersToAdd));
        assertEquals(litersToAdd, cask1.containsLiters());
        assertDoesNotThrow(() -> warehouse.addCask(cask1));
        assertDoesNotThrow(() -> cask1.tapDistillate(litersToTap));
        assertEquals(litersToAdd - litersToTap, cask1.containsLiters());
        assertDoesNotThrow(() -> warehouse.removeCask(cask1));
    }

    @Test
    void testIntegration_CreateBottleBatchAndDilute() {
        // Arrange
        // 1. Create a Cask (required for Liquid constructor)
        Cask cask = new Cask(99, 1000, new ArrayList<>(), "Test Cask", "Test Supplier");

        // 2. Create a Distillate (required for Liquid constructor)
        Distillate distillate = new Distillate(99, GrainVariety.EVERGREEN, "Test MaltBatch");

        // 3. Create Liquid objects with specific alcohol percentages and amounts
        // Liquid 1: 100L at 60% alcohol
        Liquid liquid1 = new Liquid(LocalDate.now(), 100, cask, distillate);
        // LiquidCheck is needed to set the alcohol percentage in Liquid
        liquid1.checkLiquid(LocalDate.now(), 60.0, "Initial check for liquid1");

        // Liquid 2: 200L at 75% alcohol
        Liquid liquid2 = new Liquid(LocalDate.now(), 200, cask, distillate);
        liquid2.checkLiquid(LocalDate.now(), 75.0, "Initial check for liquid2");

        // 4. Create BottleBatch
        BottleBatch bottleBatch = new BottleBatch("Batch for Dilution", "Integration Test Batch");

        // 5. Add Liquids to BottleBatch
        bottleBatch.addLiquid(new BottleBatchLiquid(liquid1.getAmountOfDistillateInCask(), liquid1, LocalDate.now()));
        bottleBatch.addLiquid(new BottleBatchLiquid(liquid2.getAmountOfDistillateInCask(), liquid2, LocalDate.now()));

        /*
         Total alcohol volume = (100 * 60) + (200 * 75) = 6000 + 15000 = 21000
         Total liquid volume = 100 + 200 = 300
         Initial average percentage = 21000 / 300 = 70.0%
        */
        
        double targetAlcoholPercentage = 50.0; // Target 50%


        // Act
        double waterNeeded = bottleBatch.dilluteLiquid(targetAlcoholPercentage);

        // Assert
        /*
         Calculate expected water needed:
         Current total volume (L) = 300
         Current alcohol percentage = 70.0%
         Target alcohol percentage = 50.0%
         Target total volume = (Current total volume * Current percentage) / Target percentage
         Target total volume = (300 * 70.0) / 50.0 = 21000 / 50.0 = 420 L
         Water needed = Target total volume - Current total volume = 420 - 300 = 120 L
         
         Just in case you wonder where the magic number comes from ;)
         */
        assertEquals(120.0, waterNeeded);
    }
}
