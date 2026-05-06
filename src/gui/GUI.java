package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {

    private final Controller controller = new Controller();

    private CaskPane caskPane;
    private DistillationCreatePane distillationCreatePane;
    private DistillationEndPane distillationEndPane;
    private FillCaskPane fillCaskPane;


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
        //Button btnFillCask = new Button("Fill cask");

        // TODO sætte knapperne i toppen
        menu.getChildren().addAll(btnCasks, btnCreateDistillation, btnEndDistillation
                );
        borderPane.setLeft(menu);

        // Panes
        caskPane = new CaskPane(controller);
        distillationCreatePane = new DistillationCreatePane(controller);
        distillationEndPane = new DistillationEndPane(controller);
        //fillCaskPane = new FillCaskPane(controller);


        // Default view
        borderPane.setCenter(caskPane);

        // menu knapper
        btnCasks.setOnAction(e -> { caskPane.refresh(); borderPane.setCenter(caskPane); });
        btnCreateDistillation.setOnAction(e -> { distillationCreatePane.refresh(); borderPane.setCenter(distillationCreatePane); });
        btnEndDistillation.setOnAction(e -> { distillationEndPane.refresh(); borderPane.setCenter(distillationEndPane); });
        //btnFillCask.setOnAction(e -> { fillCaskPane.refresh(); borderPane.setCenter(fillCaskPane); });

        Scene scene = new Scene(borderPane, 1000, 600);
        stage.setScene(scene);
        stage.show();

        // TODO lave en Alert til bekræftelse man kan kalde i alle de forskellige panes
        // TODO lave Alert til information efter oprettelse
        // TODO lave ugyldig information Alert
    }
}
