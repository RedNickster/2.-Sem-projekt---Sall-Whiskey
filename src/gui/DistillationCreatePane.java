package gui;

import controller.*;

import model.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.Locale;

public class DistillationCreatePane extends GridPane {

    private final Controller controller;

    private final ListView<Distillate> lvwDistillates = new ListView<>();
    private final ListView<Distillation> lvwDistillations = new ListView<>();

    private final ComboBox<GrainVariety> cbxGrain = new ComboBox<>();

    private final DatePicker dtpStartDate = new DatePicker();

    private final TextField txfMaltBatch = new TextField();
    private final TextField txfEmployee = new TextField();
    private final TextField txfComment = new TextField();

    private final Button btnCreateDistillate = new Button("Create distillate");
    private final Button btnCreateDistillation = new Button("Create distillation");



    public DistillationCreatePane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(15);

        // Left
        VBox leftSection = new VBox(5);
        cbxGrain.getItems().setAll(GrainVariety.values());
        cbxGrain.setPrefWidth(200);
        leftSection.getChildren().addAll(
                new Label("Create distillates"),
                new Separator(),
                new Label("Grain variety"), cbxGrain,
                new Label("Malt batch"), txfMaltBatch,
                btnCreateDistillate,
                lvwDistillates
        );
        this.add(leftSection,0,0);

        btnCreateDistillate.setOnAction(event -> this.createDistillate());

        // Center
        VBox rightSection = new VBox(5);
        rightSection.getChildren().addAll(
                new Label("Create distillation"),
                new Separator(),
                new Label("Start date"), dtpStartDate,
                new Label("Comment"), txfComment,
                new Label("Employee"), txfEmployee,
                btnCreateDistillation
        );
        this.add(rightSection,1,0);

        btnCreateDistillation.setOnAction(event -> this.createDistillation());

        // Right
        VBox centerSection = new VBox(5);
        centerSection.getChildren().addAll(
                new Label("Distillations"),
                new Separator(),
                lvwDistillations
        );
        this.add(centerSection,2,0);

        lvwDistillates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Only try to get distillations if an actual object is selected
                lvwDistillations.getItems().setAll(newValue.getDistillations());
            }
        });

    }

    private void createDistillate() {
        GrainVariety grainVariety = cbxGrain.getValue();
        String maltBatch = txfMaltBatch.getText().trim();
        // TODO isSmoked boolean relevant?

        if (grainVariety == null || maltBatch.isEmpty()) {
            AppAlerts.showError("Missing information", "Please fill out all information");
            return;
        }

        boolean confirm = AppAlerts.showConfirmation("Confirm distillate",
                "Are you sure you want to create a distillate?");


        if (confirm) {
            controller.createDistillate(grainVariety, maltBatch);


            AppAlerts.showInformation("Success", "Created distillate #" +
                    (controller.getStorage().getDistillates().size()));
        }

        refresh();
    }

    private void createDistillation() {
            LocalDate startDate = dtpStartDate.getValue();
            String comment = txfComment.getText().trim();
            String employee = txfEmployee.getText().trim();
            Distillate selectedDistillate = lvwDistillates.getSelectionModel().getSelectedItem();

            if (startDate == null || employee.isEmpty() || selectedDistillate == null) {
                AppAlerts.showError("Missing information", "Please fill out all information");
                return;
            }

            boolean confirm = AppAlerts.showConfirmation("Confirm distillation",
                    "Are you sure you want to start a distillation?");

            if (confirm) {
                int id = controller.getStorage().getDistillationCount() + 1;
                controller.createDistillationAndAddToDistillate(id, startDate, employee, comment, selectedDistillate);

                AppAlerts.showInformation("Success", "Created distillation #" + id +
                        " and added it to distillate #" + selectedDistillate.getNewMakeNumber());
            }
            refresh();

    }

    void refresh() {
        lvwDistillates.getItems().setAll(controller.getStorage().getDistillates());
        lvwDistillations.getItems().clear();
        // TODO kun vis dem uden en endDate
        // TODO udvid ToString

        cbxGrain.setValue(null);
        dtpStartDate.setValue(null);
        txfComment.clear();
        txfMaltBatch.clear();
        txfEmployee.clear();
    }
}
