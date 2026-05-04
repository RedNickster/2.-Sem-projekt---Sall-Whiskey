package model;

import java.util.HashMap;

public class Warehouse {

    private String adresse;
    private double m2;
    private boolean erFuld;
    private Cask[] lokationer; // <Lokation, Cask>

    public Warehouse(String adresse, double m2, int lagerPladser) {
        this.adresse = adresse;
        this.m2 = m2;
        this.erFuld = false;
        lokationer = new Cask[lagerPladser];
    }

    private boolean isCaskInWareHouse(Cask cask) {
        for (Cask value : lokationer) {
            if (value == cask) {
                return true;
            }
        }
        return false;
    }

    public void addCask(Cask cask) {
        if (cask == null) {
            throw new IllegalArgumentException("Cask cannot be null");
        }
        // Checks if cask is already in warehouse
        if (isCaskInWareHouse(cask)) {
            throw new IllegalArgumentException("Cask is already in warehouse");
        }

        // Checks if there is room for the cask, if yes; set lokation to cask and quit
        for (int i = 0; i < lokationer.length; i++) {
            if (lokationer[i] == null) {
                lokationer[i] = cask;
                return;
            }
        }

        // If we get here there is no more room in the warehouse
        throw new IllegalStateException("There is no more room in the warehouse");
    }

    public void removeCask(Cask cask) {
        if (cask == null) {
            throw new IllegalArgumentException("Cask cannot be null");
        }
        
        for (int i = 0; i < lokationer.length; i++) {
            if (lokationer[i] == cask) {
                lokationer[i] = null;
            }
        }
    }


}
