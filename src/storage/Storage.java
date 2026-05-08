package storage;

import model.Cask;
import model.Distillate;
import model.Distillation;
import model.Warehouse;

import java.util.*;

public class Storage {

    private final List<Cask> casks;
    private final List<Distillate> distillates;
    private final List<Warehouse> warehouses;
    private final List<Distillation> distillations;



    public Storage() {
        this.casks = new ArrayList<>();
        this.distillates = new ArrayList<>();
        this.warehouses = new ArrayList<>();
        this.distillations = new ArrayList<>();
    }



    // ----- Cask -----
    public void addCask(Cask cask) {
        if (!casks.contains(cask)) {
            casks.add(cask);
        }
    }

    public List<Cask> getCasks() {
        return new ArrayList<>(this.casks);
    }

    public int getCaskCount() {
        return casks.size();
    }



    // ----- Distillates -----
    public void addDistillate(Distillate distillate) {
        if (!distillates.contains(distillate)) {
            distillates.add(distillate);
        }
    }

    public List<Distillate> getDistillates() {
        return new ArrayList<>(this.distillates);
    }

    public int getDistillationsInDistillateCount() {
        int count = 0;
        for (Distillate distillate : distillates) {
            for (Distillation d : distillate.getDistillations()) {
                count++;
            }
        }
        return count;
    }


    // ----- Warehouse -----
    public void addWarehouse(Warehouse warehouse) {
        if (!warehouses.contains(warehouse)) {
            warehouses.add(warehouse);
        }
    }

    public List<Warehouse> getWarehouses() {
        return new ArrayList<>(this.warehouses);
    }


    // ----- Distillation -----
    public void addDistillation(Distillation distillation) {
        if (!distillations.contains(distillation)) {
            distillations.add(distillation);
        }
    }

    public List<Distillation> getDistillations() {
        return new ArrayList<>(this.distillations);
    }
}
