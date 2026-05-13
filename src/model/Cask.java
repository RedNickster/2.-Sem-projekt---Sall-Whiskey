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
        this.liquids = new ArrayList<>();
    }
    
    public void addDistillate(Distillate distillate, double literToAdd) {
        if (distillate == null || literToAdd <= 0) {
            throw new IllegalArgumentException("Data is invalid");
        }
        if (containsLiters() + literToAdd > liters) {
            throw new IllegalArgumentException("There is not room for that amount of disstillate");
        }
        Liquid existingLiquid = null;
        for (Liquid liquid : liquids) {
            if (liquid.getDistillate().equals(distillate)) {
                existingLiquid = liquid;
                break;
            }
        }

        if (existingLiquid != null) {
            existingLiquid.addAmountOfDistillateInCask(literToAdd);
        } else {
            this.liquids.add(new Liquid(LocalDate.now(), literToAdd, this, distillate));
        }
    }
    
    public void tapDistillate(double litersTapped) {
        if (litersTapped <= 0) {
            throw new IllegalArgumentException("Liters to tap must be positive.");
        }
        double total = containsLiters();
        if (litersTapped > total) {
            throw new IllegalArgumentException("Cannot tap more liters than available in the cask. Available: " + total + ", Tapped: " + litersTapped);
        }
        
        for (Liquid entry : liquids) {
            double currentLiters = entry.getAmountOfDistillateInCask();
            
            // Calculate proportional reduction
            double proportion = currentLiters / total;
            double litersToRemove = proportion * litersTapped;
            
            entry.removeAmountOfDistillateInCask(litersToRemove);
        }
    }
    
    public double containsLiters() {
        double count = 0;
        for (Liquid entry : liquids) {
            count += entry.getAmountOfDistillateInCask();
        }
        return count;
    }
    
    public void checkCask(LocalDate date, double alcoholPercentage, String tasteComment) {
        for (Liquid entry : liquids) {
            entry.checkLiquid(date, alcoholPercentage, tasteComment);
        }
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
    
    @Override
    public String toString() {
        return "Cask #" + id + " (Available space: " + (liters - containsLiters()) + "L / Total: " + liters + "L) - " + supplier + "(" + countryOfOrigin + ")" +
                " | Previous Liquids: " + previousLiquids;
    }
}
