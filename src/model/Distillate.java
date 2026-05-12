package model;

import java.util.ArrayList;
import java.util.List;

public class Distillate {
    
    private int id;
    private List<Distillation> distillations;
    private GrainVariety grainVarietyEnum;
    private String maltBatch;
    private boolean isSmoked;
    private double volumeInCasks;

    public Distillate(int id, GrainVariety grainVarietyEnum, String maltBatch) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "#" + id + " (Avail: " + getAvailableVolume() + "L / Total: " + getTotalVolume() + "L) " + grainVarietyEnum + " " + maltBatch;
    }
}
