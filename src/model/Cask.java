package model;

import java.util.ArrayList;
import java.util.List;

public class Cask {
    private int id;
    private int liters;
    private List<CaskLiquids> previousLiquids;
    private String countryOfOrigin;
    private String supplier;

    public Cask(int id, int liters, List<CaskLiquids> previousLiquids, String countryOfOrigin, String supplier) {
        this.id = id;
        this.liters = liters;
        this.previousLiquids = (previousLiquids != null) ? new ArrayList<>(previousLiquids) : new ArrayList<>();
        this.countryOfOrigin = countryOfOrigin;
        this.supplier = supplier;
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
