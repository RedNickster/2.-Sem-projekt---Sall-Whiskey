package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {

    private CaskPane caskPane;
    private DistillationCreatePane distillationCreatePane;


    public void start(Stage stage) {
        stage.setTitle("Sall Whiskey");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        // menu venstre side
        VBox menu = new VBox(8);
        menu.setPadding(new Insets(10));

        Button btnCasks = new Button("Casks");
        Button btnDistillation = new Button("Distillation");
        Button btnTilmelding = new Button("3");
        Button btnLedsager = new Button("4");
        Button btnUdflugtsOversigt = new Button("5");
        Button btnHotelOversigt = new Button("6");
        Button btnSøgDeltager = new Button("7");
        Button btnKonfDeltagere = new Button("8");

        menu.getChildren().addAll(btnCasks, btnDistillation, btnTilmelding, btnLedsager,
                btnUdflugtsOversigt, btnHotelOversigt, btnSøgDeltager, btnKonfDeltagere);
        borderPane.setLeft(menu);

        // Panes
        caskPane = new CaskPane();
        distillationCreatePane = new DistillationCreatePane();

        // Default view
        borderPane.setCenter(caskPane);

        // menu knapper
        btnCasks.setOnAction(e -> { caskPane.refresh(); borderPane.setCenter(caskPane); });
        btnDistillation.setOnAction(e -> { distillationCreatePane.refresh(); borderPane.setCenter(distillationCreatePane); });
//        btnTilmelding.setOnAction(e -> { tilmeldingPane.refreshAll(); borderPane.setCenter(tilmeldingPane); });
//        btnLedsager.setOnAction(e -> { ledsagerPane.refresh(); borderPane.setCenter(ledsagerPane); });
//        btnUdflugtsOversigt.setOnAction(e -> { udflugtOversigtPane.refresh(); borderPane.setCenter(udflugtOversigtPane); });
//        btnHotelOversigt.setOnAction(e -> { hotelOversigtPane.refresh(); borderPane.setCenter(hotelOversigtPane); });
//        btnSøgDeltager.setOnAction(e -> { participantSearchPane.refresh(); borderPane.setCenter(participantSearchPane); });
//        btnKonfDeltagere.setOnAction(e -> { conferenceParticipantsPane.refresh(); borderPane.setCenter(conferenceParticipantsPane); });

        Scene scene = new Scene(borderPane, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
