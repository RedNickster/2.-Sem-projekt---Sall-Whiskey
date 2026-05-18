package storage;

import model.*;

import java.util.List;

public interface IStorage {
    
    int getCaskCount();
    List<Cask> getCasks();
    List<Distillate> getDistillates();
    List<Warehouse> getWarehouses();
    List<Distillation> getDistillations();
    List<BottleBatch> getBottleBatches();
    
    void addCask(Cask cask);
    void addDistillate(Distillate distillate);
    void addWarehouse(Warehouse warehouse);
    void addDistillation(Distillation distillation);
    void addBottleBatch(BottleBatch bottleBatch);
    int getDistillationsInDistillateCount();

}
