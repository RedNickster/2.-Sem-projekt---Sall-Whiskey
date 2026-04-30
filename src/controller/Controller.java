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

    public Cask createCask(int id, int liters, ArrayList<CaskLiquids> previousLiquids, String countryOfOrigin,
                           String supplier) {
        Cask tempCask = new Cask(id, liters, previousLiquids, countryOfOrigin, supplier);
        storage.addCask(tempCask);
        return tempCask;
    }

    public Distillation createDistillation(Distillate destillitate, LocalDate startDate,
                                           SmokingMaterial smokingMaterialEnum, int maltBatch, String employee) {
        return destillitate.createDistillations(startDate, smokingMaterialEnum, maltBatch, employee,
                storage.getDistillationCount() + 1);
    }

    public Distillate createDistilitate(int newMakeNumber, Enum<GrainVariety> grainVarietyEnum) {
        Distillate temp = new Distillate(newMakeNumber, grainVarietyEnum);
        storage.addDistillate(temp);
        return temp;
    }

    public void addComment(){}

    public int getCaskCount(){
        return storage.getCaskCount();
    }

    public List<Cask> getCasks(){
        return storage.getCasks();
    }
}
