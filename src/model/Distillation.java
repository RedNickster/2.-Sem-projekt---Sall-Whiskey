package model;

import java.time.LocalDate;

public class Distillation {
    private LocalDate startDate;
    private LocalDate endDate;
    private Enum<GrainVariety> grainVarietyEnum;
    private int liquidAmount;
    private double alcoholPercentage;
    private Enum<SmokingMaterial> smokingMaterialEnum;
    private int maltBatch;
    private String comment;
    private int newMakeNumber;
    private String employee;

    public Distillation(LocalDate startDate, LocalDate endDate, Enum<GrainVariety> grainVarietyEnum,
                        int liquidAmount, double alcoholPercentage, Enum<SmokingMaterial> smokingMaterialEnum,
                        int maltBatch, String comment, int newMakeNumber, String employee) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.grainVarietyEnum = grainVarietyEnum;
        this.liquidAmount = liquidAmount;
        this.alcoholPercentage = alcoholPercentage;
        this.smokingMaterialEnum = smokingMaterialEnum;
        this.maltBatch = maltBatch;
        this.comment = comment;
        this.newMakeNumber = newMakeNumber;
        this.employee = employee;
    }
}
