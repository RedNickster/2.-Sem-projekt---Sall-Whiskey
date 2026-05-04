package gui;

import controller.*;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import model.*;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class DistillationCreatePane extends GridPane {

    Controller controller = new Controller();

    private final ListView<Distillate> lvwDistillates = new ListView<>();

    private final ComboBox<GrainVariety> cbxGrain = new ComboBox<>();

    private final Button btnCreateDistillate = new Button("Create distillate");

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


        // Right

    }

    private void createDistillate() {
        GrainVariety grainVariety = cbxGrain.getValue();
        int newmakeNumber = controller.getStorage().getDistillateCount() + 1;
        if (grainVariety != null) {
            controller.createDistilitate(newmakeNumber, grainVariety);
        }
        refresh();
    }

    void refresh() {
        lvwDistillates.getItems().setAll(controller.getStorage().getDistillates());
    }
}
