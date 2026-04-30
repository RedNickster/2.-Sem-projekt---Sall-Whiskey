package model;

public class Cask {
    private int id;
    private int liters;
    private String previousLiquids;
    private String countryOfOrigin;
    private String supplier;

    public Cask(int id, int liters, String previousLiquids, String countryOfOrigin, String supplier) {
        this.id = id;
        this.liters = liters;
        this.previousLiquids = previousLiquids;
        this.countryOfOrigin = countryOfOrigin;
        this.supplier = supplier;
    }
}
