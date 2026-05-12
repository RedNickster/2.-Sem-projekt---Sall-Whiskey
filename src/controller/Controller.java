package controller;

import model.*;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private IStorage storage;

    public Controller(IStorage storage) {
        this.storage = storage;
    }

    public Controller() {
        this(new Storage());
    }

    public IStorage getStorage() {
        return storage;
    }

    /**
     * Metode som creater en ny tom Cask og tilføjer den til storage.
     * @param id
     * @param liters
     * @param previousLiquids
     * @param countryOfOrigin
     * @param supplier
     * @return
     */
    public Cask createCask(int id, int liters, ArrayList<CaskLiquids> previousLiquids, String countryOfOrigin, String supplier) {
        Cask cask = new Cask(id, liters, previousLiquids, countryOfOrigin, supplier);
        storage.addCask(cask);
        return cask;
    }

    /**
     * Metode som creater en ny Distillation, som ikke er destilleret endnu.
     *
     * BRUGES IKKE LÆNGERE, UDGÅET I ITERATION 4
     * @param id
     * @param startDate
     * @param employee
     * @param comment
     * @return
     */
    public Distillation createDistillation(int id, LocalDate startDate, String employee, String comment) {
        Distillation temp = new Distillation(id, startDate, employee, comment);
        storage.addDistillation(temp);
        return temp;
    }

    public Distillation createDistillationAndAddToDistillate(int id, LocalDate startDate, String employee, String commment, Distillate distillate) {
        Distillation temp = new Distillation(id, startDate, employee, commment);
        storage.addDistillation(temp);
        distillate.addDistillation(temp);
        return temp;
    }





    /**
     * Metode som slutter en distillation ved at sætte dens status til "Finalized", og dermed kan kun fortsætte
     * arbejdet hvis distillationen har destilleret to gange.
     * @param distillitation
     * @param endDate
     * @param liquidAmount
     * @param alcoholPercentage
     * @param comment
     */
    public void endDistillation(Distillation distillitation, LocalDate endDate, double liquidAmount, double alcoholPercentage, String comment) {
        if (distillitation != null) {
            distillitation.endDistillation(endDate, liquidAmount, alcoholPercentage, comment);
        }
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

    public void pourDistillateIntoCask(Distillate distillate, int amount, Cask cask) {
        if (cask != null && distillate != null && amount > 0) {
            if (amount > distillate.getAvailableVolume()) {
                throw new IllegalArgumentException("Not enough volume in distillate");
            }
            try {
                cask.addDistillate(distillate, amount);
                distillate.subtractVolume(amount);
            } catch (IllegalArgumentException e) {
                throw e;
            }
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


    public void addCommentToDistillation(Distillation distillation, String comment) {
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

    public List<Distillation> getActiveDistillations(Distillate distillate) {
        List<Distillation> activeList = new ArrayList<>();
        for (Distillation d : distillate.getDistillations()) {
            if (d.getEndDate() == null) {
                activeList.add(d);
            }
        }
        return activeList;
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
