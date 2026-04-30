package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Distillation {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int liquidAmount;
    private double alcoholPercentage;
    private SmokingMaterial smokingMaterialEnum;
    private int maltBatch;
    private List<String> comments = new ArrayList<>();
    private String employee;

    public Distillation(LocalDate startDate, SmokingMaterial smokingMaterialEnum, int maltBatch,
                        String employee, int id) {
        this.startDate = startDate;
        this.smokingMaterialEnum = smokingMaterialEnum;
        this.maltBatch = maltBatch;
        this.employee = employee;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getLiquidAmount() {
        return liquidAmount;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public SmokingMaterial getSmokingMaterialEnum() {
        return smokingMaterialEnum;
    }

    public int getMaltBatch() {
        return maltBatch;
    }

    public List<String> getComments() {
        return new ArrayList<>(this.comments);
    }

    public String getEmployee() {
        return employee;
    }

    public void addComment(String comment) {
        if (comment != null && !comment.trim().isEmpty()) {
            comments.add(comment);
        }
    }

    public void endDistillation(LocalDate endDate, int liquidAmount, double alcoholPercentage, String comment) {
        this.endDate = endDate;
        this.liquidAmount = liquidAmount;
        this.alcoholPercentage = alcoholPercentage;
        if (comment != null && !comment.trim().isEmpty()) {
            this.comments.add(comment);
        }
    }
}
