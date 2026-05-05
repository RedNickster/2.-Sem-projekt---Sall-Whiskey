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
        Button btnFillCask = new Button("Fill cask");
        Button btnUdflugtsOversigt = new Button("5");
        Button btnHotelOversigt = new Button("6");
        Button btnSøgDeltager = new Button("7");
        Button btnKonfDeltagere = new Button("8");

        menu.getChildren().addAll(btnCasks, btnCreateDistillation, btnEndDistillation, btnFillCask,
                btnUdflugtsOversigt, btnHotelOversigt, btnSøgDeltager, btnKonfDeltagere);
        borderPane.setLeft(menu);

        // Panes
        caskPane = new CaskPane(controller);
        distillationCreatePane = new DistillationCreatePane(controller);
        distillationEndPane = new DistillationEndPane(controller);
        fillCaskPane = new FillCaskPane(controller);


        // Default view
        borderPane.setCenter(caskPane);

        // menu knapper
        btnCasks.setOnAction(e -> { caskPane.refresh(); borderPane.setCenter(caskPane); });
        btnCreateDistillation.setOnAction(e -> { distillationCreatePane.refresh(); borderPane.setCenter(distillationCreatePane); });
        btnEndDistillation.setOnAction(e -> { distillationEndPane.refresh(); borderPane.setCenter(distillationEndPane); });
        btnFillCask.setOnAction(e -> { fillCaskPane.refresh(); borderPane.setCenter(fillCaskPane); });
//        btnUdflugtsOversigt.setOnAction(e -> { udflugtOversigtPane.refresh(); borderPane.setCenter(udflugtOversigtPane); });
//        btnHotelOversigt.setOnAction(e -> { hotelOversigtPane.refresh(); borderPane.setCenter(hotelOversigtPane); });
//        btnSøgDeltager.setOnAction(e -> { participantSearchPane.refresh(); borderPane.setCenter(participantSearchPane); });
//        btnKonfDeltagere.setOnAction(e -> { conferenceParticipantsPane.refresh(); borderPane.setCenter(conferenceParticipantsPane); });

        Scene scene = new Scene(borderPane, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
