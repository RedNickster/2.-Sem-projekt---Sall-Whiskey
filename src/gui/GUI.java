package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.*;

public class GUI extends Application {

      //GUI-komponenter
      private final ListView<Cask> lvwCasks = new ListView<>();

      private final ComboBox<CaskLiquids> cbxCaskLiquids = new ComboBox<>();

      private final TextField txfXLiterSpace = new TextField();
      private final TextField txfXCountryOfOrigin = new TextField();
      private final TextField txfXSupplier = new TextField();

//      private final TextField txfX4 = new TextField();
//    private final TextField txfX5 = new TextField();

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

        lvwCasks.setPrefSize(250,150);

        Label lblCasks = new Label("Casks");
        Label lblCaskLiterSpace = new Label("Litre");
        Label lblCaskLiquid = new Label("Cask liquid");
        Label lblCountryOfOrigin = new Label("Coutry of origin");
        Label lblSupplier = new Label("Supplier");

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


        pane.add(btnCreateCask, 0, 10);

        //btnCreateCask.setOnAction(event -> this.createCask);
    }

    private void createCask() {
        int id = ;
        int liters = Integer.parseInt(txfXLiterSpace.getText());
        Enum<CaskLiquids> = ;
        String countryOfOrigin = txfXCountryOfOrigin.getText();
        String supplier = txfXSupplier.getText();

    }

    private void createDistillation() {

    }

}
