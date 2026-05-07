package model;

import java.util.ArrayList;
import java.util.List;

public class Distillate {
    
    private int newMakeNumber;
    private List<Distillation> distillations;
    private GrainVariety grainVariety;
    private String maltBatch;
    private boolean isSmoked;
    private double volumeInCasks = 0;

    public Distillate(int newMakeNumber,  GrainVariety grainVariety, String maltBatch) {
        this.newMakeNumber = newMakeNumber;
        this.distillations = new ArrayList<>();
        this.grainVariety = grainVariety;
        this.maltBatch = maltBatch;
    }

    public void addDistillation(Distillation distillation) {
        if (distillation != null && !this.distillations.contains(distillation)) {
            distillations.add(distillation);
        }
    }

    public double getTotalVolume(){
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

    public int getNewMakeNumber() {
        return newMakeNumber;
    }

    @Override
    public String toString() {
        return "New Make #" + newMakeNumber + " (Avail: " + getAvailableVolume() + "L / Total: " + getTotalVolume() + "L) " + grainVariety + " " + maltBatch;
    }
}
