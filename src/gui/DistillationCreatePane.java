package gui;

import controller.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import model.*;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class DistillationCreatePane extends GridPane {

    Controller controller = new Controller();

    private final ListView<Distillate> lvwDistillates = new ListView<>();
    private final ListView<Distillation> lvwDistillations = new ListView<>();

    private final ComboBox<GrainVariety> cbxGrain = new ComboBox<>();
    private final ComboBox<SmokingMaterial> cbxSmoke = new ComboBox<>();

    private final DatePicker dtpStartDate = new DatePicker();

    private final TextField txfMaltBatch = new TextField();
    private final TextField txfEmployee = new TextField();

    private final Button btnCreateDistillate = new Button("Create distillate");
    private final Button btnCreateDistillation = new Button("Create distillation");



    public DistillationCreatePane() {
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
                btnCreateDistillate,
                lvwDistillates
        );
        this.add(leftSection,0,0);

        btnCreateDistillate.setOnAction(event -> this.createDistillate());

        // Center
        VBox centerSection = new VBox(5);
        cbxSmoke.getItems().setAll(SmokingMaterial.values());
        cbxSmoke.setPrefWidth(200);
        centerSection.getChildren().addAll(
                new Label("Create distillation"),
                new Separator(),
                new Label(""), dtpStartDate,
                new Label(""), cbxSmoke,
                new Label("Malt batch"), txfMaltBatch,
                new Label("Employee"), txfEmployee,
                btnCreateDistillation
        );
        this.add(centerSection,1,0);

        btnCreateDistillation.setOnAction(event -> this.createDistillation());

        // Right
        VBox rightSection = new VBox(5);
        rightSection.getChildren().addAll(
                new Label("Distillations"),
                new Separator(),
                lvwDistillations
        );
        this.add(rightSection,2,0);

        lvwDistillates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Only try to get distillations if an actual object is selected
                lvwDistillations.getItems().setAll(newValue.getDistillations());
            } else {
                // If nothing is selected (e.g., after a refresh), clear the right list
                lvwDistillations.getItems().clear();
            }
        });
    }

    private void createDistillate() {
        GrainVariety grainVariety = cbxGrain.getValue();
        int newmakeNumber = controller.getStorage().getDistillateCount() + 1;
        if (grainVariety != null) {
            controller.createDistilitate(newmakeNumber, grainVariety);
        }
        refresh();
    }

    private void createDistillation(){
        try {
            Distillate selectedDestillate = lvwDistillates.getSelectionModel().getSelectedItem();
            LocalDate startDate = dtpStartDate.getValue();
            SmokingMaterial smoke = cbxSmoke.getValue();
            String maltBatchString = txfMaltBatch.getText().trim();
            String employee = txfEmployee.getText().trim();

            if(selectedDestillate != null && startDate != null && smoke != null && !maltBatchString.isEmpty() && !employee.isEmpty()) {
                int maltchBatch = Integer.parseInt(maltBatchString);
                controller.createDistillation(selectedDestillate, startDate, smoke, maltchBatch, employee);
            }
            refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setContentText("Distillation created");
            alert.setHeaderText(null);
            alert.showAndWait();

        } catch (NumberFormatException e) {
            // Error popup if input isn't a number
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Please enter valid info for malt batch and employee.");
            error.showAndWait();
        }

    }

    void refresh() {
        lvwDistillates.getItems().setAll(controller.getStorage().getDistillates());
        lvwDistillations.getItems().clear();

        cbxGrain.setValue(null);
        dtpStartDate.setValue(null);
        cbxSmoke.setValue(null);
        txfMaltBatch.clear();
        txfEmployee.clear();
    }
}
