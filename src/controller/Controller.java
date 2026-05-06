package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    
    private Storage storage;
    
    public Controller() {
        this.storage = new Storage();
    }
    
    public Storage getStorage() {
        return storage;
    }
    
    public Cask createCask(int id, int liters, ArrayList<CaskLiquids> previousLiquids, String countryOfOrigin, String supplier) {
        Cask cask = new Cask(id, liters, previousLiquids, countryOfOrigin, supplier);
        storage.addCask(cask);
        return cask;
    }
    
    public Distillation createDistillation(int id, LocalDate startDate, String employee, String commment) {
        Distillation temp = new Distillation(id, startDate, employee, commment);
        storage.addDistillation(temp);
        return temp;
        
    }
    
    public Distillate createDistillate(GrainVariety grainVariety, String maltBatch) {
        int newMakeNumber = storage.getDistillates().size() + 1;
        
        Distillate temp = new Distillate(newMakeNumber, grainVariety, maltBatch);
        storage.addDistillate(temp);
        return temp;
    }
    
    public void combineToDistillate(List<Distillation> selectedDistillates, Distillate selectedDistillate) {
        for (Distillation selected : selectedDistillates) {
            selectedDistillate.addDistillation(selected);
        }
    }
    
    public void endDistillation(Distillation distillitation, LocalDate endDate, double liquidAmount, double alcoholPercentage, String comment) {
        if (distillitation != null) {
            distillitation.endDistillation(endDate, liquidAmount, alcoholPercentage, comment);
        }
    }
    
    public void pourDistillateIntoCask(Distillate distillate, int amount, Cask cask) {
        if (cask != null && distillate != null && amount > 0) {
            cask.addDistillate(distillate, amount);
        }
    }
    
    public Warehouse createWarehouse(String address, double m2, int storageSpaces) {
        Warehouse temp = new Warehouse(address, m2, storageSpaces);
        storage.addWarehouse(temp);
        return temp;
    }
    
    public void addCaskToWarehouse(Cask cask, Warehouse warehouse) {
        warehouse.addCask(cask);
    }
    
    
    public void addComment(Distillation distillation, String comment) {
        if (distillation != null && comment != null) {
            distillation.addComment(comment);
        }
    }
    
    public int getCaskCount() {
        return storage.getCaskCount();
    }
    
    public List<Distillation> getDistillations() {
        return storage.getDistillations();
    }
    
    public List<Cask> getCasks() {
        return storage.getCasks();
    }
    
    public List<Distillate> getDistillates() {
        return storage.getDistillates();
    }
    
    public List<Warehouse> getWarehouses() {
        return storage.getWarehouses();
    }
}
