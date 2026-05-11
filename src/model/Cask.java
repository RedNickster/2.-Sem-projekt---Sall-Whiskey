package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cask {
    private int id;
    private int liters;
    private List<CaskLiquids> previousLiquids;
    private String countryOfOrigin;
    private String supplier;
    private Map<Distillate, Integer> distillates;
    private List<CaskControl> caskControls;
    
    public Cask(int id, int liters, List<CaskLiquids> previousLiquids, String countryOfOrigin, String supplier) {
        this.id = id;
        this.liters = liters;
        this.previousLiquids = (previousLiquids != null) ? new ArrayList<>(previousLiquids) : new ArrayList<>();
        this.countryOfOrigin = countryOfOrigin;
        this.supplier = supplier;
        this.distillates = new HashMap<>();
        this.caskControls = new ArrayList<>();
    }
    
    public void addDistillate(Distillate distillate, Integer literToAdd) {
        if (literToAdd == null || distillate == null || literToAdd <= 0) {
            throw new IllegalArgumentException("Data is invalid");
        }
        if (containsLiters() + literToAdd > liters) {
            throw new IllegalArgumentException("There is not room for that amount of disstillate");
        }
        
        if (distillates.containsKey(distillate)) {
            distillates.compute(distillate, (_, currentLiters) -> currentLiters + literToAdd);
        } else {
            distillates.put(distillate, literToAdd);
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

        Map<Distillate, Integer> updatedDistillates = new HashMap<>();
        for (Map.Entry<Distillate, Integer> entry : distillates.entrySet()) {
            Distillate distillate = entry.getKey();
            Integer currentLiters = entry.getValue();
            
            // Calculate proportional reduction
            double proportion = (double) currentLiters / total;
            int litersToRemove = (int) Math.round(proportion * litersTapped);
            
            int newLiters = currentLiters - litersToRemove;
            if (newLiters < 0) {
                newLiters = 0; // Should not happen if calculations are correct and litersTapped <= total
            }
            updatedDistillates.put(distillate, newLiters);
        }
        this.distillates = updatedDistillates;
    }
    
    private int containsLiters() {
        int count = 0;
        for (Integer liters : distillates.values()) {
            count += liters;
        }
        return count;
    }
    
    private CaskControl createCaskControl(LocalDate date, double alcoholPercentage, String tasteComment) {
        CaskControl temp = new CaskControl(date, alcoholPercentage, tasteComment);
        this.caskControls.add(temp);
        return temp;
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
