package storage;

import model.Cask;
import model.Distillate;
import model.Distillation;
import model.Warehouse;

import java.util.List;

public interface IStorage {
    
    int getCaskCount();
    List<Cask> getCasks();
    List<Distillate> getDistillates();
    List<Warehouse> getWarehouses();
    int getDistillationsInDistillateCount();
    
    List<Distillation> getDistillations();
    void addCask(Cask cask);
    void addDistillate(Distillate distillate);
    void addWarehouse(Warehouse warehouse);
    void addDistillation(Distillation distillation);
}
