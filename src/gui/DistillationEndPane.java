package gui;

import controller.*;

import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;


public class DistillationEndPane extends GridPane{

    private final Controller controller;

    private final ListView<Distillate> lvwDistillates = new ListView<>();
    private final ListView<Distillation> lvwDistillations = new ListView<>();

    private final DatePicker dtpEndDate = new DatePicker();

    private final TextField txfLiquidAmount = new TextField();
    private final TextField txfAlchoholPercent = new TextField();
    private final TextField txfComment = new TextField();

    private final Button btnEndDistillation = new Button("End distillation");



    public DistillationEndPane(Controller controller) {
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
                new Label("Active distillations"),
                new Separator(),
                lvwDistillations
        );
        this.add(centerSection,1,0);

        lvwDistillates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Only try to get distillations if an actual object is selected
                lvwDistillations.getItems().setAll(controller.getActiveDistillations(newValue));
            }
        });

        // Right
        VBox rightSection = new VBox(5);
        rightSection.getChildren().addAll(
                new Label("End distillation"),
                new Separator(),
                new Label("End date"), dtpEndDate,
                new Label("Liquid amount"), txfLiquidAmount,
                new Label("Alcohol percentage"), txfAlchoholPercent,
                new Label("Comment"), txfComment,
                btnEndDistillation
        );
        this.add(rightSection,2,0);

        btnEndDistillation.setOnAction((event -> this.endDistillation()));

    }
    private void endDistillation() {
        //TODO tekstuel fejl ved enddate før startdate, bogstaver ved int osv
        Distillation selectedDistillation = lvwDistillations.getSelectionModel().getSelectedItem();
        LocalDate endDate = dtpEndDate.getValue();
        String liquidAmountString = txfLiquidAmount.getText().trim();
        String alcoholPercentString = txfAlchoholPercent.getText().trim();
        String comment = txfComment.getText().trim();


        if (selectedDistillation == null || endDate == null|| liquidAmountString.isEmpty() ||
                        alcoholPercentString.isEmpty() || comment.isEmpty()) {
            AppAlerts.showError("Missing information", "Please fill out all information");
            return;
        }

        boolean confirm = AppAlerts.showConfirmation("Confirm ending distillation",
                "Are you sure you want to end the distillation?");

        if (confirm) {
            int liquidAmount = Integer.parseInt(liquidAmountString);
            double alcoholPercent = Double.parseDouble(alcoholPercentString);

            controller.endDistillation(selectedDistillation,endDate,liquidAmount,alcoholPercent,comment);

            AppAlerts.showInformation("Success", "Ended distillation #" + selectedDistillation.getId());
        }
        refresh();

    }

    void refresh() {
        lvwDistillates.getItems().setAll(controller.getStorage().getDistillates());
        lvwDistillations.getItems().clear();

        dtpEndDate.setValue(null);
        txfAlchoholPercent.clear();
        txfComment.clear();
        txfLiquidAmount.clear();
    }
}
