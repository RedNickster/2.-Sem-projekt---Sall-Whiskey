package gui;

import controller.Controller;

import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Cask;
import model.CaskLiquids;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;

import java.util.ArrayList;
import java.util.List;


public class CaskPane extends GridPane {

    Controller controller = new Controller();

    private final ListView<Cask> lvwCasks = new ListView<>();

    private final CheckComboBox<CaskLiquids> cbxCaskLiquids = new CheckComboBox();

    private final TextField txfXLiterSpace = new TextField();
    private final TextField txfXCountryOfOrigin = new TextField();
    private final TextField txfXSupplier = new TextField();
    private final TextField txfCaskAmount = new TextField();

    private final Button btnCreateCask = new Button("Create cask");


    public CaskPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(15);

        // Left
        VBox leftSection = new VBox(10);
        leftSection.getChildren().addAll(new Label("Casks"), lvwCasks);
        lvwCasks.getItems().setAll(controller.getStorage().getCasks());
        lvwCasks.setPrefWidth(450);
        VBox.setVgrow(lvwCasks, Priority.ALWAYS);
        this.add(leftSection, 0,0);

        // Right
        VBox rightSection = new VBox(5);

        cbxCaskLiquids.getItems().setAll(CaskLiquids.values());
        cbxCaskLiquids.setPrefWidth(200);

        rightSection.getChildren().addAll(
                new Label("Register cask"),
                new Separator(), // Optional: adds a nice line under the title
                new Label("Liter"), txfXLiterSpace,
                new Label("CaskLiquid"), cbxCaskLiquids,
                new Label("CountryOfOrigin"), txfXCountryOfOrigin,
                new Label("Supplier"), txfXSupplier,
                new Label("CaskAmount"), txfCaskAmount,
                btnCreateCask
        );
        this.add(rightSection,1,0);

        btnCreateCask.setOnAction(event -> this.createCask());

    }

    private void createCask() {
        try {
            int caskAmount = Integer.parseInt(txfCaskAmount.getText().trim());
            int startId = controller.getCaskCount() + 1;
            int lastId = startId + caskAmount - 1;
            int liters = Integer.parseInt(txfXLiterSpace.getText().trim());

            List<CaskLiquids> selectedLiquids = cbxCaskLiquids.getCheckModel().getCheckedItems();
            ArrayList<CaskLiquids> liquidsList = new ArrayList<>(selectedLiquids);

            String countryOfOrigin = txfXCountryOfOrigin.getText();
            String supplier = txfXSupplier.getText();

            if (!countryOfOrigin.isEmpty() && !supplier.isEmpty()) {
                int currentId = startId;
                for (int index = 0; index < caskAmount; index++) {
                    controller.createCask(currentId, liters, liquidsList, countryOfOrigin, supplier);
                    currentId++;
                }

                refresh();

                // --- SHOW POPUP ---
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Casks Created");
                alert.setHeaderText(null);

                if (caskAmount == 1) {
                    alert.setContentText("Successfully created Cask #" + startId);
                } else {
                    alert.setContentText("Successfully created " + caskAmount + " casks.\n" +
                            "IDs: " + startId + " through " + lastId);
                }
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            // Error popup if input isn't a number
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Please enter valid numbers for Litre and Amount.");
            error.showAndWait();
        }
    }


    void refresh() {
        lvwCasks.getItems().setAll(controller.getStorage().getCasks());
        txfCaskAmount.clear();
        txfXCountryOfOrigin.clear();
        txfXLiterSpace.clear();
        txfXSupplier.clear();
        cbxCaskLiquids.getCheckModel().clearChecks();
    }
}
