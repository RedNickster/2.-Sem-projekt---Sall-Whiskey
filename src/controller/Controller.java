package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    Storage storage;

    public Controller() {
        this.storage = new Storage();
    }

    public Storage getStorage() {
        return storage;
    }

    public Cask createCask(int id, int liters, ArrayList<CaskLiquids> previousLiquids, String countryOfOrigin,
                           String supplier) {
        Cask tempCask = new Cask(id, liters, previousLiquids, countryOfOrigin, supplier);
        storage.addCask(tempCask);
        return tempCask;
    }



    public Distillation createDistillation(int id, LocalDate startDate, LocalDate endDate, double liquidAmount, double alcoholPercentage, String maltBatch, GrainVariety grainVariety, SmokingMaterial smokingMaterialEnum, String commment) {
        Distillation temp = new Distillation(id, startDate, endDate, liquidAmount, alcoholPercentage, maltBatch, grainVariety, smokingMaterialEnum, commment);
        storage.addDistillation(temp);
        return temp;

    }

    public Distillate compineToDistillate(List<Distillation> selectedDistillates, int newMakeNumber) {
        Distillate distillate = new Distillate(storage.getDistillateCount() + 1);

        for (Distillation selected : selectedDistillates) {
            distillate.addDistillation(selected);
        }

        storage.addDistillate(distillate);
        return distillate;
    }

    public void endDistillitation(Distillation distillitation, LocalDate endDate, int liquidAmount,
                                  double alcoholPercentage, String comment) {



        distillitation.endDistillation(endDate, liquidAmount, alcoholPercentage, comment);
    }

    public void createWarehouse(String adresse, double m2, int lagerPladser){
        storage.addWarehouse(new Warehouse(adresse, m2, lagerPladser));
    }

    public void pourDistillateIntoCask(Distillate distillate, int amount, Cask cask) {
        if (!cask.equals(null) && !distillate.equals(null) && amount != 0) {
            cask.addDistillate(distillate, amount);
        }
    }
    
    public void addCaskToWarehouse(Cask cask, Warehouse warehouse) {
        warehouse.addCask(cask);
    }


    public void addComment(){}

    public int getCaskCount(){
        return storage.getCaskCount();
    }

    public List<Cask> getCasks(){
        return storage.getCasks();
    }
}
