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
    private final TextField addressTwoInput = new TextField();
    private final TextField cityTwoInput = new TextField();
    private final TextField zipTwoInput = new TextField();

    // Labels
    private final Label addressOneLabel = new Label("User 1 Address");
    private final Label cityOneLabel = new Label("User 1 City and State");
    private final Label zipOneLabel = new Label("User 1 Zip");
    private final Label addressTwoLabel = new Label("User 2 Address");
    private final Label cityTwoLabel = new Label("User 2 City and State");
    private final Label zipTwoLabel = new Label("User 2 Zip");

    private final Button letsLynkButton = new Button("Let's Lynk!");
    private final Executor letsLynkExecutor = Executors.newSingleThreadExecutor();

    private final Label formattedAddress1 = new Label("");
    private final Label formattedAddress2 = new Label("");

    // Result Information

    private final Label venue = new Label("");
    private final Label venueInfo = new Label("");

    AddressParser addressParser = new AddressParser();


    public void start(Stage primaryStage) {
        setUpWindow(primaryStage);
        setLetsLynkButtonClick();
        primaryStage.show();
        addressOneInput.setPromptText("ex. 1615 West Riverside Avenue");
        cityOneInput.setPromptText("ex. Muncie, IN");
        zipOneInput.setPromptText("ex. 47303");
        addressTwoInput.setPromptText("2800 North Tillotson Avenue");
        cityTwoInput.setPromptText("ex. Muncie, IN");
        zipTwoInput.setPromptText("ex. 47304");
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
                venue,
                venueInfo
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
                intakeUserInput();
                useUserInput();
                letsLynkButton.setDisable(false);
            });
        });
    }



    private void intakeUserInput() {
        Platform.runLater(()-> {
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
        });
    }

    private void useUserInput() {
        Platform.runLater(()-> {
            try {
                InputStream addressOneURL = addressParser.placeFromText(formattedAddress1.getText());
                InputStream addressTwoURL = addressParser.placeFromText(formattedAddress2.getText());

                Double[] latLong1 = addressParser.parseLatitudeAndLongitude(addressOneURL);
                Double[] latLong2 = addressParser.parseLatitudeAndLongitude(addressTwoURL);
                Double[] avgLatLong = addressParser.returnAverage(latLong1, latLong2);

                InputStream venueURL1 = addressParser.placeNearSearch(avgLatLong[0], avgLatLong[1], "restaurant");
                InputStream venueURL2 = addressParser.placeNearSearch(avgLatLong[0], avgLatLong[1], "restaurant");
                venue.setText(addressParser.parseVenueAddress(venueURL1));
                venueInfo.setText(addressParser.parseName(venueURL2));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
