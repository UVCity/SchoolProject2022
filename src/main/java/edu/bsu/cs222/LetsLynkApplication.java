package edu.bsu.cs222;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class LetsLynkApplication extends Application {

    // Input Fields
    private final TextField addressOneInput = new TextField();
    private final TextField cityOneInput = new TextField();
    private final TextField zipOneInput = new TextField();
    private final TextField distanceOneInput = new TextField();
    private final TextField addressTwoInput = new TextField();
    private final TextField cityTwoInput = new TextField();
    private final TextField zipTwoInput = new TextField();
    private final TextField distanceTwoInput = new TextField();

    // Labels
    private final Label addressOneLabel = new Label("User 1 Address");
    private final Label cityOneLabel = new Label("User 1 City");
    private final Label zipOneLabel = new Label("User 1 Zip");
    private final Label addressTwoLabel = new Label("User 2 Address");
    private final Label cityTwoLabel = new Label("User 2 City");
    private final Label zipTwoLabel = new Label("User 2 Zip");

    private final Button letsLynkButton = new Button("Let's Lynk!");
    private final Executor letsLynkExecutor = Executors.newSingleThreadExecutor();

    private final Label formattedAddress1 = new Label("");
    private final Label formattedAddress2 = new Label("");
    private final Button yesButton = new Button("This is my address!");
    //private final Button noButton = new Button("This is NOT my address!");
    private final Executor yesButtonExecutor = Executors.newSingleThreadExecutor();
    //private final Executor noButtonExecutor = Executors.newSingleThreadExecutor();

    AddressParser addressParser = new AddressParser();

    private final Label venueInformation = new Label("");

    public void start(Stage primaryStage) {
        setUpWindow(primaryStage);
        setLetsLynkButtonClick();
        setYesButtonClick();
        primaryStage.show();
        addressOneInput.setPromptText("ex. 1525 W McKinley Ave");
        cityOneInput.setPromptText("ex. Muncie");
        zipOneInput.setPromptText("ex. 47303");
        distanceOneInput.setPromptText("ex. 50mi");
        addressTwoInput.setPromptText("ex. 2000 N University Ave");
        cityTwoInput.setPromptText("ex. Muncie");
        zipTwoInput.setPromptText("ex. 47303");
        distanceTwoInput.setPromptText("ex. 50mi");
    }

    private void setUpWindow(Stage primaryStage){
        primaryStage.setTitle("Let's-Lynk");
        primaryStage.setScene(new Scene(createWindow()));
        primaryStage.setOnCloseRequest(X -> Platform.exit());

    }

    public Parent createWindow() {
        VBox mainWindow = new VBox();
        HBox content = new HBox();
        VBox input1 = new VBox();
        VBox input2 = new VBox();
        mainWindow.getChildren().addAll(
                letsLynkButton,
                content,
                yesButton,
                venueInformation
        );
        content.getChildren().addAll(
                input1,
                input2
        );
        input1.getChildren().addAll(
                addressOneLabel,
                addressOneInput,
                cityOneLabel,
                cityOneInput,
                zipOneLabel,
                zipOneInput
        );
        input2.getChildren().addAll(
                addressTwoLabel,
                addressTwoInput,
                cityTwoLabel,
                cityTwoInput,
                zipTwoLabel,
                zipTwoInput
        );
        letsLynkButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return mainWindow;
    }


    private void setLetsLynkButtonClick(){
        letsLynkButton.setOnAction((event) -> {
            letsLynkButton.setDisable(true);

            letsLynkExecutor.execute(()->{

                try {
                    InputStream address1Comparison = addressParser.placeFromText(addressOneInput.getText());
                    String address1Print = addressParser.parseUserAddress(address1Comparison);
                    formattedAddress1.setText(address1Print);

                    InputStream address2Comparison = addressParser.placeFromText(addressTwoInput.getText());
                    String address2Print = addressParser.parseUserAddress(address2Comparison);
                    formattedAddress2.setText(address2Print);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                letsLynkButton.setDisable(false);
            });
        });
    }


    private void setYesButtonClick(){
        yesButton.setOnAction((event) -> {
            yesButton.setDisable(true);

            yesButtonExecutor.execute(()->{

                try {
                    InputStream address1Comparison = addressParser.placeFromText(addressOneInput.getText());
                    InputStream address2Comparison = addressParser.placeFromText(addressTwoInput.getText());


                    Double [] latLong1 = addressParser.parseLatitudeAndLongitude(address1Comparison);
                    Double [] latLong2 = addressParser.parseLatitudeAndLongitude(address2Comparison);


                    Double [] avgCoordinates = addressParser.returnAverage(latLong1, latLong2);

                    InputStream venueStream = addressParser.placeNearSearch(avgCoordinates[0], avgCoordinates[1], "Restaurant");

                    String venueAddress = addressParser.parseVenueAddress(venueStream);
                    String venueName = addressParser.parseName(venueStream);
                    String venueHours = addressParser.parseHoursOfOperation(venueStream);

                    venueInformation.setText(venueName + " " + venueAddress + " " + venueHours);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }




}
