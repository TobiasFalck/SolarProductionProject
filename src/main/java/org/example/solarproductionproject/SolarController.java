package org.example.solarproductionproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SolarController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() throws FileNotFoundException {
         ArrayList<SolarData> data = ReadData.readFileData("C:\\Users\\simo0\\Desktop\\solardata.tsv");
         System.out.println(data.get(0).getDate());
    }
}