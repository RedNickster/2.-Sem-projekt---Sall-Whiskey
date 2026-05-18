package storage;

import model.*;

import java.util.*;

public class Storage implements IStorage {

    private final List<Cask> casks;
    private final List<Distillate> distillates;
    private final List<Warehouse> warehouses;
    private final List<Distillation> distillations;
    private final List<BottleBatch> bottleBatches;

    public Storage() {
        this.casks = new ArrayList<>();
        this.distillates = new ArrayList<>();
        this.warehouses = new ArrayList<>();
        this.distillations = new ArrayList<>();
        this.bottleBatches = new ArrayList<>();
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

    // ----- BottleBatch -----
    public void addBottleBatch(BottleBatch bottleBatch) {
        if (!bottleBatches.contains(bottleBatch)) {
            bottleBatches.add(bottleBatch);
        }
    }

    public List<BottleBatch> getBottleBatches() {
        return new ArrayList<>(bottleBatches);
    }
}
