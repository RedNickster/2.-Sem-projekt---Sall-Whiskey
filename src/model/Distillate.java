package model;

import model.enums.GrainVariety;

import java.util.ArrayList;
import java.util.List;

public class Distillate {
    
    private int newMakeNumber;
    private List<Distillation> distillations;
    private GrainVariety grainVarietyEnum;
    private String maltBatch;
    private boolean isSmoked;
    private double volumeInCasks;

    public Distillate(int newMakeNumber, GrainVariety grainVarietyEnum, String maltBatch) {
        this.newMakeNumber = newMakeNumber;
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
        return getVolumeOfLastDesstilation() - volumeInCasks;
    }

    public void subtractVolume(double amount) {
        if (amount <= getAvailableVolume()) {
            this.volumeInCasks += amount;
        } else {
            throw new IllegalArgumentException("Not enough volume in distillate");
        }
    }
    
    public double getVolumeOfLastDesstilation() {
        for (int i = distillations.size() - 1; i >= 0; i--) {
            if (distillations.get(i).getLiquidAmountAtEnd() > 0) {
                return distillations.get(i).getLiquidAmountAtEnd();
            }
        }
        return 0;
    }

    public List<Distillation> getDistillations() {
        return new ArrayList<>(distillations);
    }

    public int getNewMakeNumber() {
        return newMakeNumber;
    }

    @Override
    public String toString() {
        return "#" + newMakeNumber + " (Avail: " + getAvailableVolume() + "L / Total: " + getVolumeOfLastDesstilation() + "L) " + grainVarietyEnum + " " + maltBatch;
    }
}
