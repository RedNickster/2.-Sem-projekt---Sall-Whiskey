package model;

public class BottleBatchLiquid {
    private double liquidAmount;
    private Liquid liquid;
    
    public BottleBatchLiquid(double liquidAmount, Liquid liquid) {
        this.liquidAmount = liquidAmount;
        this.liquid = liquid;
    }
    
    public Liquid getLiquid() {
        return liquid;
    }
    
    public double getLiquidAmount() {
        return liquidAmount;
    }
}