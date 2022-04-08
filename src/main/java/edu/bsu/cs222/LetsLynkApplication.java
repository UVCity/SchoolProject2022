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

    private final Label addressOneLabel = new Label("Input user 1 address (Ex: 1500 W Riverside Ave. Muncie, IN 47303)");
    private final TextField addressOneInput = new TextField("");
    private final Label addressTwoLabel = new Label("Input user 1 address (Ex: 1500 W Riverside Ave. Muncie, IN 47303)");
    private final TextField addressTwoInput = new TextField("");

    private final Button letsLynkButton = new Button("Let's Lynk!");
    private final Executor letsLynkExecutor = Executors.newSingleThreadExecutor();

    private final Label formattedAddress1 = new Label("");
    private final Label formattedAddress2 = new Label("");
    private final Button yesButton = new Button("This is my address!");
    //private final Button noButton = new Button("This is NOT my address!");
    private final Executor yesButtonExecutor = Executors.newSingleThreadExecutor();
    //private final Executor noButtonExecutor = Executors.newSingleThreadExecutor();

    AddressParser parser1 = new AddressParser();

    private final Label venueInformation = new Label("");

    public void start(Stage primaryStage) {
        setUpWindow(primaryStage);
        setLetsLynkButtonClick();
        setYesButtonClick();
        primaryStage.show();
        address1Input.setPromptText("ex. 9999 Heckin Rd");
        city1Input.setPromptText("ex. Actually Hell");
        zip1Input.setPromptText("ex. 66666");
        distance1Input.setPromptText("ex. 50mi");
    }

    private void setUpWindow(Stage primaryStage){
        primaryStage.setTitle("Let's-Lynk");
        primaryStage.setScene(new Scene(createWindow()));
        primaryStage.setOnCloseRequest(X -> Platform.exit());

    }

    public Parent createWindow() {
        VBox bigBox = new VBox();
        HBox window = new HBox();
        VBox user1Info = new VBox();
        VBox user2Info = new VBox();
        bigBox.getChildren().addAll(
                letsLynkButton,
                window,
                yesButton,
                venueInformation
        );

        window.getChildren().addAll(
                user1Info,
                user2Info
        );

        user2Info.getChildren().addAll(
                addressTwoLabel,
                addressTwoInput
        );

        user1Info.getChildren().addAll(
                addressOneLabel,
                addressOneInput
        );
        letsLynkButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return bigBox;
    }


    private void setLetsLynkButtonClick(){
        letsLynkButton.setOnAction((event) -> {
            letsLynkButton.setDisable(true);

            letsLynkExecutor.execute(()->{

                try {
                    InputStream address1Comparison = parser1.placeFromText(addressOneInput.getText());
                    String address1Print = parser1.parseUserAddress(address1Comparison);
                    formattedAddress1.setText(address1Print);

                    InputStream address2Comparison = parser1.placeFromText(addressTwoInput.getText());
                    String address2Print = parser1.parseUserAddress(address2Comparison);
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
                    InputStream address1Comparison = parser1.placeFromText(addressOneInput.getText());
                    InputStream address2Comparison = parser1.placeFromText(addressTwoInput.getText());


                    Double [] latLong1 = parser1.parseLatitudeAndLongitude(address1Comparison);
                    Double [] latLong2 = parser1.parseLatitudeAndLongitude(address2Comparison);


                    Double [] avgCoordinates = parser1.returnAverage(latLong1, latLong2);

                    InputStream venueStream = parser1.placeNearSearch(avgCoordinates[0], avgCoordinates[1], "Restaurant");

                    String venueAddress = parser1.parseVenueAddress(venueStream);
                    String venueName = parser1.parseName(venueStream);
                    String venueHours = parser1.parseHoursOfOperation(venueStream);

                    venueInformation.setText(venueName + " " + venueAddress + " " + venueHours);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }




}
