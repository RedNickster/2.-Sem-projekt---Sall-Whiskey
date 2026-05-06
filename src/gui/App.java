package gui;

import controller.Controller;
import javafx.application.Application;

public class App {

    private Controller controller;

    public static void main(String[] args) {
        App app = new App();
        //app.initStorage();
        Application.launch(GUI.class);
    }
}
