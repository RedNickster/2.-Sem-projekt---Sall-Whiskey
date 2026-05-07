package gui;

import controller.*;

import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class WarehousePane extends GridPane {

    private final Controller controller;

    private final ListView<Warehouse> lvwWarehouses = new ListView<>();
    private final ListView<Cask> lvwCasksInWarehouse= new ListView<>();
    private final ListView<Cask> lvwCasksAvailable = new ListView<>();

    private final Button btnAddToWarehouse = new Button("Add");
    private final Button btnRemoveFromWarehouse = new Button("Remove");

    public WarehousePane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(15);

        // Left
        VBox leftSection = new VBox(5);
        leftSection.getChildren().addAll(
                new Label("Warehouses"),
                new Separator(),
                lvwWarehouses
        );
        this.add(leftSection, 0, 0);
        // Center
        VBox centerSection = new VBox(5);
        centerSection.getChildren().addAll(
                new Label("Casks in warehouse"),
                new Separator(),
                lvwCasksInWarehouse,
                btnRemoveFromWarehouse
        );
        this.add(centerSection, 1, 0);

        //btnRemoveFromWarehouse.setOnAction(e -> this.removeFromWarehouse);

        // Right
        VBox rightSection = new VBox(5);
        rightSection.getChildren().addAll(
                new Label("Available casks"),
                new Separator(),
                lvwCasksAvailable,
                btnAddToWarehouse
        );
        this.add(rightSection, 2, 0);

        //btnAddToWarehouse.setOnAction((event -> this.addToWarehouse()));
    }
    void refresh() {
        lvwWarehouses.getItems().setAll(controller.getStorage().getWarehouses());
        lvwCasksAvailable.getItems().setAll(controller.getStorage().getCasks());
        //lvwCasksInWarehouse.getItems().clear();
        //lvwCasksAvailable.getItems().clear();
    }
}


