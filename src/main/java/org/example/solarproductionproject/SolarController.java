package org.example.solarproductionproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SolarController
{
    static boolean dataInBarChart = false;
    static ArrayList<SolarData> data;

    static {
        try {
            data = ReadData.readFileData("src/resources/solar-dataset.tsv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // create ArrayLists for future use
    static ArrayList<Integer> totals = new ArrayList<>();
    static ArrayList<Integer> times = new ArrayList<>();


    @FXML
    private Label errorMessage;

    @FXML
    private ChoiceBox<String> siteDDL; // DDL: Drop Down List

    @FXML
    private DatePicker dateDP;

    @FXML
    private ChoiceBox<String> diagramTypeDDL;

    @FXML
    private Button searchButton;

    @FXML
    private BarChart<String, Integer> productionBarChart;


    @FXML
    public void initialize() {
        for (SolarData solarData : data)
        {
            // finds all siteIDs that are not currently added to siteDDL
            if (!siteDDL.getItems().contains(String.valueOf(solarData.getSiteID())))
            {
                // convert int to String, since choicebox only takes Strings
                siteDDL.getItems().add(String.valueOf(solarData.getSiteID()));
            }
        }
    }

    public void createChartClick() {
        totals.clear(); // clear out potentially "old" data from totals
        errorMessage.setText(""); // hide error message

        LocalDate datePicked = dateDP.getValue();
        int siteIDPicked = Integer.parseInt(siteDDL.getValue()); // get siteID from sites choicebox and convert it to int

        // run through every index in ArrayList data
        for (int i = 0; i < data.size(); i++) {

            // check if date and site picked(based on siteID) correspond to what is in the dataset
            if (siteIDPicked == data.get(i).getSiteID() && datePicked.equals(data.get(i).getDate())) {
                totals.add(data.get(i).getTotalEnergyProduction()); // add hourly total kWh to ArrayList totals
                times.add(data.get(i).getTime());
            }
        }
        if (totals.isEmpty()) {
            errorMessage.setText("No data for\nchosen date");
        }

        // create bar chart
        XYChart.Series<String, Integer> series = new XYChart.Series();
        series.setName("Site ID: " + siteIDPicked + "\nDate: " + datePicked.toString());

        for (int i = 0; i < totals.size(); i++) {
                series.getData().add(new XYChart.Data(times.get(i) + ":00", totals.get(i)));
                System.out.println(totals.get(i));
        }

        // only run if date is valid/has data
        if (!totals.isEmpty()) {
            // replace data in barchart instead of replacing it
            productionBarChart.setData(FXCollections.observableArrayList(series));
        }
    }



}