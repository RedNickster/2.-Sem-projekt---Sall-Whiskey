package gui;

import controller.*;

import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class FillCaskPane extends GridPane {

    private final Controller controller;

    private final ListView<Cask> lvwCasks = new ListView<>();
    private final ListView<Distillate> lvwDistillates = new ListView<>();

    private final TextField txfAmount = new TextField();

    private final Button btnFillCask = new Button();

    public FillCaskPane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(15);

        // Left
        VBox leftSection = new VBox(5);
        leftSection.getChildren().addAll(
                new Label("Distillates"),
                new Separator(),
                lvwDistillates
        );
        this.add(leftSection,0,0);
        // Center
        VBox centerSection = new VBox(5);
        centerSection.getChildren().addAll(
                new Label("Casks"),
                new Separator(),
                lvwCasks
        );
        this.add(centerSection,1,0);
        // Right
        VBox rightSection = new VBox(5);
        rightSection.getChildren().addAll(
                new Label("Fill cask"),
                new Separator(),
                new Label("Liquid amount"), txfAmount

        );
    }

    private void fillCasks() {

    }

    void refresh() {

    }
}
