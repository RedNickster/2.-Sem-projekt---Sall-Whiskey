package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class DistillationTest {

    private Distillation distillation;
    private LocalDate startDate;
    private SmokingMaterial smokingMaterial;
    private int maltBatch;
    private String employee;
    private int id;

    @BeforeEach
    void setUp() {
        // setUp /\ Arrange
        startDate = LocalDate.of(2023, 1, 10);
        smokingMaterial = SmokingMaterial.OAK;
        maltBatch = 1;
        employee = "John Doe";
        id = 1;
        distillation = new Distillation(startDate, smokingMaterial, maltBatch, employee, id);
    }

    @Test
    void testDistillationConstructorAndInitialState() {
        // Tester om alt er oprettet rigtigt :)
        assertNotNull(distillation);
        assertEquals(id, distillation.getId());
        assertEquals(startDate, distillation.getStartDate());
        assertEquals(smokingMaterial, distillation.getSmokingMaterialEnum());
        assertEquals(maltBatch, distillation.getMaltBatch());
        assertEquals(employee, distillation.getEmployee());
        assertTrue(distillation.getComments().isEmpty());
        assertNull(distillation.getEndDate());
        assertEquals(0, distillation.getLiquidAmount());
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
    void testEndDistillation() {
        //Arrange
        LocalDate endDate = LocalDate.of(2023, 1, 20);
        int liquidAmount = 500;
        double alcoholPercentage = 63.5;
        String endComment = "Distillation completed successfully.";

        // Act
        distillation.endDistillation(endDate, liquidAmount, alcoholPercentage, endComment);

        // Assert
        assertEquals(endDate, distillation.getEndDate());
        assertEquals(liquidAmount, distillation.getLiquidAmount());
        assertEquals(alcoholPercentage, distillation.getAlcoholPercentage());
        assertEquals(1, distillation.getComments().size());
        assertTrue(distillation.getComments().contains(endComment));
    }

    @Test
    void testEndDistillationWithPreExistingComments() {
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
        assertEquals(liquidAmount, distillation.getLiquidAmount());
        assertEquals(alcoholPercentage, distillation.getAlcoholPercentage());
        assertEquals(2, distillation.getComments().size());
        assertTrue(distillation.getComments().contains(preExistingComment));
        assertTrue(distillation.getComments().contains(endComment));
    }
}
