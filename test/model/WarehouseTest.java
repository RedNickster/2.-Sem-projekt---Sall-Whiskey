package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private Warehouse warehouse;
    private Cask cask1;
    private Cask cask2;
    private Cask cask3;
    private Distillate distillate1;

    @BeforeEach
    void setUp() {
        // Basic data for Warehouse: capacity 2
        String address = "Warehouse 1, Main St";
        double m2 = 100.0;
        int capacity = 2; // Small capacity for easy testing
        warehouse = new Warehouse(address, m2, capacity);

        // Basic data for Casks
        List<CaskLiquids> previousLiquids = new ArrayList<>(Arrays.asList(CaskLiquids.BURBON));
        cask1 = new Cask(1, 500, previousLiquids, "USA", "Supplier A");
        cask2 = new Cask(2, 600, previousLiquids, "Scotland", "Supplier B");
        cask3 = new Cask(3, 700, previousLiquids, "Ireland", "Supplier C");

        // Basic data for Distillates (initialized here)
        distillate1 = new Distillate(1, GrainVariety.EVERGREEN);
    }

    /**
     * Test for Warehouse.addCask method.
     * Checks if a cask can be successfully added to an empty warehouse without throwing an exception.
     * This test implicitly verifies the addition by ensuring no error occurs for valid input.
     * Valid data:
     * - cask1 (not null), warehouse with capacity.
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Warehouse with capacity 2.
     * - cask1 object.
     */
    @Test
    void TC1_addCask_happyPath_emptyWarehouse() {
        // Arrange
        // warehouse is empty initially, cask1 is ready

        // Act & Assert
        assertDoesNotThrow(() -> {
            warehouse.addCask(cask1);
        });
    }

    /**
     * Test for Warehouse.addCask method.
     * Checks if the warehouse can be filled to its capacity without throwing an exception.
     * This test implicitly verifies the additions by ensuring no error occurs for valid inputs up to capacity.
     * Valid data:
     * - cask1, cask2 (not null), warehouse with capacity for 2.
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Warehouse with capacity 2.
     * - cask1, cask2 objects.
     */
    @Test
    void TC2_addCask_happyPath_fillWarehouse() {
        // Arrange
        // warehouse initialized with capacity 2

        // Act & Assert
        assertDoesNotThrow(() -> {
            warehouse.addCask(cask1); // Add first cask
            warehouse.addCask(cask2); // Add second cask
        });
    }

    /**
     * Test for Warehouse.addCask method.
     * Checks if adding a null cask throws an IllegalArgumentException.
     * Valid data: None.
     * Invalid data:
     * - null cask object.
     * Basic data used:
     * - Warehouse with capacity 2.
     */
    @Test
    void TC3_addCask_error_nullCask() {
        // Arrange
        Cask nullCask = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addCask(nullCask);
        });
    }

    /**
     * Test for Warehouse.addCask method.
     * Checks if adding a cask that is already in the warehouse throws an IllegalArgumentException.
     * Valid data:
     * - cask1 already added.
     * Invalid data:
     * - Attempting to add cask1 again.
     * Basic data used:
     * - Warehouse with capacity 2.
     * - cask1 object.
     */
    @Test
    void TC4_addCask_error_caskAlreadyInWarehouse() {
        // Arrange
        warehouse.addCask(cask1); // Add cask1 first

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addCask(cask1); // Try to add cask1 again
        });
    }

    /**
     * Test for Warehouse.addCask method.
     * Checks if adding a cask to a full warehouse throws an IllegalStateException.
     * Valid data:
     * - warehouse filled with cask1 and cask2.
     * Invalid data:
     * - Attempting to add cask3 to a full warehouse.
     * Basic data used:
     * - Warehouse with capacity 2.
     * - cask1, cask2, cask3 objects.
     */
    @Test
    void TC5_addCask_error_warehouseFull() {
        // Arrange
        warehouse.addCask(cask1);
        warehouse.addCask(cask2); // Fill the warehouse

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            warehouse.addCask(cask3); // Try to add a third cask
        });
    }

    /**
     * Test for Warehouse.removeCask method.
     * Checks if an existing cask can be successfully removed from the warehouse without throwing an exception.
     * This test implicitly verifies removal by ensuring no error occurs for valid input.
     * Valid data:
     * - cask1 present in warehouse.
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Warehouse with capacity 2, containing cask1.
     */
    @Test
    void TC6_removeCask_happyPath_existingCask() {
        // Arrange
        warehouse.addCask(cask1);
        warehouse.addCask(cask2);

        // Act & Assert
        assertDoesNotThrow(() -> {
            warehouse.removeCask(cask1);
        });
    }

    /**
     * Test for Warehouse.removeCask method.
     * Checks if removing a non-existent cask does not throw an exception.
     * This test implicitly verifies the behavior as per the method's implementation (no error for non-existent cask).
     * Valid data: None for this specific test case, as the method should handle non-existence gracefully.
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Warehouse with capacity 2, containing cask1.
     * - cask3 object (not added to warehouse).
     */
    @Test
    void TC7_removeCask_edgeCase_nonExistentCask() {
        // Arrange
        warehouse.addCask(cask1);

        // Act & Assert
        assertDoesNotThrow(() -> {
            warehouse.removeCask(cask3); // Try to remove a cask not in the warehouse
        });
    }

    /**
     * Test for Warehouse.removeCask method.
     * Checks if removing a null cask throws an IllegalArgumentException.
     * Valid data: None.
     * Invalid data:
     * - null cask object.
     * Basic data used:
     * - Warehouse with capacity 2, containing cask1.
     */
    @Test
    void TC8_removeCask_error_nullCask() {
        // Arrange
        warehouse.addCask(cask1);
        Cask nullCask = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            warehouse.removeCask(nullCask);
        });
    }

    /**
     * Test for a central use case involving Cask and Warehouse.
     * Scenario: Add distillate to a cask, add the cask to the warehouse, then tap from the cask.
     * This use case test verifies the interaction between Cask and Warehouse, and the state change in Cask after tapping.
     * Valid data:
     * - cask1 with 500L capacity.
     * - warehouse with capacity 2.
     * - distillate1 object, 300 liters to add, 50 liters to tap.
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Cask with 500L capacity.
     * - Warehouse with capacity 2.
     * - distillate1 object.
     */
    @Test
    void TC9_UseCase_AddDistillateToCaskAndStoreInWarehouseThenTap() {
        // Arrange
        Distillate newDistillate = distillate1;
        Integer litersToAdd = 300;
        Integer litersToTap = 50;
        
        // Act + Assert         // assertDoesNotThrow bruges for at vi kalder dem og er sikker på der ikke kommer fejl
        assertDoesNotThrow(() -> cask1.addDistillate(newDistillate, litersToAdd));
        assertEquals(300, cask1.getTotalCurrentLiters());
        assertDoesNotThrow(() -> warehouse.addCask(cask1));
        assertDoesNotThrow(() -> cask1.tabDistillate(litersToTap));
        assertEquals(litersToAdd - litersToTap, cask1.getTotalCurrentLiters());
        assertDoesNotThrow(() -> warehouse.removeCask(cask1));
    }
}