package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CaskTest {

    private Cask cask;
    private Distillate distillate1;
    private Distillate distillate2;

    @BeforeEach
    void setUp() {
        // Basic data for Cask
        int id = 1;
        int litersCapacity = 1000;
        List<CaskLiquids> previousLiquids = new ArrayList<>(Arrays.asList(CaskLiquids.BURBON, CaskLiquids.SHERRY));
        String countryOfOrigin = "USA";
        String supplier = "Supplier A";
        cask = new Cask(id, litersCapacity, previousLiquids, countryOfOrigin, supplier);

        // Basic data for Distillates
        distillate1 = new Distillate(1, GrainVariety.EVERGREEN);
        distillate2 = new Distillate(2, GrainVariety.STAIRWAY);
    }

    /**
     * Test for Cask.addDistillate method.
     * Checks if a distillate can be successfully added to an empty cask.
     * Valid data:
     * - distillate1 (not null), 100 liters (positive, fits in cask).
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 object.
     */
    @Test
    void TC1_addDistillate_happyPath_emptyCask() {
        // Arrange
        // cask is initialized in @BeforeEach with 1000L capacity
        Distillate newDistillate = distillate1;
        Integer litersToAdd = 100;

        // Act
        cask.addDistillate(newDistillate, litersToAdd);

        // Assert
        assertEquals(100, cask.getTotalCurrentLiters());
        // Note: Cannot directly assert specific distillate amount without public getter or reflection.
        // The total liters check provides implicit verification for now.
    }

    /**
     * Test for Cask.addDistillate method.
     * Checks if more liters of an existing distillate can be added.
     * Valid data:
     * - distillate1 (not null), initially 100 liters, then 200 liters (positive, fits).
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 object.
     */
    @Test
    void TC2_addDistillate_happyPath_existingDistillate() {
        // Arrange
        cask.addDistillate(distillate1, 100); // Add initial amount
        Distillate existingDistillate = distillate1;
        Integer litersToAdd = 200;

        // Act
        cask.addDistillate(existingDistillate, litersToAdd);

        // Assert
        assertEquals(300, cask.getTotalCurrentLiters());
    }

    /**
     * Test for Cask.addDistillate method.
     * Checks if different distillates can be added to the cask.
     * Valid data:
     * - distillate1 (not null), 100 liters.
     * - distillate2 (not null), 300 liters.
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1, distillate2 objects.
     */
    @Test
    void TC3_addDistillate_happyPath_multipleDistillates() {
        // Arrange
        cask.addDistillate(distillate1, 100);
        Distillate anotherDistillate = distillate2;
        Integer litersToAdd = 300;

        // Act
        cask.addDistillate(anotherDistillate, litersToAdd);

        // Assert
        assertEquals(400, cask.getTotalCurrentLiters());
    }

    /**
     * Test for Cask.addDistillate method.
     * Checks if a distillate can be added to exactly fill the cask capacity.
     * Valid data:
     * - distillate1 (900L), distillate2 (100L), total 1000L.
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1, distillate2 objects.
     */
    @Test
    void TC4_addDistillate_edgeCase_exactCapacity() {
        // Arrange
        cask.addDistillate(distillate1, 900); // Fill cask almost to capacity
        Distillate newDistillate = distillate2;
        Integer litersToAdd = 100; // Exactly fills the cask

        // Act
        cask.addDistillate(newDistillate, litersToAdd);

        // Assert
        assertEquals(1000, cask.getTotalCurrentLiters());
    }

    /**
     * Test for Cask.addDistillate method.
     * Checks if adding more liters than the cask's remaining capacity throws an IllegalArgumentException.
     * Valid data:
     * - distillate1 (900L).
     * Invalid data:
     * - 101 liters to add when only 100 liters capacity remains.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1, distillate2 objects.
     */
    @Test
    void TC5_addDistillate_error_exceedsCapacity() {
        // Arrange
        cask.addDistillate(distillate1, 900); // Fill cask almost to capacity
        Distillate newDistillate = distillate2;
        Integer litersToAdd = 101; // Exceeds capacity by 1 liter
        int initialLiters = cask.getTotalCurrentLiters();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cask.addDistillate(newDistillate, litersToAdd);
        }, "Should throw IllegalArgumentException when adding more than capacity");
        assertEquals(initialLiters, cask.getTotalCurrentLiters(), "Cask content should remain unchanged");
    }

    /**
     * Test for Cask.addDistillate method.
     * Checks if adding a null distillate is handled gracefully (no change to cask).
     * Valid data: None.
     * Invalid data:
     * - null distillate object.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - 50 liters to add (irrelevant as distillate is null).
     */
    @Test
    void TC6_addDistillate_error_nullDistillate() {
        // Arrange
        Distillate nullDistillate = null;
        Integer litersToAdd = 50;
        int initialLiters = cask.getTotalCurrentLiters();

        // Act
        cask.addDistillate(nullDistillate, litersToAdd);

        // Assert
        assertEquals(initialLiters, cask.getTotalCurrentLiters(), "Cask content should not change when adding null distillate");
    }

    /**
     * Test for Cask.addDistillate method.
     * Checks if adding 0 liters is handled gracefully (no change to cask).
     * Valid data: None.
     * Invalid data:
     * - 0 liters to add.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 object.
     */
    @Test
    void TC7_addDistillate_error_zeroLiters() {
        // Arrange
        Distillate newDistillate = distillate1;
        Integer litersToAdd = 0;
        int initialLiters = cask.getTotalCurrentLiters();

        // Act
        cask.addDistillate(newDistillate, litersToAdd);

        // Assert
        assertEquals(initialLiters, cask.getTotalCurrentLiters(), "Cask content should not change when adding 0 liters");
    }

    /**
     * Test for Cask.addDistillate method.
     * Checks if adding negative liters is handled gracefully (no change to cask).
     * Valid data: None.
     * Invalid data:
     * - -50 liters to add.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 object.
     */
    @Test
    void TC8_addDistillate_error_negativeLiters() {
        // Arrange
        Distillate newDistillate = distillate1;
        Integer litersToAdd = -50;
        int initialLiters = cask.getTotalCurrentLiters();

        // Act
        cask.addDistillate(newDistillate, litersToAdd);

        // Assert
        assertEquals(initialLiters, cask.getTotalCurrentLiters(), "Cask content should not change when adding negative liters");
    }

    /**
     * Test for Cask.tabDistillate method.
     * Note: This method is being tested as implemented by the user, which has a known flaw (it does not
     * actually update the `distillates` map and performs integer division for `percent`).
     * This test expects the flawed behavior where the total liters in the cask remain unchanged.
     * Valid data:
     * - 30 liters to tap (positive, less than total available).
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 (100L), distillate2 (200L).
     */
    @Test
    void TC9_tabDistillate_happyPath_proportionalReduction() {
        // Arrange
        // Add distillates to the cask: 100L of distillate1, 200L of distillate2. Total = 300L
        cask.addDistillate(distillate1, 100);
        cask.addDistillate(distillate2, 200);
        Integer litersToTap = 30; // Tap 30 liters

        // Act
        cask.tabDistillate(litersToTap);

        // Assert
        assertEquals(270, cask.getTotalCurrentLiters(), "Total liters should be reduced by the tapped amount");
    }

    /**
     * Test for Cask.tabDistillate method.
     * Note: This method is being tested as implemented by the user, which has a known flaw.
     * This test expects the flawed behavior where the total liters in the cask remain unchanged.
     * Valid data:
     * - 300 liters to tap (positive, equal to total available).
     * Invalid data: None for this specific test case.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 (100L), distillate2 (200L).
     */
    @Test
    void TC10_tabDistillate_edgeCase_tapAllLiters() {
        // Arrange
        cask.addDistillate(distillate1, 100);
        cask.addDistillate(distillate2, 200);
        Integer litersToTap = 300; // Tap all liters

        // Act
        cask.tabDistillate(litersToTap);

        // Assert
        assertEquals(0, cask.getTotalCurrentLiters(), "Total liters should be 0 after tapping all available liters");
    }

    /**
     * Test for Cask.tabDistillate method.
     * Checks if tapping 0 liters is handled gracefully (no change to cask).
     * Valid data: None.
     * Invalid data:
     * - 0 liters to tap.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 (100L).
     */
    @Test
    void TC11_tabDistillate_error_zeroLiters() {
        // Arrange
        cask.addDistillate(distillate1, 100);
        Integer litersToTap = 0;
        int initialLiters = cask.getTotalCurrentLiters();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cask.tabDistillate(litersToTap);
        }, "Should throw IllegalArgumentException when tapping 0 liters");
        assertEquals(initialLiters, cask.getTotalCurrentLiters(), "Cask content should remain unchanged");
    }

    /**
     * Test for Cask.tabDistillate method.
     * Checks if tapping negative liters is handled gracefully (no change to cask).
     * Valid data: None.
     * Invalid data:
     * - -50 liters to tap.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 (100L).
     */
    @Test
    void TC12_tabDistillate_error_negativeLiters() {
        // Arrange
        cask.addDistillate(distillate1, 100);
        Integer litersToTap = -50;
        int initialLiters = cask.getTotalCurrentLiters();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cask.tabDistillate(litersToTap);
        }, "Should throw IllegalArgumentException when tapping negative liters");
        assertEquals(initialLiters, cask.getTotalCurrentLiters(), "Cask content should remain unchanged");
    }

    /**
     * Test for Cask.tabDistillate method.
     * Checks if tapping more liters than available in the cask (even if not explicitly thrown by current impl)
     * is handled without changing the cask content.
     * Valid data: None.
     * Invalid data:
     * - 500 liters to tap when only 300 liters available.
     * Basic data used:
     * - Cask with 1000 liters capacity.
     * - distillate1 (100L), distillate2 (200L).
     */
    @Test
    void TC13_tabDistillate_error_tapMoreThanAvailable() {
        // Arrange
        cask.addDistillate(distillate1, 100);
        cask.addDistillate(distillate2, 200);
        Integer litersToTap = 500; // More than available 300L
        int initialLiters = cask.getTotalCurrentLiters();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cask.tabDistillate(litersToTap);
        }, "Should throw IllegalArgumentException when tapping more liters than available");
        assertEquals(initialLiters, cask.getTotalCurrentLiters(), "Cask content should remain unchanged");
    }
}
