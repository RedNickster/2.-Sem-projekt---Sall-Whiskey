package model;

import model.enums.CaskLiquids;
import model.enums.GrainVariety;
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
        List<CaskLiquids> previousLiquids = new ArrayList<>(Arrays.asList(CaskLiquids.BOURBON, CaskLiquids.SHERRY));
        String countryOfOrigin = "USA";
        String supplier = "Supplier A";
        cask = new Cask(id, litersCapacity, previousLiquids, countryOfOrigin, supplier);
        
        
        // Basic data for Distillates
        String maltbatch = "malt batch";
        distillate1 = new Distillate(1, GrainVariety.EVERGREEN, maltbatch);
        distillate2 = new Distillate(2, GrainVariety.STAIRWAY, maltbatch);
    }
    
    @Test
    void TC1_addDistillate_ToCask_happyPath_emptyCask() {
        // Arrange
        // cask is initialized in @BeforeEach with 1000L capacity
        Distillate newDistillate = distillate1;
        int litersToAdd = 100;
        
        // Act
        cask.addDistillateToCask(newDistillate, litersToAdd);
        
        // Assert
        assertEquals(100, cask.containsLiters());
        // Note: Cannot directly assert specific distillate amount without public getter or reflection.
        // The total liters check provides implicit verification for now.
    }
    
    @Test
    void TC2_addDistillate_happyPath_existingDistillateToCask() {
        // Arrange
        int initialLiters = 100;
        cask.addDistillateToCask(distillate1, initialLiters);
        Distillate existingDistillate = distillate1;
        int litersToAdd = 200;
        
        // Act
        cask.addDistillateToCask(existingDistillate, litersToAdd);
        
        // Assert
        assertEquals(300, cask.containsLiters());
    }
    
    @Test
    void TC3_addDistillate_ToCask_edgeCase_exactCapacity() {
        // Arrange
        int initialLiters = 900;
        cask.addDistillateToCask(distillate1, initialLiters);
        int litersToAdd = 100;
        
        // Act
        cask.addDistillateToCask(distillate2, litersToAdd);
        
        // Assert
        assertEquals(1000, cask.containsLiters());
    }
    
    @Test
    void TC4_addDistillate_ToCask_error_exceedsCapacity() {
        // Arrange
        int initialLiters = 900;
        cask.addDistillateToCask(distillate1, initialLiters); // Fill cask almost to capacity
        int litersToAdd = 101; // Exceeds capacity by 1 liter
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cask.addDistillateToCask(distillate2, litersToAdd);
        });
        assertEquals(initialLiters, cask.containsLiters());
    }
    
    @Test
    void TC5_addDistillate_error_nullDistillateToCask() {
        // Arrange
        Distillate nullDistillate = null;
        int litersToAdd = 50;
        
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {cask.addDistillateToCask(nullDistillate, litersToAdd);});
    }
    
    @Test
    void TC6_addDistillate_ToCask_error_zeroLiters() {
        // Arrange
        Distillate newDistillate = distillate1;
        int litersToAdd = 0;
        
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {cask.addDistillateToCask(newDistillate, litersToAdd);});
    }
    
    @Test
    void TC7_addDistillate_ToCask_error_negativeLiters() {
        // Arrange
        Distillate newDistillate = distillate1;
        int litersToAdd = -50;
        
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {cask.addDistillateToCask(newDistillate, litersToAdd);});
    }
    
    @Test
    void TC8_tapDistillate_happyPath_proportionalReduction() {
        // Arrange
        // Add distillates to the cask: 100L of distillate1, 200L of distillate2. Total = 300L
        cask.addDistillateToCask(distillate1, 100);
        cask.addDistillateToCask(distillate2, 200);
        double litersToTap = 30; // Tap 30 liters
        
        // Act
        cask.tapDistillate(litersToTap);
        
        // Assert
        assertEquals(270, cask.containsLiters());
    }
    
    @Test
    void TC9_tapDistillate_edgeCase_tapAllLiters() {
        // Arrange
        cask.addDistillateToCask(distillate1, 100);
        cask.addDistillateToCask(distillate2, 200);
        double litersToTap = 300; // Tap all liters
        
        // Act
        cask.tapDistillate(litersToTap);
        
        // Assert
        assertEquals(0, cask.containsLiters());
    }
    
    @Test
    void TC10_tapDistillate_error_zeroLiters() {
        // Arrange
        cask.addDistillateToCask(distillate1, 100);
        double litersToTap = 0;
        double initialLiters = cask.containsLiters();
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cask.tapDistillate(litersToTap);
        });
        assertEquals(initialLiters, cask.containsLiters());
    }
    
    @Test
    void TC11_tapDistillate_error_negativeLiters() {
        // Arrange
        cask.addDistillateToCask(distillate1, 100);
        double litersToTap = -50;
        double initialLiters = cask.containsLiters();
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cask.tapDistillate(litersToTap);
        });
        assertEquals(initialLiters, cask.containsLiters());
    }
    
    @Test
    void TC12_tapDistillate_error_tapMoreThanAvailable() {
        // Arrange
        cask.addDistillateToCask(distillate1, 100);
        cask.addDistillateToCask(distillate2, 200);
        double litersToTap = 500; // More than available 300L
        double initialLiters = cask.containsLiters();
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cask.tapDistillate(litersToTap);
        });
        assertEquals(initialLiters, cask.containsLiters());
    }
}
