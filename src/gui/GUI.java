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
    private WarehouseControlPane warehouseControlPane;
    private DefaultPane defaultPane;
    private CreateWhiskyPane createWhiskyPane;
    private WarehouseMoveCaskPane moveCaskPane;

    public void start(Stage stage) {
        stage.setTitle("Sall Whiskey");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        // menu venstre side
        VBox menu = new VBox(8);
        menu.setPadding(new Insets(10));

        Button btnHome = new Button("Dashboard");
        btnHome.setStyle("-fx-background-color: #d3d3d3; -fx-font-weight: bold;"); // Visual distinction

        menu.getChildren().add(0, btnHome);
        borderPane.setLeft(menu);

        // Panes
        defaultPane = new DefaultPane();
        caskPane = new CaskPane(controller);
        distillationCreatePane = new DistillationCreatePane(controller);
        distillationEndPane = new DistillationEndPane(controller);
        fillCaskPane = new FillCaskPane(controller);
        warehousePane = new WarehousePane(controller);
        warehouseControlPane = new WarehouseControlPane(controller);
        createWhiskyPane = new CreateWhiskyPane(controller);
        moveCaskPane = new WarehouseMoveCaskPane(controller);

        // Default view
        borderPane.setCenter(defaultPane);

        // menu knapper
        btnHome.setOnAction(e -> borderPane.setCenter(defaultPane));
        defaultPane.setActions(
                () -> { caskPane.refresh(); borderPane.setCenter(caskPane); },
                () -> { distillationCreatePane.refresh(); borderPane.setCenter(distillationCreatePane); },
                () -> { distillationEndPane.refresh(); borderPane.setCenter(distillationEndPane); },
                () -> { fillCaskPane.refresh(); borderPane.setCenter(fillCaskPane); },
                () -> { warehousePane.refresh(); borderPane.setCenter(warehousePane); },
                () -> { warehouseControlPane.refresh(); borderPane.setCenter(warehouseControlPane); },
                () -> { createWhiskyPane.refresh(); borderPane.setCenter(createWhiskyPane); },
                () -> { moveCaskPane.refresh(); borderPane.setCenter(moveCaskPane);}
        );

        Scene scene = new Scene(borderPane, 1000, 600);
        stage.setScene(scene);
        stage.show();


    }
}
