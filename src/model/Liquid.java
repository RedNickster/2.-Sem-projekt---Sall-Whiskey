package model;

import java.time.LocalDate;

public class Liquid {
    
    private boolean isWhisky;
    private LocalDate fillingDate;
    private Double amountOfDistillateInCask;
    
    private Cask cask;
    private Distillate distillate;
    
    public Liquid(LocalDate fillingDate, Double amountOfDistillateInCask, Cask cask, Distillate distillate) {
        this.fillingDate = fillingDate;
        this.amountOfDistillateInCask = amountOfDistillateInCask;
        this.cask = cask;
        this.distillate = distillate;
        this.isWhisky = false;
    }
    
    public void addAmountOfDistillateInCask(Double amountToAdd) {
        this.amountOfDistillateInCask += amountToAdd;
    }
    
    public Distillate getDistillate() {
        return distillate;
    }
    
    
}
