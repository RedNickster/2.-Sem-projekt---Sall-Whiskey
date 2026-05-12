package storage;

import model.Cask;
import model.Distillate;
import model.Distillation;
import model.Warehouse;

import java.util.List;

public interface IStorage {
    void addCask(Cask cask);
    List<Cask> getCasks();
    int getCaskCount();

    void addDistillate(Distillate distillate);
    List<Distillate> getDistillates();
    int getDistillationsInDistillateCount();

    void addWarehouse(Warehouse warehouse);
    List<Warehouse> getWarehouses();

    void addDistillation(Distillation distillation);
    List<Distillation> getDistillations();
}
