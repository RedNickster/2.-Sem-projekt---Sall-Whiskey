package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DefaultPane extends GridPane {

    private final Button btnCasks = new Button("Create casks");
    private final Button btnCreateDistillation = new Button("Create distillation");
    private final Button btnEndDistillation = new Button("End distillation");
    private final Button btnFillCask = new Button("Fill cask");
    private final Button btnWarehouse = new Button("Warehouse");
    private final Button btnWarehouseControl = new Button("WarehouseControl");
    private final Button btnCreateWhisky = new Button("Create whisky");
    private final Button btnMoveCask = new Button("Move cask");

    public DefaultPane() {
        this.setPadding(new Insets(20));
        this.setHgap(100);
        this.setVgap(15);

        // Left cask/warehouse
        VBox leftSection = new VBox(10);
        leftSection.getChildren().addAll(
                new Label("Cask/Warehouse"),
                new Separator(),
                btnCasks,
                btnFillCask,
                btnWarehouse,
                btnWarehouseControl,
                btnMoveCask
        );
        this.add(leftSection, 1, 0);

        // Center distillate/distillation
        VBox centerSection = new VBox(10);
        centerSection.getChildren().addAll(
                new Label("Distillate/Distillation"),
                new Separator(),
                btnCreateDistillation,
                btnEndDistillation
        );
        this.add(centerSection, 2, 0);

        // Right whiskey/bottle
        VBox rightSection = new VBox(10);
        rightSection.getChildren().addAll(
                new Label("Whiskey/Bottle"),
                btnCreateWhisky
        );
        this.add(rightSection, 3, 0);

    }

    public void setActions(Runnable caskAct, Runnable createDistAct, Runnable endDistAct,
                           Runnable fillAct, Runnable warehouseAct, Runnable warehouseControlAct, Runnable createWhisAct,
                           Runnable moveCaskAct) {
        btnCasks.setOnAction(e -> caskAct.run());
        btnCreateDistillation.setOnAction(e -> createDistAct.run());
        btnEndDistillation.setOnAction(e -> endDistAct.run());
        btnFillCask.setOnAction(e -> fillAct.run());
        btnWarehouse.setOnAction(e -> warehouseAct.run());
        btnWarehouseControl.setOnAction(e -> warehouseControlAct.run());
        btnCreateWhisky.setOnAction(e -> createWhisAct.run());
        btnMoveCask.setOnAction(e -> moveCaskAct.run());
    }

    private void styleButtons() {
        // Just an example to make them look more like a dashboard
        for (Button b : new Button[]{btnCasks, btnCreateDistillation, btnEndDistillation,
                btnFillCask, btnWarehouse, btnWarehouseControl, btnCreateWhisky, btnMoveCask}) {
            b.setPrefWidth(200);
            b.setPrefHeight(40);
        }
    }
}
