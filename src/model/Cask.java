package model;

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

    public Cask(int id, int liters, List<CaskLiquids> previousLiquids, String countryOfOrigin, String supplier) {
        this.id = id;
        this.liters = liters;
        this.previousLiquids = (previousLiquids != null) ? new ArrayList<>(previousLiquids) : new ArrayList<>();
        this.countryOfOrigin = countryOfOrigin;
        this.supplier = supplier;
        this.distillates = new HashMap<>();
    }

    private int containsLiters() {
        int count = 0;
        for (Integer liters : distillates.values()) {
            count += liters;
        }
        return count;
    }

    public void addDistillate(Distillate distillate, Integer literToAdd) {
        if (literToAdd == null || distillate == null || literToAdd <= 0) {
            return;
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

    public void tabDistillate(Integer litersTapped) {
        int total = containsLiters();
        for (Integer distillateLiters : distillates.values()) {
            int percent = distillateLiters / total;
            distillateLiters = distillateLiters - litersTapped * percent;
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
        return "Cask #" + id + " (" + liters + "L) - " + supplier + "(" + countryOfOrigin + ")" +
                " | Previous Liquids: " + previousLiquids;
    }
}
