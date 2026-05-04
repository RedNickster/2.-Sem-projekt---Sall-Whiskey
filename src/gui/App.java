package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class App {

    private Stage stage;

    public static void main(String[] args) {
        App app = new App();
        Application.launch(GUI.class);
    }
}
