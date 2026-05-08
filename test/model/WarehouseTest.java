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
        int newMakeNumber = 1;
        GrainVariety grainVariety = GrainVariety.EVERGREEN;
        String maltBatch = "Malt Batch";
        distillate1 = new Distillate(newMakeNumber, grainVariety, maltBatch);
    }

    @Test
    void TC1_addCask_happyPath_emptyWarehouse() {
        // Arrange
        // warehouse is empty initially, cask1 is ready

        // Act & Assert
        assertDoesNotThrow(() -> {
            warehouse.addCask(cask1);
        });
    }

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

    @Test
    void TC3_addCask_error_nullCask() {
        // Arrange
        Cask nullCask = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addCask(nullCask);
        });
    }

    @Test
    void TC4_addCask_error_caskAlreadyInWarehouse() {
        // Arrange
        warehouse.addCask(cask1); // Add cask1 first

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addCask(cask1); // Try to add cask1 again
        });
    }

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

    @Test
    void TC7_removeCask_edgeCase_nonExistentCask() {
        // Arrange
        warehouse.addCask(cask1);

        // Act & Assert
        assertDoesNotThrow(() -> {
            warehouse.removeCask(cask3); // Try to remove a cask not in the warehouse
        });
    }

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
}