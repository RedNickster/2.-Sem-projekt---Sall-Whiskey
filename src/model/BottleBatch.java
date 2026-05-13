package model;

import java.util.ArrayList;
import java.util.List;

public class BottleBatch {
    private String name;
    private String description;
    
    private List<BottleBatchLiquid> bottleBatchLiquidList;
    
    public BottleBatch(String name, String description) {
        this.name = name;
        this.description = description;
        bottleBatchLiquidList = new ArrayList<>();
    }
    
    /**
     *
     * @param targetAlcoholPercentage
     * @return how much water that needs to be added to batch to end up with the target alcohol percentage
     */
    public double dilluteLiquid(double targetAlcoholPercentage) {
        double actualPerc = getAlcoholPercentage();
        double actualVol = getTotalLiquidAmount();
        
        if (targetAlcoholPercentage >= actualPerc) {
            throw new IllegalArgumentException(
                    "Target alcohol percentage must be lower than the current percentage to dilute."
            );
        }
        
        double targetVolume = actualPerc / targetAlcoholPercentage;
        return targetVolume - actualVol;
    }
    
    public BottleBatchLiquid addLiquid(BottleBatchLiquid liquid) {
        bottleBatchLiquidList.add(liquid);
        return liquid;
    }
    
    private double getAlcoholPercentage() {
        double alcoholPercentage = 0;
        double count = 0;
        
        for (BottleBatchLiquid bbl : bottleBatchLiquidList) {
            alcoholPercentage += bbl.getLiquid().getAlcoholPercentage();
            count++;
        }
        
        return alcoholPercentage / count;
    }
    
    private double getTotalLiquidAmount() {
        double liquidAmount = 0;
        for (BottleBatchLiquid bbl : bottleBatchLiquidList) {
            liquidAmount += bbl.getLiquidAmount();
        }
        return liquidAmount;
    }
}
