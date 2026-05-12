package model;

import model.enums.CaskLiquids;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cask {
    private int id;
    private int liters;
    private List<CaskLiquids> previousLiquids;
    private String countryOfOrigin;
    private String supplier;
    private List<Liquid> liquids;
    
    public Cask(int id, int liters, List<CaskLiquids> previousLiquids, String countryOfOrigin, String supplier) {
        this.id = id;
        this.liters = liters;
        this.previousLiquids = (previousLiquids != null) ? new ArrayList<>(previousLiquids) : new ArrayList<>();
        this.countryOfOrigin = countryOfOrigin;
        this.supplier = supplier;
    }
    
    public void addDistillate(Distillate distillate, double literToAdd) {
        if (distillate == null || literToAdd <= 0) {
            throw new IllegalArgumentException("Data is invalid");
        }
        if (containsLiters() + literToAdd > liters) {
            throw new IllegalArgumentException("There is not room for that amount of disstillate");
        }
        
        if (liquids.contains(distillate)) {
            for (Liquid liquid : liquids) {
                if (liquid.getDistillate().equals(distillate)) {
                    liquid.addAmountOfDistillateInCask(literToAdd);
                }
            }
        } else {
            this.liquids.add(new Liquid(LocalDate.now(), literToAdd, this, distillate));
        }
    }
    
    public void checkCask(LocalDate date, double alcoholPercent, String tasteComment) {
        for (Liquid entry : liquids) {
            entry.checkLiquid(date, alcoholPercent, tasteComment);
        }
    }
    
    public void tapDistillate(Integer litersTapped) {
        if (litersTapped == null || litersTapped <= 0) {
            throw new IllegalArgumentException("Liters to tap must be positive.");
        }
        int total = containsLiters();
        if (litersTapped > total) {
            throw new IllegalArgumentException("Cannot tap more liters than available in the cask. Available: " + total + ", Tapped: " + litersTapped);
        }

        for (Liquid entry : liquids) {
            double currentLiters = entry.getAmountOfDistillateInCask();
            
            // Calculate proportional reduction
            double proportion = currentLiters / total;
            double litersToRemove = proportion * litersTapped;
            
            double newLiters = currentLiters - litersToRemove;
            if (newLiters < 0) {
                newLiters = 0; // Should not happen if calculations are correct and litersTapped <= total
            }
            entry.removeAmountOfDistillateInCask(newLiters);
        }
    }
    
    private int containsLiters() {
        int count = 0;
        for (Liquid entry : liquids) {
            count += entry.getAmountOfDistillateInCask();
        }
        return count;
    }
    
    public int getId() {
        return id;
    }
    
    public int getLiters() {
        return liters;
    }
    
    public List<CaskLiquids> getPreviousLiquids() {
        return new ArrayList<>(this.previousLiquids);
    }
    
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }
    
    public String getSupplier() {
        return supplier;
    }

    public int getTotalCurrentLiters() {
        return containsLiters();
    }
    
    
    @Override
    public String toString() {
        return "Cask #" + id + " (Available space: " + (liters - containsLiters()) + "L / Total: " + liters + "L) - " + supplier + "(" + countryOfOrigin + ")" +
                " | Previous Liquids: " + previousLiquids;
    }
}
