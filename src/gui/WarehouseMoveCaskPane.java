package gui;

import controller.*;

import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.Date;

public class WarehouseMoveCaskPane extends GridPane {

    private final Controller controller;

    private final ListView<Warehouse> lvwWarehouses = new ListView<>();
    private final ListView<Cask> lvwCasksInWarehouse = new ListView<>();
    private final ListView<String> lvwStorageLocations = new ListView<>();

    private final Button btnmMoveCask = new Button("Move cask");

    public WarehouseMoveCaskPane(Controller controller) {
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
                lvwCasksInWarehouse
        );
        this.add(centerSection, 1, 0);

        // Right
        VBox rightSection = new VBox(5);
        rightSection.getChildren().addAll(
                new Label("Locations"),
                new Separator(),
                lvwStorageLocations,
                btnmMoveCask

        );
        this.add(rightSection,2,0);

        btnmMoveCask.setOnAction(e -> this.moveCask());

        lvwWarehouses.getSelectionModel().selectedItemProperty().addListener((obs, oldW, newW) -> {
            lvwCasksInWarehouse.getItems().clear();
            lvwStorageLocations.getItems().clear();

            lvwCasksInWarehouse.getItems().setAll(controller.getCasksInWarehouse(newW));

            Cask[] slots = controller.getAllLocationsInWarehouse(newW);

            for (int i = 0; i < slots.length; i++) {
                Cask cask = slots[i];
                int slotNum = i + 1;

                if (cask == null) {
                    lvwStorageLocations.getItems().add("Slot " + slotNum + ": [ Available Space ]");
                } else {
                    lvwStorageLocations.getItems().add("Slot " + slotNum + ": Cask #" + cask.getId());
                }
            }
        });

    }

    private void moveCask() {
        Warehouse selectedWarehouse = lvwWarehouses.getSelectionModel().getSelectedItem();
        Cask selectedCask = lvwCasksInWarehouse.getSelectionModel().getSelectedItem();
        int selectedLocation = lvwStorageLocations.getSelectionModel().getSelectedIndex();

        if (selectedWarehouse == null || selectedCask == null || selectedLocation == -1) {
            AppAlerts.showError("Missing information", "Please fill out all information");
            return;
        }

        boolean confirm = AppAlerts.showConfirmation("Confirm move",
                "Are you sure you want to move the cask?");

        if (confirm) {
            controller.moveCaskInWarehouse(selectedCask, selectedWarehouse, selectedLocation);

            AppAlerts.showInformation("Success", "Moved cask #" + selectedCask.getId());
        }
        refresh();
    }

    void refresh() {
        lvwWarehouses.getItems().setAll(controller.getWarehouses());
    }
}