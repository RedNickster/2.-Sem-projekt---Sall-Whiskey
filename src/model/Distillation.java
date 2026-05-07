package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Distillation {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double liquidAmountAtEnd;
    private double alcoholPercentage;
    private List<String> comments = new ArrayList<>();
    private String employee;
    private String status;
    
    public Distillation(int id, LocalDate startDate, String employee, String comment) {
        this.id = id;
        this.startDate = startDate;
        this.employee = employee;
        this.status = "Created";

        if (comment != null && !comment.isEmpty()) {
            this.comments.add(comment);
        }
    }
    
    public Distillation(int id, LocalDate startDate, String employee) {
        this.id = id;
        this.startDate = startDate;
        this.employee = employee;
        this.status = "Created";
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
        this.status = "Finalized";
        addComment(comment);
    }

    // Getters
    public int getId() {
        return id;
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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Distillation #"+ id;
    }
}
