package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BottleBatchTest {

    private BottleBatch bottleBatch;

    @BeforeEach
    void setUp() {
        // Initialize a fresh BottleBatch for each test
        bottleBatch = new BottleBatch("Test Batch", "Description for test batch");
    }

    // Helper class for mocking Liquid
    private class MockLiquid extends Liquid {
        private double alcoholPercentage; // To mock getAlcoholPercentage
        private double amount; // To mock getAmountOfDistillateInCask

        public MockLiquid(double amount, double alcoholPercentage) {
            // Pass dummy values for Cask and Distillate because they are not relevant for this test
            super(LocalDate.now(), amount, null, null);
            this.alcoholPercentage = alcoholPercentage;
            this.amount = amount;
        }

        @Override
        public double getAlcoholPercentage() {
            return alcoholPercentage;
        }

        @Override
        public double getAmountOfDistillateInCask() {
            return amount;
        }
    }

    @Test
    void TC1_dilluteLiquid_happyPath_simpleDilution() {
        // Arrange
        // Mock Liquid with 50% alcohol and 100L volume
        MockLiquid mockLiquid1 = new MockLiquid(100, 50.0);
        bottleBatch.addLiquid(new BottleBatchLiquid(100, mockLiquid1, LocalDate.now()));

        double targetPercentage = 40.0; // Dilute from 50% to 40%

        // Act
        double waterNeeded = bottleBatch.dilluteLiquid(targetPercentage);

        // Assert
        assertEquals(25.0, waterNeeded); // Expect 25L of water
    }

    @Test
    void TC2_dilluteLiquid_edgeCase_targetEqualsCurrent() {
        // Arrange
        MockLiquid mockLiquid1 = new MockLiquid(100, 50.0);
        bottleBatch.addLiquid(new BottleBatchLiquid(100, mockLiquid1, LocalDate.now()));

        double targetPercentage = 50.0; // Target equals current

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                bottleBatch.dilluteLiquid(targetPercentage));
        assertEquals("Target alcohol percentage must be lower than the current percentage to dilute.", thrown.getMessage());
    }

    @Test
    void TC3_dilluteLiquid_edgeCase_targetHigherThanCurrent() {
        // Arrange
        MockLiquid mockLiquid1 = new MockLiquid(100, 50.0);
        bottleBatch.addLiquid(new BottleBatchLiquid(100, mockLiquid1, LocalDate.now()));

        double targetPercentage = 60.0; // Target higher than current

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                bottleBatch.dilluteLiquid(targetPercentage));
        assertEquals("Target alcohol percentage must be lower than the current percentage to dilute.", thrown.getMessage());
    }

    @Test
    void TC4_dilluteLiquid_error_emptyBatch() {
        // Arrange - bottleBatch is empty by default from @BeforeEach

        double targetPercentage = 40.0;

        // Act & Assert
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                bottleBatch.dilluteLiquid(targetPercentage));
        assertEquals("Cannot dilute an empty bottle batch.", thrown.getMessage());
    }

    @Test
    void TC5_dilluteLiquid_boundary_zeroTargetPercentage() {
        // Arrange
        MockLiquid mockLiquid1 = new MockLiquid(100, 50.0);
        bottleBatch.addLiquid(new BottleBatchLiquid(100, mockLiquid1, LocalDate.now()));

        double targetPercentage = 0.0;

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                bottleBatch.dilluteLiquid(targetPercentage));
        assertEquals("Cannot dillute to an alcohol percentage lower or equal to zero.", thrown.getMessage()); // Expected Java default message for division by zero
    }

    @Test
    void TC6_dilluteLiquid_boundary_negativeTargetPercentage() {
        // Arrange
        MockLiquid mockLiquid1 = new MockLiquid(100, 50.0);
        bottleBatch.addLiquid(new BottleBatchLiquid(100, mockLiquid1, LocalDate.now()));

        double targetPercentage = -10.0;

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                bottleBatch.dilluteLiquid(targetPercentage));
        assertEquals("Cannot dillute to an alcohol percentage lower or equal to zero.", thrown.getMessage());
    }
}
