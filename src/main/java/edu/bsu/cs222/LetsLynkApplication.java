package edu.bsu.cs222;


import javafx.application.Application;
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

    private final TextField address1 = new TextField("Street address 1");
    private final TextField city1 = new TextField("City name 1");
    private final TextField zip1 = new TextField("Zipcode 1");

    private final TextField address2 = new TextField("Street address 2");
    private final TextField city2 = new TextField("City name 2");
    private final TextField zip2 = new TextField("Zipcode 2");

    private final Button goButton = new Button("Let's Lynk!");

    private Label checkAddress1 = new Label("Formatted Address Should Appear Here");
    private Label checkAddress2 = new Label("Formatted Address Should Appear Here");

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
        gridPane.add(address1,1,0);
        gridPane.add(address2,2,0);
        gridPane.add(city1,1,1);
        gridPane.add(city2,2,1);
        gridPane.add(zip1,1,2);
        gridPane.add(zip2,2,2);


        gridPane.add(checkAddress1, 1, 3);
        gridPane.add(checkAddress2, 2, 3);


        goButton.setOnAction((event) -> {
            goButton.setDisable(true);

            executor.execute(()->{
                AddressParser parser1 = new AddressParser();
                AddressParser parser2 = new AddressParser();

                String addressOne = String.format(address1.getText() + city1.getText() + zip1.getText());
                String addressTwo = String.format(address2.getText() + city2.getText() + zip2.getText());

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
