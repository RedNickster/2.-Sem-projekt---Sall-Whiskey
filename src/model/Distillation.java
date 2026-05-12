package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Distillation {
    private int newMakeNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private double liquidAmountAtEnd;
    private double alcoholPercentage;
    private List<String> comments;
    private String employee;
    
    public Distillation(int newMakeNumber, LocalDate startDate, String employee, String comment) {
        this.newMakeNumber = newMakeNumber;
        this.startDate = startDate;
        this.employee = employee;
        this.comments = new ArrayList<>();

        if (comment != null && !comment.isEmpty()) {
            this.comments.add(comment);
        }
    }
    
    public Distillation(int newMakeNumber, LocalDate startDate, String employee) {
        this.newMakeNumber = newMakeNumber;
        this.startDate = startDate;
        this.employee = employee;
    }
    
    

    public void addComment(String comment) {
        if (comment != null && !comment.trim().isEmpty()) {
            comments.add(comment);
        } else {
            throw new IllegalArgumentException("Comment cannot be null or empty");
        }
    }

    public void endDistillation(LocalDate endDate, double liquidAmount, double alcoholPercentage, String comment) {
        if (endDate == null) {
            throw new NullPointerException("endDate is null");
        }
        if (endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("endDate is before startDate");
        }
        if (liquidAmount <= 0) {
            throw new IllegalArgumentException("liquidAmount <= 0");
        }
        if (alcoholPercentage <= 0) {
            throw new IllegalArgumentException("alcoholPercentage <= 0");
        }
        this.endDate = endDate;
        this.liquidAmountAtEnd = liquidAmount;
        this.alcoholPercentage = alcoholPercentage;
        addComment(comment);
    }

    // Getters
    public int getNewMakeNumber() {
        return newMakeNumber;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public double getLiquidAmountAtEnd() {
        return liquidAmountAtEnd;
    }
    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }
    public List<String> getComments() {
        return new ArrayList<>(this.comments);
    }
    public String getEmployee() {
        return employee;
    }
    
    @Override
    public String toString() {
        return "New Make #"+ newMakeNumber;
    }
}
