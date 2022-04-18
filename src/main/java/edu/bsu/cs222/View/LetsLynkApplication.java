package edu.bsu.cs222.View;



import edu.bsu.cs222.Model.AddressParser;
import edu.bsu.cs222.Model.Coordinates;
import edu.bsu.cs222.Model.URLFormatter;
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

import static javafx.scene.text.TextAlignment.CENTER;


public class LetsLynkApplication extends Application {

    // Input Fields
    private final TextField addressOneInput = new TextField();
    private final TextField addressTwoInput = new TextField();

    // Labels
    private final Label instructions = new Label("Please input your desired addresses in the following format \n 1615 West Riverside Avenue, Muncie, IN 47303");
    private final Label formattedAddress1 = new Label("");
    private final Label formattedAddress2 = new Label("");
    private final Label venue = new Label("");
    private final Label venueInfo = new Label("");
    private final Label venueOpenValue = new Label("");

    // Buttons
    private final Button letsLynkButton = new Button("Let's Lynk!");

    // Executors
    private final Executor letsLynkExecutor = Executors.newSingleThreadExecutor();

    // Classes used
    AddressParser addressParser = new AddressParser();
    URLFormatter urlFormatter = new URLFormatter();
    Coordinates locationData = new Coordinates();

    // Show GUI
    public void start(Stage primaryStage) {
        setUpWindow(primaryStage);
        setLetsLynkButtonClick();
        primaryStage.show();
    }
    // Build GUI
    private void setUpWindow(Stage primaryStage){
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
                letsLynkButton,
                instructions,
                content,
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
        letsLynkButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        instructions.setTextAlignment(CENTER);
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
                InputStream address1Comparison = urlFormatter.placeFromText(addressOneInput.getText());
                String address1Print = addressParser.parseUserAddress(address1Comparison);
                formattedAddress1.setText(address1Print);

                InputStream address2Comparison = urlFormatter.placeFromText(addressTwoInput.getText());
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
                InputStream addressOneURL = urlFormatter.placeFromText(formattedAddress1.getText());
                InputStream addressTwoURL = urlFormatter.placeFromText(formattedAddress2.getText());

                Double[] latLong1 = locationData.parseLatitudeAndLongitude(addressOneURL);
                Double[] latLong2 = locationData.parseLatitudeAndLongitude(addressTwoURL);
                Double[] avgLatLong = locationData.coordinatesMidpoint(latLong1, latLong2);

                InputStream venueURL1 = urlFormatter.placeNearSearch(avgLatLong[0], avgLatLong[1], "restaurant");
                InputStream venueURL2 = urlFormatter.placeNearSearch(avgLatLong[0], avgLatLong[1], "restaurant");
                InputStream venueURL3 = urlFormatter.placeNearSearch(avgLatLong[0], avgLatLong[1], "restaurant");

                venue.setText(addressParser.parseVenueAddress(venueURL1));
                venueInfo.setText(addressParser.parseName(venueURL2));
                venueOpenValue.setText("This venue is open: " + addressParser.parseHoursOfOperation(venueURL3));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
