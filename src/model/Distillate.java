package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Distillate {

    private List<Distillation> distillations = new ArrayList<Distillation>();
    private int newMakeNumber;
    private Enum<GrainVariety> grainVarietyEnum;

    public Distillate(int newMakeNumber, Enum<GrainVariety> grainVarietyEnum) {
        this.newMakeNumber = newMakeNumber;
        this.grainVarietyEnum = grainVarietyEnum;
    }

    public void addDistillation(Distillation distillation) {
        if (!this.distillations.contains(distillation)) {
            this.distillations.add(distillation);
        }
    }

    public Distillation createDistillations(LocalDate startDate, SmokingMaterial smokingMaterialEnum, int maltBatch,
                                            String employee, int newMakeNumber) {

        Distillation distillation = new Distillation(startDate, smokingMaterialEnum, maltBatch, employee,
                newMakeNumber);

        this.distillations.add(distillation);
        return distillation;
    }

    public List<Distillation> getDistillations() {
        return distillations;
    }
}
