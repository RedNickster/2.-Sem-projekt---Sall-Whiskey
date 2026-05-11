package model;

import java.time.LocalDate;

public class CaskControl {
    
    private LocalDate date;
    private double alcoholPercentage;
    private String tasteComment;
    
    protected CaskControl(LocalDate date, double alcoholPercentage, String tasteComment) {
        this.date = date;
        this.alcoholPercentage = alcoholPercentage;
        this.tasteComment = tasteComment;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }
    
    public String getTasteComment() {
        return tasteComment;
    }
}
