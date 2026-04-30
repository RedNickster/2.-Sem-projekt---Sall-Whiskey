package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import model.Cask;
import model.CaskLiquids;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GUI extends Application {

    Controller controller = new Controller();

    // GUI components
    private final ListView<Cask> lvwCasks = new ListView<>();
    private final ComboBox<ObservableList<CaskLiquids>> cbxCaskLiquids = new ComboBox<>();
    private final TextField txfXLiterSpace = new TextField();
    private final TextField txfXCountryOfOrigin = new TextField();
    private final TextField txfXSupplier = new TextField();
    private final TextField txfCaskAmount = new TextField();
    private final Button btnCreateCask = new Button("Create cask");

    // ListView for the popup
    private final ListView<CaskLiquids> listView = new ListView<>();
    private final Popup popup = new Popup();

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sall Whiskey");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setAlignment(Pos.CENTER);

        // Initialize the ListView with all CaskLiquids
        listView.setItems(FXCollections.observableArrayList(CaskLiquids.values()));
        listView.setCellFactory(lv -> new CheckBoxListCell());

        // Configure the ListView for multi-selection
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Set the ComboBox button cell to display selected items
        cbxCaskLiquids.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(ObservableList<CaskLiquids> items, boolean empty) {
                super.updateItem(items, empty);
                if (empty || items == null) {
                    setText("Select liquids...");
                } else {
                    setText(items.stream()
                            .map(CaskLiquids::name)
                            .collect(Collectors.joining(", ")));
                }
            }
        });

        // Set the ComboBox cell factory to display selected items in the dropdown
        cbxCaskLiquids.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(ObservableList<CaskLiquids> items, boolean empty) {
                super.updateItem(items, empty);
                if (empty || items == null) {
                    setText(null);
                } else {
                    setText(items.stream()
                            .map(CaskLiquids::name)
                            .collect(Collectors.joining(", ")));
                }
            }
        });

        // Initialize the ComboBox value as an empty list
        cbxCaskLiquids.setValue(FXCollections.observableArrayList());

        // Show the popup when the ComboBox is clicked
        cbxCaskLiquids.setOnMouseClicked(event -> {
            if (popup.isShowing()) {
                popup.hide();
            } else {
                // Update the ListView's selected items to match the ComboBox value
                listView.getSelectionModel().clearSelection();
                ObservableList<CaskLiquids> selectedItems = cbxCaskLiquids.getValue();
                if (selectedItems != null) {
                    for (CaskLiquids item : selectedItems) {
                        listView.getSelectionModel().select(item);
                    }
                }

                // Position the popup below the ComboBox
                popup.setAnchorLocation(PopupWindow.AnchorLocation.CONTENT_BOTTOM_LEFT);
                popup.getContent().setAll(listView);
                popup.setAutoHide(true);
                popup.show(cbxCaskLiquids.getScene().getWindow());
            }
        });

        // Handle selection changes in the ListView
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            // Update the ComboBox value with the currently selected items
            cbxCaskLiquids.setValue(FXCollections.observableArrayList(listView.getSelectionModel().getSelectedItems()));
        });

        // Hide the popup when clicking outside
        popup.setOnAutoHide(event -> {
            cbxCaskLiquids.setValue(FXCollections.observableArrayList(listView.getSelectionModel().getSelectedItems()));
        });

        lvwCasks.setPrefSize(250, 150);

        Label lblCasks = new Label("Casks");
        Label lblCaskLiterSpace = new Label("Litre");
        Label lblCaskLiquid = new Label("Cask liquid");
        Label lblCountryOfOrigin = new Label("Country of origin");
        Label lblSupplier = new Label("Supplier");
        Label lblCaskAmount = new Label("Amount");

        pane.add(lblCasks, 0, 0);
        pane.add(lvwCasks, 0, 1);
        pane.add(lblCaskLiterSpace, 0, 2);
        pane.add(txfXLiterSpace, 0, 3);
        pane.add(lblCaskLiquid, 0, 4);
        pane.add(cbxCaskLiquids, 0, 5);
        pane.add(lblCountryOfOrigin, 0, 6);
        pane.add(txfXCountryOfOrigin, 0, 7);
        pane.add(lblSupplier, 0, 8);
        pane.add(txfXSupplier, 0, 9);
        pane.add(lblCaskAmount, 0, 10);
        pane.add(txfCaskAmount, 0, 11);
        pane.add(btnCreateCask, 0, 12);

        btnCreateCask.setOnAction(event -> this.createCask());
    }

    // Custom ListCell with CheckBox
    private static class CheckBoxListCell extends ListCell<CaskLiquids> {
        private final CheckBox checkBox = new CheckBox();

        @Override
        protected void updateItem(CaskLiquids item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                checkBox.setText(item.name());
                setGraphic(checkBox);
            }
        }
    }

    private void createCask() {
        try {
            int caskAmount = Integer.parseInt(txfCaskAmount.getText().trim());
            int startId = controller.getCaskCount() + 1;
            int lastId = startId + caskAmount - 1;
            int liters = Integer.parseInt(txfXLiterSpace.getText().trim());

            // Get the selected liquids from the ComboBox
            ObservableList<CaskLiquids> selectedLiquids = cbxCaskLiquids.getValue();
            ArrayList<CaskLiquids> liquidsList = new ArrayList<>(selectedLiquids);

            String countryOfOrigin = txfXCountryOfOrigin.getText();
            String supplier = txfXSupplier.getText();

            if (!liquidsList.isEmpty() && !countryOfOrigin.isEmpty() && !supplier.isEmpty()) {
                int currentId = startId;
                for (int index = 0; index < caskAmount; index++) {
                    controller.createCask(currentId, liters, liquidsList, countryOfOrigin, supplier);
                    currentId++;
                }

                updateLists();

                // Show success popup
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
            } else {
                // Error popup if no liquids are selected
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Please select at least one liquid, and enter valid country and supplier.");
                error.showAndWait();
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
    }

    private void updateLists() {
        lvwCasks.getItems().setAll(controller.getStorage().getCasks());
    }
}