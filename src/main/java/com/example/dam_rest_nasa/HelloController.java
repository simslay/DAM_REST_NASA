package com.example.dam_rest_nasa;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ImageView image;
    @FXML
    private VBox content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        oracleExemple();
        /** Récupération de donnees Rest sous forme de fichier JSON */
        APOD apod = createAPOD(LocalDate.now());

        title.setText(apod.getTitle());

        image.setImage(new Image(apod.getUrl()));
        image.setFitHeight(300);
        image.setFitWidth(300);

        image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label secondLabel = new Label(apod.getTitle());
                ImageView imageView = new ImageView(new Image(apod.getUrl()));

                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(secondLabel);
                secondaryLayout.getChildren().add(imageView);

                Scene secondScene = new Scene(secondaryLayout, 230, 100);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);

                newWindow.show();
                event.consume();
            }
        });

        datePicker.setValue(apod.getDate());
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                APOD apod2 = createAPOD(newValue);

                title.setText(apod2.getTitle());
                image.setImage(new Image(apod2.getUrl()));
                image.setFitHeight(300);
                image.setFitWidth(300);
            }
        });
    }

    private APOD createAPOD(LocalDate date) {
        // https://api.nasa.gov/planetary/apod?api_key=XJJDhlvsGLcGc0bqi8tWKSUCQPPUz8PTKntxGmNp
        // copyright
        // explanation
        // hdurl
        // title
        String apodUrl = "https://api.nasa.gov/planetary/apod";
        String apiKey = "XJJDhlvsGLcGc0bqi8tWKSUCQPPUz8PTKntxGmNp";

//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("YYYY-MM-DD");
//        String formattedDate = date.format(pattern);

        try {
            URL connexionSiteNasaApod = new URL(apodUrl + "?api_key=" + apiKey + "&date=" + date);
            URLConnection urlConnection = connexionSiteNasaApod.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

//            String inputLine;
//
//
//            while ((inputLine = bufferedReader.readLine()) != null) {
//                System.out.println(inputLine);
//            }
//            bufferedReader.close();

            JSONTokener jsonTokener = new JSONTokener(bufferedReader);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            String objectUrl = jsonObject.getString("url");
            String objectTitle = jsonObject.getString("title");

            return new APOD(objectUrl, date, objectTitle);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private void oracleExemple() {
        try {
            URL oracle = new URL("https://www.oracle.com");
            String inputLine;
            BufferedReader in = new BufferedReader(new InputStreamReader((oracle.openStream())));

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }

            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}