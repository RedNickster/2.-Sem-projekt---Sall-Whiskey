package gui;

import controller.*;

import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.Date;

public class WarehouseControlPane extends GridPane {

    private final Controller controller;

    private final ListView<Warehouse> lvwWarehouses = new ListView<>();
    private final ListView<Cask> lvwCasksInWarehouse = new ListView<>();

    private final DatePicker datePicker = new DatePicker();

    private final TextField txfAlcoholpercent = new TextField();
    private final TextField txfTasteComment = new TextField();

    private final Button btnControl = new Button("Register control");

    public WarehouseControlPane(Controller controller) {
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

        //TODO listener in lvw for the warehouse

        // Right
        VBox rightSection = new VBox(5);
        rightSection.getChildren().addAll(
                new Label("Control"),
                new Separator(),
                new Label("Date"), datePicker,
                new Label("Alcoholpercent"), txfAlcoholpercent,
                new Label("Taste"), txfTasteComment,
                btnControl
        );
        this.add(rightSection,2,0);

        btnControl.setOnAction(e -> this.controlCask());
    }

    private void controlCask() {
        Cask selectedCask = lvwCasksInWarehouse.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();
        String alcoholPercentString = txfAlcoholpercent.getText().trim();
        String tasteComment = txfTasteComment.getText().trim();

        //TODO controller metode for controlCask
    }

    void refresh() {
        lvwWarehouses.getItems().setAll(controller.getStorage().getWarehouses());
        //lvwCasksInWarehouse.getItems().clear();
    }
}