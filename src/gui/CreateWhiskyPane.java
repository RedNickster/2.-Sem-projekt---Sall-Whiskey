package gui;

import controller.*;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class CreateWhiskyPane extends GridPane {

    private final Controller controller;

    private final Button btnDilute = new Button("Dilute");
    private final Button btnCreateWhisky = new Button("Create Whisky");


    public CreateWhiskyPane(Controller controller) {
        this.controller = controller;


        // Left
        VBox leftSection = new VBox(5);
        leftSection.getChildren().addAll(
                new Label(""),
                new Separator()
        );
        this.add(leftSection,1,0);

        // Center
        VBox centerSection = new VBox(5);
        centerSection.getChildren().addAll(
                new Label(""),
                new Separator()
        );
        this.add(centerSection,2,0);

        // Right
        VBox rightSection = new VBox(5);
        centerSection.getChildren().addAll(
                new Label(""),
                new Separator(),
                btnDilute,
                btnCreateWhisky
        );
        this.add(rightSection,3,0);

        btnDilute.setOnAction(e -> this.openDiluteWindow());
        btnCreateWhisky.setOnAction(e -> this.createWhisky());
    }

    private void openDiluteWindow() {
            // Open a new Window (Stage)
            Stage stage = new Stage();
            stage.setTitle("Dilute Whisky");
            stage.setScene(new Scene(new DilutePane(controller, stage), 300, 200));
            stage.initModality(Modality.APPLICATION_MODAL); // Blocks main window
            stage.show();

    }

    private void createWhisky() {
        openFlaskWindow();
    }

    private void openFlaskWindow() {
            // Logic to transition to the flasking step
            Stage stage = new Stage();
            stage.setTitle("Put on flask");
            stage.setScene(new Scene(new PutWhiskyInFlaskPane(controller), 400, 300));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
    }

    void refresh() {

    }
}


