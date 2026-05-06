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

    public Distillation createDistillation(int id, LocalDate startDate, LocalDate endDate, double liquidAmount, double alcoholPercentage, String maltBatch, GrainVariety grainVariety, SmokingMaterial smokingMaterialEnum, String employee, String commment) {
        Distillation temp = new Distillation(id, startDate, endDate, liquidAmount, alcoholPercentage, maltBatch, grainVariety, smokingMaterialEnum, employee, commment);
        storage.addDistillation(temp);
        return temp;

    }

    public Distillate combineToDistillate(List<Distillation> selectedDistillates) {
        int newMakeNumber = storage.getDistillates().size() + 1;
        Distillate distillate = new Distillate(newMakeNumber);

        for (Distillation selected : selectedDistillates) {
            distillate.addDistillation(selected);
        }

        storage.addDistillate(distillate);
        return distillate;
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

    public void createWarehouse(String adresse, double m2, int lagerPladser){
        storage.addWarehouse(new Warehouse(adresse, m2, lagerPladser));
    }

    public void addCaskToWarehouse(Cask cask, Warehouse warehouse) {
        warehouse.addCask(cask);
    }


    public void addComment(Distillation distillation, String comment){
        if (distillation != null && comment != null) {
            distillation.addComment(comment);
        }
    }

    public int getCaskCount(){
        return storage.getCaskCount();
    }

    public List<Distillation> getDistillations() {
        return storage.getDistillations();
    }
    public List<Cask> getCasks(){
        return storage.getCasks();
    }
    public List<Distillate> getDistillates() {
        return storage.getDistillates();
    }
    public List<Warehouse> getWarehouses() {
        return storage.getWarehouses();
    }
}
