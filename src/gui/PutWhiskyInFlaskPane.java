package gui;

import controller.*;

import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.enums.GrainVariety;

import java.time.LocalDate;

public class PutWhiskyInFlaskPane extends GridPane {
    private final Controller controller;

    public PutWhiskyInFlaskPane(Controller controller) {
        this.controller = controller;

    }
}
