package model;

import java.util.ArrayList;
import java.util.List;

public class BottleBatch {
    private String name;
    private String description;
    private double amountOfWaterUsedToDilute;
    
    private List<BottleBatchLiquid> bottleBatchLiquidList;
    
    public BottleBatch(String name, String description) {
        this.name = name;
        this.description = description;
        bottleBatchLiquidList = new ArrayList<>();
        this.amountOfWaterUsedToDilute = 0;
    }
    
    /**
     *
     * @param targetAlcoholPercentage
     * @return how much water that needs to be added to batch to end up with the target alcohol percentage
     */
    public double dilluteLiquid(double targetAlcoholPercentage) {
        if (bottleBatchLiquidList.isEmpty()) {
            throw new IllegalStateException("Cannot dilute an empty bottle batch.");
        }
        if (targetAlcoholPercentage <= 0) {
            throw new IllegalArgumentException("Cannot dillute to an alcohol percentage lower or equal to zero.");
        }
        double actualPerc = getAlcoholPercentage();
        double actualVol = getTotalLiquidAmount();
        
        if (targetAlcoholPercentage >= actualPerc) {
            throw new IllegalArgumentException(
                    "Target alcohol percentage must be lower than the current percentage to dilute."
            );
        }
        
        double targetVolume = actualPerc / targetAlcoholPercentage * 100;
        double singleTimeWaterUsed = targetVolume - actualVol;
        this.amountOfWaterUsedToDilute = singleTimeWaterUsed;
        return singleTimeWaterUsed;
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
    
    public double getAmountOfWaterUsedToDilute() {
        return amountOfWaterUsedToDilute;
    }
}
