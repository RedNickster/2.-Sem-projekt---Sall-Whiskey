package model;

import java.time.LocalDate;

public class LiquidCheck {
    private LocalDate date;
    private double alcoholPercentage;
    private String tasteComment;
    
    public LiquidCheck(LocalDate date, double alcoholPercentage, String tasteComment) {
        this.date = date;
        this.alcoholPercentage = alcoholPercentage;
        this.tasteComment = tasteComment;
    }
    
    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }
}
