package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.*;

import controller.*;
import model.*;
import storage.*;

import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

      Controller controller = new Controller();

      //GUI-komponenter
      private final ListView<Cask> lvwCasks = new ListView<>();

      private final CheckComboBox<CaskLiquids> cbxCaskLiquids = new CheckComboBox();

      private final TextField txfXLiterSpace = new TextField();
      private final TextField txfXCountryOfOrigin = new TextField();
      private final TextField txfXSupplier = new TextField();
      private final TextField txfCaskAmount = new TextField();

    private final Button btnCreateCask = new Button("Create cask");

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
        pane.setGridLinesVisible(false);
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setPadding(new Insets(20,20,20,20));
        pane.setAlignment(Pos.CENTER);

        cbxCaskLiquids.getItems().setAll(CaskLiquids.values());
        cbxCaskLiquids.setPrefWidth(200);

        lvwCasks.setPrefSize(250,150);

        Label lblCasks = new Label("Casks");
        Label lblCaskLiterSpace = new Label("Litre");
        Label lblCaskLiquid = new Label("Cask liquid");
        Label lblCountryOfOrigin = new Label("Coutry of origin");
        Label lblSupplier = new Label("Supplier");
        Label lblCaskAmount = new Label("Amount");

        pane.add(lblCasks, 0, 0);
        pane.add(lvwCasks,0,1);

        pane.add(lblCaskLiterSpace,0,2);
        pane.add(txfXLiterSpace,0,3);
        pane.add(lblCaskLiquid,0,4);
        pane.add(cbxCaskLiquids,0,5);
        pane.add(lblCountryOfOrigin,0,6);
        pane.add(txfXCountryOfOrigin,0,7);
        pane.add(lblSupplier,0,8);
        pane.add(txfXSupplier,0,9);
        pane.add(lblCaskAmount,0,10);
        pane.add(txfCaskAmount,0,11);


        pane.add(btnCreateCask, 0, 12);

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

                updateLists();

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

        txfXLiterSpace.clear();
        txfXCountryOfOrigin.clear();
        txfXSupplier.clear();
        txfCaskAmount.clear();
        cbxCaskLiquids.getCheckModel().clearChecks();
    }

    private void updateLists() {
        lvwCasks.getItems().setAll(controller.getStorage().getCasks());
    }

}
