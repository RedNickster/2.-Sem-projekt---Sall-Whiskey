package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

      //GUI-komponenter
//    private final ListView<x> x = new ListView<>();
//
//    private final DatePicker datePicker = new DatePicker();
//
//    private final TextField txfX1 = new TextField();
//    private final TextField txfX2 = new TextField();
//    private final TextField txfX3 = new TextField();
//
//    private final TextField txfX4 = new TextField();
//    private final TextField txfX5 = new TextField();
//
    private final Button btnCreateCask = new Button("Create cask");
    private final Button btnCreateDistillation = new Button("Create distillation");

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sall Whiskey");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();

        //updateLists();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(true);
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setPadding(new Insets(20,20,20,20));
        pane.setAlignment(Pos.CENTER);

        pane.add(btnCreateCask, 1, 6);
        pane.add(btnCreateDistillation, 2, 6);

        //btnCreateCask.setOnAction(event -> this.createCask);
        //btnCreateDistillation.setOnAction(event -> this.createDistillation);
    }

    private void createCask() {

    }

    private void createDistillation() {

    }

}
