package com.example.dam_rest_nasa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        oracleExemple();
        /** Récupération de donnees Rest sous forme de fichier JSON */
        String apodUrl = "https://api.nasa.gov/planetary/apod";
        String apiKey = "XJJDhlvsGLcGc0bqi8tWKSUCQPPUz8PTKntxGmNp";

        try {
            URL connexionSiteNasaApod = new URL(apodUrl + "?api_key=" + apiKey);
            URLConnection urlConnection = connexionSiteNasaApod.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void oracleExemple() {
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