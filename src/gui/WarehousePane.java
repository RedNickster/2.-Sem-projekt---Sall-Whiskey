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

        btnRemoveFromWarehouse.setOnAction(event -> this.removeFromWarehouse());

        // Right
        VBox rightSection = new VBox(5);
        rightSection.getChildren().addAll(
                new Label("Available casks"),
                new Separator(),
                lvwCasksAvailable,
                btnAddToWarehouse
        );
        this.add(rightSection, 2, 0);

        btnAddToWarehouse.setOnAction((event -> this.addToWarehouse()));

        //TODO tilføje listeners og tilhørende metoder for at vise cask ikke i lager i available, og cask i lager i anden
    }

    private void addToWarehouse() {
        Cask selectedCask = lvwCasksAvailable.getSelectionModel().getSelectedItem();
        Warehouse selectedWarehouse = lvwWarehouses.getSelectionModel().getSelectedItem();

        if (selectedCask == null || selectedWarehouse == null) {
            AppAlerts.showError("Missing information", "Please fill out all information");
            return;
        }

        boolean confirm = AppAlerts.showConfirmation("Confirm adding cask",
                "Are you sure you want to add the cask?");

        if (confirm) {
            controller.addCaskToWarehouse(selectedCask, selectedWarehouse);

            AppAlerts.showInformation("Success", "Added cask #" + selectedCask.getId() + " to "
                    + "warehouse");
        }
        refresh();
    }

    private void removeFromWarehouse() {
        Cask selectedCask = lvwCasksInWarehouse.getSelectionModel().getSelectedItem();
        Warehouse selectedWarehouse = lvwWarehouses.getSelectionModel().getSelectedItem();

        if (selectedCask == null || selectedWarehouse == null) {
            AppAlerts.showError("Missing information", "Please fill out all information");
            return;
        }

        boolean confirm = AppAlerts.showConfirmation("Confirm removing cask",
                "Are you sure you want to remove the cask?");

        if (confirm) {

            controller.removeCaskFromWarehouse(selectedCask, selectedWarehouse);

            AppAlerts.showInformation("Success", "Removed cask #" + selectedCask.getId() + " from "
                    + "warehouse");
        }
        refresh();
    }


    void refresh() {
        lvwWarehouses.getItems().setAll(controller.getStorage().getWarehouses());
        lvwCasksAvailable.getItems().setAll(controller.getStorage().getCasks());
        //lvwCasksInWarehouse.getItems().clear();
        //lvwCasksAvailable.getItems().clear();
    }
}


