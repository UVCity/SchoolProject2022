package edu.bsu.cs222;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class LetsLynkApplication extends Application {

    private final Label display = new Label("Let's Lynk!");

    private final TextField address1Input = new TextField("");
    private final TextField city1Input = new TextField("");
    private final TextField zip1Input = new TextField("");
    private final TextField distance1Input = new TextField("");
    private final Label address1Label = new Label("Input street address (Ex: 123 Oak Street)");
    private final Label city1Label = new Label("Input city and state (Ex: Muncie, IN)");
    private final Label zip1Label = new Label("Input zipcode (Ex: 47306)");
    private final Label distance1Label = new Label("Input distance you are willing to commute in miles (Ex: 5)");

    private final TextField address2Input = new TextField("");
    private final TextField city2Input = new TextField("");
    private final TextField zip2Input = new TextField("");
    private final TextField distance2Input = new TextField("");
    private final Label address2Label = new Label("Input street address (Ex: 123 Oak Street)");
    private final Label city2Label = new Label("Input city and state (Ex: Muncie, IN)");
    private final Label zip2Label = new Label("Input zipcode (Ex: 47306)");
    private final Label distance2Label = new Label("Input distance you are willing to commute in miles (Ex: 5)");


    private final Button goButton = new Button("Let's Lynk!");

    private Label checkAddress1 = new Label("");
    private Label checkAddress2 = new Label("");

    private final Executor executor = Executors.newSingleThreadExecutor();


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(makeScene());
        primaryStage.show();
    }

    private Scene makeScene() {
        VBox vBox1 = new VBox();
        Parent addressEntryArea = createAddressEntryControl();
        vBox1.getChildren().addAll(goButton, addressEntryArea);
        return new Scene(vBox1);
    }

    private Parent createAddressEntryControl () {
        GridPane gridPane = new GridPane();
        gridPane.add(goButton,0,0);
        goButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(address1Label,1,0);
        gridPane.add(address1Input,1,1);
        gridPane.add(address2Label,2,0);
        gridPane.add(address2Input,2,1);
        gridPane.add(city1Label,1,2);
        gridPane.add(city1Input,1,3);
        gridPane.add(city2Label,2,2);
        gridPane.add(city2Input,2,3);
        gridPane.add(zip1Label,1,4);
        gridPane.add(zip1Input,1,5);
        gridPane.add(zip2Label,2,4);
        gridPane.add(zip2Input,2,5);
        gridPane.add(distance1Label,1,6);
        gridPane.add(distance1Input,1,7);
        gridPane.add(distance2Label,2,6);
        gridPane.add(distance2Input,2,7);


        goButton.setOnAction((event) -> {
            goButton.setDisable(true);

            executor.execute(()->{
                AddressParser parser1 = new AddressParser();
                AddressParser parser2 = new AddressParser();

                String addressOne = String.format(address1Input.getText() + city1Input.getText() + zip1Input.getText());
                String addressTwo = String.format(address2Input.getText() + city2Input.getText() + zip2Input.getText());

                Platform.runLater(()->{
                    try {
                        InputStream formattedAddress1 = parser1.placeFromText(addressOne);
                        String formattedUserAddress1 = parser1.parseUserAddress(formattedAddress1);
                        checkAddress1.setText(formattedUserAddress1);

                        InputStream formattedAddress2 = parser2.placeFromText(addressTwo);
                        String formattedUserAddress2 = parser2.parseUserAddress(formattedAddress2);
                        checkAddress2.setText(formattedUserAddress2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    goButton.setDisable(false);
                });
            });
        });

        return gridPane;

    }




}
