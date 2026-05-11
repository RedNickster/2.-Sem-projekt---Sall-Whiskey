package integration;

import controller.Controller;
import model.Cask;
import model.Distillation;
import storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Warehouse;
import model.Distillate;
import model.GrainVariety;
import model.CaskLiquids;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void testIntegration_UC1_UC2() {
        // 1. Create Distillation
        Distillation distillation = controller.createDistillation(1, LocalDate.now(), "Employee", "Comment");
        
        // 2. Create Cask (using null for previousLiquids as allowed by model)
        Cask cask = controller.createCask(1, 100, null, "Denmark", "Supplier");
        
        // 3. Register end of distillation
        controller.endDistillation(distillation, LocalDate.now().plusDays(1), 50.0, 45.0, "Finished");
        
        // 4. Verify in storage
        Storage controllerStorage = controller.getStorage();
        assertTrue(controllerStorage.getDistillations().contains(distillation), "Distillation should be in storage");
        assertTrue(controllerStorage.getCasks().contains(cask), "Cask should be in storage");
    }

    @Test
    void TC9_UseCase3_AddDistillateToCaskAndStoreInWarehouseThenTap() {
        // Arrange
        Warehouse warehouse = new Warehouse("Warehouse 1, Main St", 100.0, 2);
        Cask cask1 = new Cask(1, 500, new ArrayList<>(Arrays.asList(CaskLiquids.BOURBON)), "USA", "Supplier A");
        Distillate distillate1 = new Distillate(1, GrainVariety.EVERGREEN, "Malt Batch");
        
        Integer litersToAdd = 300;
        Integer litersToTap = 50;
        
        // Act + Assert
        assertDoesNotThrow(() -> cask1.addDistillate(distillate1, litersToAdd));
        assertEquals(litersToAdd, cask1.getTotalCurrentLiters());
        assertDoesNotThrow(() -> warehouse.addCask(cask1));
        assertDoesNotThrow(() -> cask1.tapDistillate(litersToTap));
        assertEquals(litersToAdd - litersToTap, cask1.getTotalCurrentLiters());
        assertDoesNotThrow(() -> warehouse.removeCask(cask1));
    }
}
