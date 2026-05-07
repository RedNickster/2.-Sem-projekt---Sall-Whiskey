package gui;

import controller.*;

import javafx.scene.text.TextBoundsType;
import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class WarehouseTapPane extends GridPane {

    private final Controller controller;

    private final ListView<Warehouse> lvwWarehouses = new ListView<>();
    private final ListView<Cask> lvwCasksInWarehouse = new ListView<>();

    private final TextField txfLitersTapped = new TextField();

    private final Button btnTapCask = new Button("Tap cask");

    public WarehouseTapPane(Controller controller) {
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
                new Label("Availabke casks"),
                new Separator(),
                new Label("Liters tapped"), txfLitersTapped,
                btnTapCask
        );
        this.add(rightSection, 2, 0);

        //btnTapCask.setOnAction((event -> this.tapCask()));
    }
    void refresh() {
        lvwWarehouses.getItems().setAll(controller.getStorage().getWarehouses());
        lvwCasksInWarehouse.getItems().clear();
    }
}
