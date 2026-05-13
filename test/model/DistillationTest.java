package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class DistillationTest {

    private Distillation distillation;
    private LocalDate startDate;
    private String employee;
    private int id;
    
    @BeforeEach
    void setUp() {
        // setUp /\ Arrange
        startDate = LocalDate.of(2023, 1, 10);
        
        employee = "John Doe";
        id = 1;
        distillation = new Distillation(id, startDate, employee);
    }

    @Test
    void testDistillationConstructorAndInitialState() {
        // Tester om alt er oprettet rigtigt :)
        assertNotNull(distillation);
        assertEquals(id, distillation.getId());
        assertEquals(startDate, distillation.getStartDate());
        assertEquals(employee, distillation.getEmployee());
        assertTrue(distillation.getComments().isEmpty());
        assertNull(distillation.getEndDate());
        assertEquals(0, distillation.getLiquidAmountAtEnd());
        assertEquals(0.0, distillation.getAlcoholPercentage());
    }

    @Test
    void testAddComment() {
        // Arrage

        // Act
        String comment = "Initial comment for distillation process.";
        distillation.addComment(comment);

        // Assert
        assertEquals(1, distillation.getComments().size());
        assertTrue(distillation.getComments().contains(comment));
    }

    @Test
    void TC1_testEndDistillation() {
        //Arrange
        LocalDate endDate = LocalDate.of(2023, 1, 20);
        int liquidAmount = 500;
        double alcoholPercentage = 63.5;
        String endComment = "Distillation completed successfully.";

        // Act
        distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);

        // Assert
        assertEquals(endDate, distillation.getEndDate());
        assertEquals(liquidAmount, distillation.getLiquidAmountAtEnd());
        assertEquals(alcoholPercentage, distillation.getAlcoholPercentage());
        assertEquals(1, distillation.getComments().size());
        assertTrue(distillation.getComments().contains(endComment));
    }

    @Test
    void TC1_testEndDistillationWithPreExistingComments() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 20);
        int liquidAmount = 500;
        double alcoholPercentage = 63.5;
        String endComment = "Distillation completed successfully.";

        // Act
        distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);

        // Assert
        assertEquals(endDate, distillation.getEndDate());
        assertEquals(liquidAmount, distillation.getLiquidAmountAtEnd());
        assertEquals(alcoholPercentage, distillation.getAlcoholPercentage());
        assertEquals(2, distillation.getComments().size());
        assertTrue(distillation.getComments().contains(preExistingComment));
        assertTrue(distillation.getComments().contains(endComment));
    }

    @Test
    void TC2_testEndDistillationendDateAtLimit() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 10);
        int liquidAmount = 500;
        double alcoholPercentage = 63.5;
        String endComment = "Distillation completed successfully.";

        // Act
        distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);

        // Assert
        assertEquals(endDate, distillation.getEndDate());
    }

    @Test
    void TC3_testEndDistillationendDateBeforeLimit() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 9);
        int liquidAmount = 500;
        double alcoholPercentage = 63.5;
        String endComment = "Distillation completed successfully.";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);
        });
    }

    @Test
    void TC4_testEndDistillationendDateIsNULL() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = null;
        int liquidAmount = 500;
        double alcoholPercentage = 63.5;
        String endComment = "Distillation completed successfully.";

        // Act + Assert
        assertThrows(NullPointerException.class, () -> {
            distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);
        });
    }

    @Test
    void TC5_testEndDistillationliquidAmountAtLimit() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 10);
        int liquidAmount = 1;
        double alcoholPercentage = 63.5;
        String endComment = "Distillation completed successfully.";

        // Act
        distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);

        // Assert
        assertEquals(liquidAmount, distillation.getLiquidAmountAtEnd());
    }

    @Test
    void TC6_testEndDistillationalliquidAmountOverLimit() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 10);
        int liquidAmount = 0;
        double alcoholPercentage = 63.5;
        String endComment = "Distillation completed successfully.";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);
        });
    }

    @Test
    void TC7_testEndDistillationalalcoholPercentageAtLimit() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 10);
        int liquidAmount = 500;
        double alcoholPercentage = 0.1;
        String endComment = "Distillation completed successfully.";

        // Act
        distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);

        // Assert
        assertEquals(alcoholPercentage, distillation.getAlcoholPercentage());
    }

    @Test
    void TC8_testEndDistillationalalcoholPercentageOnLimit() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 10);
        int liquidAmount = 500;
        double alcoholPercentage = 0;
        String endComment = "Distillation completed successfully.";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);
        });
    }

    @Test
    void TC9_testEndDistillationalcommentIsEmpty() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 10);
        int liquidAmount = 500;
        double alcoholPercentage = 63.5;
        String endComment = "";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);
        });
    }

    @Test
    void TC10_testEndDistillationalcommentOnlySpace() {
        // Arrange
        String preExistingComment = "Pre-existing comment.";
        distillation.addComment(preExistingComment);

        LocalDate endDate = LocalDate.of(2023, 1, 10);
        int liquidAmount = 500;
        double alcoholPercentage = 63.5;
        String endComment = "   ";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);
        });
    }

}
