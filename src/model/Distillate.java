package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Distillate {
    private int newMakeNumber;
    private List<Distillation> distillations;

    public Distillate(int newMakeNumber) {
        this.newMakeNumber = newMakeNumber;
        this.distillations = new ArrayList<>();
    }

    public void addDistillation(Distillation distillation) {
        if (distillation != null && !this.distillations.contains(distillation)) {
            distillations.add(distillation);
        }
    }

    public double getTotalVolume(){
        double total = 0;
        for (Distillation distillation : distillations) {
            total += distillation.getLiquidAmount();
        }
        return total;
    }

    public List<Distillation> getDistillations() {
        return new ArrayList<>(distillations);
    }

    public int getNewMakeNumber() {
        return newMakeNumber;
    }

    @Override
    public String toString() {
        return "New Make #" + newMakeNumber + " (" + getTotalVolume() + "L)";
    }
}
