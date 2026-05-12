package model;

import model.enums.GrainVariety;

import java.util.ArrayList;
import java.util.List;

public class Distillate {
    
    private int inewMakeNumber;
    private List<Distillation> distillations;
    private GrainVariety grainVarietyEnum;
    private String maltBatch;
    private boolean isSmoked;
    private double volumeInCasks;

    public Distillate(int inewMakeNumber, GrainVariety grainVarietyEnum, String maltBatch) {
        this.inewMakeNumber = inewMakeNumber;
        this.distillations = new ArrayList<>();
        this.grainVarietyEnum = grainVarietyEnum;
        this.maltBatch = maltBatch;
        this.volumeInCasks = 0;
    }

    public void addDistillation(Distillation distillation) {
        if (distillation != null && !this.distillations.contains(distillation)) {
            distillations.add(distillation);
        }
    }

    private double getTotalVolume(){
        double total = 0;
        for (Distillation distillation : distillations) {
            total += distillation.getLiquidAmountAtEnd();
        }
        return total;
    }

    public double getAvailableVolume() {
        return getTotalVolume() - volumeInCasks;
    }

    public void subtractVolume(double amount) {
        if (amount <= getAvailableVolume()) {
            this.volumeInCasks += amount;
        } else {
            throw new IllegalArgumentException("Not enough volume in distillate");
        }
    }

    public List<Distillation> getDistillations() {
        return new ArrayList<>(distillations);
    }

    public int getInewMakeNumber() {
        return inewMakeNumber;
    }

    @Override
    public String toString() {
        return "#" + inewMakeNumber + " (Avail: " + getAvailableVolume() + "L / Total: " + getTotalVolume() + "L) " + grainVarietyEnum + " " + maltBatch;
    }
}
