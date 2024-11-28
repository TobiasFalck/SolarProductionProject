package org.example.solarproductionproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SolarController {

    private ArrayList<SolarData> data;

    //region
    @FXML
    private Label errorMessage;
    @FXML
    private ChoiceBox<String> siteDDL; // Drop Down List for Sites
    @FXML
    private DatePicker dateDP; // Date Picker
    @FXML
    private ChoiceBox<String> diagramTypeDDL; // Drop Down List for Chart Types
    @FXML
    private BarChart<String, Integer> productionBarChart; // Bar Chart for Data
    @FXML
    private PieChart productionPieChart; // Pie Chart for Data
    @FXML
    private LineChart<String, Integer> productionLineChart;
    @FXML
    private Button dayButton, monthButton;
    //endregion


    // Initializes and reads the data file
    @FXML
    public void initialize()
    {
        try {
            data = ReadData.readFileData("src/resources/solar-dataset.tsv");

            // Populate SiteID Dropdown
            for (SolarData solarData : data) {
                if (!siteDDL.getItems().contains(String.valueOf(solarData.getSiteID()))) {
                    siteDDL.getItems().add(String.valueOf(solarData.getSiteID()));
                }
            }

            // Populate Chart Type Dropdown
            diagramTypeDDL.setItems(FXCollections.observableArrayList("Bar Chart", "Pie Chart", "Line Chart"));
            diagramTypeDDL.setValue("Bar Chart"); // Default selection
        } catch (FileNotFoundException e) {
            errorMessage.setText("File not found");
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }
    }

    // Button click event to create charts
    public void createChartClick() {
        ArrayList<Integer> totalsWhs = new ArrayList<>();
        ArrayList<Integer> times = new ArrayList<>();

        errorMessage.setText(""); // Hide error message

        // Get user selections
        LocalDate datePicked = dateDP.getValue();
        int siteIDPicked = Integer.parseInt(siteDDL.getValue()); // Convert selected site to int

        // Filter data based on selected site and date
        for (SolarData solarData : data) {
            if (siteIDPicked == solarData.getSiteID() && datePicked.equals(solarData.getDate())) {
                totalsWhs.add(solarData.getWattPerHour());
                times.add(solarData.getTime());
            }
        }

        if (totalsWhs.isEmpty())
        {
            errorMessage.setText("No data for\nchosen date");
        }
        else
        {
            // Get selected chart type and create appropriate chart
            String selectedChart = diagramTypeDDL.getValue();

            // Hide all charts initially
            productionBarChart.setVisible(false);
            productionPieChart.setVisible(false);

            // Display and update the selected chart - Removes active charts
            switch (selectedChart) {
                case "Bar Chart":
                    createDayChart(siteIDPicked, datePicked, totalsWhs, times);
                    productionBarChart.setVisible(true);
                    productionPieChart.setVisible(false);
                    productionLineChart.setVisible(false);
                    break;
                case "Pie Chart":
                    createPieChart(siteIDPicked, datePicked, totalsWhs);
                    productionPieChart.setVisible(true);
                    productionLineChart.setVisible(false);
                    productionBarChart.setVisible(false);
                    break;
                case "Line Chart":
                    createLineChart(siteIDPicked, datePicked, totalsWhs, times);
                    productionLineChart.setVisible(true);
                    productionPieChart.setVisible(false);
                    productionBarChart.setVisible(false);
                    break;
                default:
                    errorMessage.setText("Invalid chart type selected");
            }
        }
    }

    /**
     * Creates a Bar Chart based on provided data
     */
    public void createDayChart(int siteIDPicked, LocalDate datePicked, ArrayList<Integer> totalWhs, ArrayList<Integer> times) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Site ID: " + siteIDPicked + "\nDate: " + datePicked.toString());

        for (int i = 0; i < totalWhs.size(); i++) {
            series.getData().add(new XYChart.Data<>(times.get(i) + ":00", totalWhs.get(i)));
        }

        productionBarChart.setData(FXCollections.observableArrayList(series));
    }

    /**
     * Creates a Pie Chart based on provided data
     */
    public void createPieChart(int siteIDPicked, LocalDate datePicked, ArrayList<Integer> totalWhs) {
        productionPieChart.getData().clear();

        for (int i = 0; i < totalWhs.size(); i++) {
            productionPieChart.getData().add(new PieChart.Data("" + i, totalWhs.get(i)));
        }

        productionPieChart.setTitle("Site ID: " + siteIDPicked + "\nDate: " + datePicked.toString());
    }

    /**
     * Adds the given data into a LineChart
     * @param siteIDPicked
     * @param datePicked
     * @param totalWhs
     * @param times
     */
    public void createLineChart(int siteIDPicked, LocalDate datePicked, ArrayList<Integer> totalWhs, ArrayList<Integer> times) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Site ID: " + siteIDPicked + "\nDate: " + datePicked.toString());

        for (int i = 0; i < totalWhs.size(); i++) {
            series.getData().add(new XYChart.Data<>(times.get(i) + ":00", totalWhs.get(i)));
        }

        // Clear old data and set new data
        productionLineChart.getData().clear();
        productionLineChart.getData().add(series);
    }
}
