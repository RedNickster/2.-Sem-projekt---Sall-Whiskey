package gui;

import controller.Controller;
import javafx.application.Application;
import model.GrainVariety;
import model.CaskLiquids;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {

    private static final Controller controller = new Controller();

    public static void main(String[] args) {
        initContent();
        Application.launch(GUI.class);
    }
    
    public static Controller getController() {
        return controller;
    }
    
    private static void initContent(){
        // Distillates
        var de1 = controller.createDistillate(GrainVariety.EVERGREEN, "MaltBatch1");
        controller.createDistillate(GrainVariety.STAIRWAY, "MaltBatch2");

        // Distillations
        var d1 = controller.createDistillationAndAddToDistillate(1, LocalDate.now().minusDays(10), "Employee1", "Comment1", de1);
        controller.createDistillationAndAddToDistillate(2, LocalDate.now().minusDays(5), "Employee2", "Comment2", de1);
        controller.endDistillation(d1, LocalDate.now(), 50.0, 45.0, "Finished distillation");

        // Casks
        controller.createCask(1, 100, new ArrayList<>(), "Denmark", "Supplier1");
        
        ArrayList<CaskLiquids> usedLiquids = new ArrayList<>();
        usedLiquids.add(CaskLiquids.BOURBON);
        controller.createCask(2, 200, usedLiquids, "Scotland", "Supplier2");
        
        // Warehouse
        controller.createWarehouse("Address1", 100, 10);
        controller.createWarehouse("Address2", 200, 5);
    }
}
