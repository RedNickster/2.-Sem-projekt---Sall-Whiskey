package model;

import java.util.ArrayList;
import java.util.List;

public class Cask {
    private int id;
    private int liters;
    private List<CaskLiquids> previousLiquids = new ArrayList<>();
    private String countryOfOrigin;
    private String supplier;

    public Cask(int id, int liters, ArrayList<CaskLiquids> previousLiquids, String countryOfOrigin, String supplier) {
        this.id = id;
        this.liters = liters;
        this.previousLiquids = previousLiquids;
        this.countryOfOrigin = countryOfOrigin;
        this.supplier = supplier;
    }
}
