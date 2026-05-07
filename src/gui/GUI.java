package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {

    private final Controller controller = App.getController();

    private CaskPane caskPane;
    private DistillationCreatePane distillationCreatePane;
    private DistillationEndPane distillationEndPane;
    private FillCaskPane fillCaskPane;
    private WarehousePane warehousePane;
    private WarehouseTapPane warehouseTapPane;

    public void start(Stage stage) {
        stage.setTitle("Sall Whiskey");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        // menu venstre side
        VBox menu = new VBox(8);
        menu.setPadding(new Insets(10));

        Button btnCasks = new Button("Casks");
        Button btnCreateDistillation = new Button("Create distillation");
        Button btnEndDistillation = new Button("End distillation");
        Button btnFillCask = new Button("Fill cask");
        Button btnWarehouse = new Button("Warehouse");
        Button btnWarehouseTao = new Button("WarehouseTap");

        // TODO sætte knapperne i toppen
        menu.getChildren().addAll(btnCasks, btnCreateDistillation, btnEndDistillation, btnFillCask, btnWarehouse, btnWarehouseTao
                );
        borderPane.setLeft(menu);

        // Panes
        caskPane = new CaskPane(controller);
        distillationCreatePane = new DistillationCreatePane(controller);
        distillationEndPane = new DistillationEndPane(controller);
        fillCaskPane = new FillCaskPane(controller);
        warehousePane = new WarehousePane(controller);
        warehouseTapPane = new WarehouseTapPane(controller);

        // Default view
        borderPane.setCenter(caskPane);

        // menu knapper
        btnCasks.setOnAction(e -> { caskPane.refresh(); borderPane.setCenter(caskPane); });
        btnCreateDistillation.setOnAction(e -> { distillationCreatePane.refresh(); borderPane.setCenter(distillationCreatePane); });
        btnEndDistillation.setOnAction(e -> { distillationEndPane.refresh(); borderPane.setCenter(distillationEndPane); });
        btnFillCask.setOnAction(e -> { fillCaskPane.refresh(); borderPane.setCenter(fillCaskPane); });
        btnWarehouse.setOnAction(e -> {warehousePane.refresh(); borderPane.setCenter(warehousePane);});
        btnWarehouseTao.setOnAction(e -> {warehouseTapPane.refresh(); borderPane.setCenter(warehouseTapPane);});

        Scene scene = new Scene(borderPane, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
