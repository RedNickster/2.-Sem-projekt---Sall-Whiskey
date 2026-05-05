package storage;

import model.Cask;
import model.Distillate;
import model.Distillation;
import model.Warehouse;

import java.util.*;

public class Storage {

    private List<Cask> casks = new ArrayList<>();
    private List<Distillate> distillates = new ArrayList<>();
    private List<Warehouse> warehouses = new ArrayList<>();
    private List<Distillation> distillations = new ArrayList<>();

    private int distillateCount;


    public Storage() {
        this.distillateCount = 0;
    }

    // ----- Cask -----
    public void addCask(Cask cask) {
        if (!casks.contains(cask)) {
            casks.add(cask);
        }
    }

    public int getCaskCount() {
        return casks.size();
    }

    public List<Cask> getCasks() {
        return new ArrayList<>(this.casks);
    }




    // ----- Distillates -----
    public void addDistillate(Distillate distillate) {
        if (!distillates.contains(distillate)) {
            distillates.add(distillate);
        }
        distillateCount++;
    }

    public int getDistillateCount() {
        return distillateCount;
    }

    public int getDistillationCount() {
        int count = 0;
        for (Distillate distillate : distillates) {
            for (Distillation d : distillate.getDistillations()) {
                count++;
            }
        }
        return count;
    }


    public List<Distillate> getDistillates() {
        return distillates;
    }



    // ----- Warehouse -----
    public void addWarehouse(Warehouse warehouse) {
        if (!warehouses.contains(warehouse)) {
            warehouses.add(warehouse);
        }
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }


    // ----- Distillation -----
    public void addDistillation(Distillation distillation) {
        if (!distillations.contains(distillation)) {
            distillations.add(distillation);
        }
    }

    public List<Distillation> getDistillations() {
        return new ArrayList<>(distillations);
    }
}
