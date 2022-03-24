package edu.bsu.cs222;


import javafx.application.Application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class LetsLynkApplication extends Application {

    private final TextField address1 = new TextField("Street address 1");
    private final TextField city1 = new TextField("City name 1");
    private final TextField zip1 = new TextField("Zipcode 1");

    private final TextField address2 = new TextField("Street address 2");
    private final TextField city2 = new TextField("City name 2");
    private final TextField zip2 = new TextField("Zipcode 2");

    private final Button goButton = new Button("Go!");

    @Override
    public void start(Stage dataEnterStage) {

    }
}
