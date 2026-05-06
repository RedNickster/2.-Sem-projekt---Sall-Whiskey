package model;

import java.util.ArrayList;
import java.util.List;

public class Distillate {
    
    private int newMakeNumber;
    private List<Distillation> distillations;
    private GrainVariety grainVariety;
    private String maltBatch;
    private boolean isSmoked;

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

    public double getLiquidAmount(){
        return distillations.getLast().getLiquidAmountAtEnd();
    }

    public List<Distillation> getDistillations() {
        return new ArrayList<>(distillations);
    }

    public int getNewMakeNumber() {
        return newMakeNumber;
    }

    @Override
    public String toString() {
        return "New Make #" + newMakeNumber + " (" + getLiquidAmount() + "L)";
    }
}
