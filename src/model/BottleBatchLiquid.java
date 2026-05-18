package model;

import java.time.LocalDate;

public class BottleBatchLiquid {
    private double liquidAmount;
    private Liquid liquid;
    private LocalDate creationDate;
    
    public BottleBatchLiquid(double liquidAmount, Liquid liquid) {
        this.liquidAmount = liquidAmount;
        this.liquid = liquid;
        this.creationDate = LocalDate.now();
    }
    
    public Liquid getLiquid() {
        return liquid;
    }
    
    public double getLiquidAmount() {
        return liquidAmount;
    }
    
    public LocalDate getCreationDate() {
        return creationDate;
    }
}