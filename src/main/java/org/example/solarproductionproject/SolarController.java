package org.example.solarproductionproject;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SolarController
{
    ArrayList<SolarData> data;

    @FXML
    private ChoiceBox<String> diagramTypeDDL;

    @FXML
    private Button searchButton;

    @FXML
    private DatePicker dateDP;

    @FXML
    private BarChart productionBarChart;

    @FXML
    private Label productionTotal;

    @FXML
    final private ChoiceBox<String> siteDDL = new ChoiceBox<>();

    @FXML
    private void createChartClick()
    {
        productionBarChart.setVisible(true);
    }

    @FXML
    public void initialize() throws FileNotFoundException {
         data = ReadData.readFileData("src/resources/solar-dataset.tsv");

         for (SolarData solarData : data)
         {
             //Finds all siteID's that is not currently added to siteDDL
             if (!siteDDL.getItems().contains(String.valueOf(solarData.getSiteID())))
             {
                 siteDDL.getItems().add(String.valueOf(solarData.getSiteID()));
             }
         }
    }
}