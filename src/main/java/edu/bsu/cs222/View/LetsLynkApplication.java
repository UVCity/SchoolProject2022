package edu.bsu.cs222.View;



import edu.bsu.cs222.Model.AddressHandler;
import edu.bsu.cs222.Model.AddressParser;
import edu.bsu.cs222.Model.Coordinates;
import edu.bsu.cs222.Model.URLFormatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.minidev.json.parser.ParseException;

import java.io.IOException;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static javafx.scene.text.TextAlignment.CENTER;


public class LetsLynkApplication extends Application {

    // Input Fields
    private final TextField addressOneInput = new TextField();
    private final TextField addressTwoInput = new TextField();

    // Labels
    private final Label instructions = new Label("Please input one address per field using the following format \n 1615 West Riverside Avenue, Muncie, IN 47303");
    private final Label venue = new Label("");
    private final Label venueInfo = new Label("");
    private final Label venueOpenValue = new Label("");

    // Buttons
    private final Button goButton = new Button("Go!");

    // Executors
    private final Executor letsLynkExecutor = Executors.newSingleThreadExecutor();

    // Objects
    AddressParser addressParser = new AddressParser();
    URLFormatter urlFormatter = new URLFormatter();
    Coordinates locationData = new Coordinates();
    AddressHandler address1 = new AddressHandler();
    AddressHandler address2 = new AddressHandler();

    // Show GUI
    public void start(Stage primaryStage) {
        setUpWindow(primaryStage);
        setLetsLynkButtonClick();
        primaryStage.show();
    }

    // Build GUI
    private void setUpWindow(Stage primaryStage) {
        primaryStage.setTitle("Let's-Lynk");
        primaryStage.setScene(new Scene(createWindow()));
        primaryStage.setOnCloseRequest(X -> Platform.exit());
    }

    // Organize GUI
    public Parent createWindow() {
        VBox mainWindow = new VBox();
        HBox content = new HBox();
        VBox input1 = new VBox();
        VBox input2 = new VBox();
        mainWindow.getChildren().addAll(
                instructions,
                content,
                goButton,
                venue,
                venueInfo,
                venueOpenValue
        );
        content.getChildren().addAll(
                input1,
                input2
        );
        input1.getChildren().addAll(
                addressOneInput
        );
        input2.getChildren().addAll(
                addressTwoInput
        );
        mainWindow.setAlignment(Pos.CENTER);
        content.setAlignment(Pos.CENTER);
        addressOneInput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addressTwoInput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        instructions.setTextAlignment(CENTER);
        venue.setTextAlignment(CENTER);
        venueInfo.setTextAlignment(CENTER);
        venueOpenValue.setTextAlignment(CENTER);
        return mainWindow;
    }


    private void setLetsLynkButtonClick() {
        goButton.setOnAction((event) -> {
            goButton.setDisable(true);

            letsLynkExecutor.execute(() -> {
                address1.formatUserInput(addressOneInput.getText());
                address2.formatUserInput(addressTwoInput.getText());
                address1.parseCoordinates(address1.getUrl());
                address2.parseCoordinates(address2.getUrl());
                locationData.findCoordinatesMidpoint(address1.getCoordinates(), address2.getCoordinates());
                try {
                    urlFormatter.createVenueAddressURL(locationData.getLatitude(), locationData.getLongitude());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    addressParser.createJsonObject(urlFormatter.getVenueUrl());
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()-> {
                            try {
                                venue.setText(addressParser.parseVenueAddress());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                venueOpenValue.setText("This venue is open: " + addressParser.parseHoursOfOperation());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                venueInfo.setText(addressParser.parseName());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                goButton.setDisable(false);
            });
        });
    }
}