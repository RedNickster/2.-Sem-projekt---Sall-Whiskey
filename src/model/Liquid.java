package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Liquid {
    
    private boolean isWhisky;
    private LocalDate fillingDate;
    private double amountOfDistillateInCask;
    
    private Cask cask;
    private Distillate distillate;
    private List<LiquidCheck> liquidChecks;
    
    public Liquid(LocalDate fillingDate, double amountOfDistillateInCask, Cask cask, Distillate distillate) {
        this.fillingDate = fillingDate;
        this.amountOfDistillateInCask = amountOfDistillateInCask;
        this.cask = cask;
        this.distillate = distillate;
        this.isWhisky = false;
        this.liquidChecks = new ArrayList<>();
    }
    
    void addAmountOfDistillateInCask(double amountToAdd) {
        this.amountOfDistillateInCask += amountToAdd;
    }
    
    void removeAmountOfDistillateInCask(double amountToRemove) {
        this.amountOfDistillateInCask -= amountToRemove;
    }
    
    public void checkLiquid(LocalDate date, double alcoholPercent, String tasteComment) {
        checkIfIsWhisky();
        liquidChecks.add(new LiquidCheck(date, alcoholPercent, tasteComment));
    }
    
    public boolean checkIfIsWhisky() {
        if (fillingDate.plusYears(3).isBefore(LocalDate.now()) && lastCheckAlcoholOverForty()) {
            isWhisky = true;
        }
        return isWhisky;
    }
    
    private boolean lastCheckAlcoholOverForty() {
        if (liquidChecks.getLast().getAlcoholPercentage() >= 40.0) {
            return true;
        }
        return false;
    }
    
    public double getAlcoholPercentage() {
        return liquidChecks.getLast().getAlcoholPercentage();
    }
    
    public Distillate getDistillate() {
        return distillate;
    }
    
    public double getAmountOfDistillateInCask() {
        return amountOfDistillateInCask;
    }
    
    public LocalDate getFillingDate() {
        return fillingDate;
    }
    
    public Cask getCask() {
        return cask;
    }
}
