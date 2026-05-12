package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Liquid {
    
    private boolean isWhisky;
    private LocalDate fillingDate;
    private Double amountOfDistillateInCask;
    
    private Cask cask;
    private Distillate distillate;
    private List<LiquidCheck> liquidChecks;
    
    public Liquid(LocalDate fillingDate, Double amountOfDistillateInCask, Cask cask, Distillate distillate) {
        this.fillingDate = fillingDate;
        this.amountOfDistillateInCask = amountOfDistillateInCask;
        this.cask = cask;
        this.distillate = distillate;
        this.isWhisky = false;
        this.liquidChecks = new ArrayList<>();
    }
    
    public void addAmountOfDistillateInCask(Double amountToAdd) {
        this.amountOfDistillateInCask += amountToAdd;
    }
    
    public void removeAmountOfDistillateInCask(Double amountToRemove) {
        this.amountOfDistillateInCask -= amountToRemove;
    }
    
    public void checkLiquid(LocalDate date, double alcoholPercent, String tasteComment) {
        liquidChecks.add(new LiquidCheck(date, alcoholPercent, tasteComment));
    }
    
    public boolean isWhisky() {
        if (fillingDate.plusYears(3).isBefore(LocalDate.now())) {
            isWhisky = true;
        }
        return isWhisky;
    }
    
    public Distillate getDistillate() {
        return distillate;
    }
    
    public Double getAmountOfDistillateInCask() {
        return amountOfDistillateInCask;
    }
}
