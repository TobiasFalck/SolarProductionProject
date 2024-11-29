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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SolarController
{
    DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // set formatting for date

    final private String filePath = "src/resources/solar-dataset.tsv";
    private ArrayList<SolarData> data;

    @FXML
    private Label errorMessage;

    @FXML
    private ChoiceBox<String> siteDDL; // DDL: Drop Down List

    @FXML
    private DatePicker dateDP;

    @FXML
    private ChoiceBox<String> diagramTypeDDL;

    @FXML
    private Button dayButton;

    @FXML
    private Button monthButton;

    @FXML
    private BarChart<String, Integer> barChartDay;

    @FXML
    private BarChart<String, Integer> barChartMonth;


    @FXML
    public void initialize()
    {
        try
        {
            data = ReadData.readFileData(filePath);

            for (SolarData solarData : data)
            {
                // finds all siteIDs that are not currently added to siteDDL
                if (!siteDDL.getItems().contains(String.valueOf(solarData.getSiteID())))
                {
                    // convert int to String, since choicebox only takes Strings
                    siteDDL.getItems().add(String.valueOf(solarData.getSiteID()));
                }
            }
            if (siteDDL.getItems().isEmpty())
            {
                errorMessage.setText("No sites found");
            }
            else
            {
                siteDDL.getSelectionModel().selectFirst();
            }
        }
        catch (FileNotFoundException e)
        {
            errorMessage.setText("File not found");
        }
        catch (Exception e)
        {
            errorMessage.setText(e.getMessage());
        }
    }

    public void createChartClick()
    {
        // specific date
        ArrayList<Integer> whsHoursPerDay = new ArrayList<>();
        ArrayList<Integer> hours = new ArrayList<>();

        ArrayList<Integer> whsDaysPerMonth = new ArrayList<>();
        ArrayList<Integer> days = new ArrayList<>();

        int dayCounter = 1;
        int whsProducedInDay = 0;

        LocalDate datePicked = dateDP.getValue();
        int siteIDPicked = Integer.parseInt(siteDDL.getValue()); // get siteID from sites choicebox and convert it to int

        for (SolarData solarData : data)
        {
            // check if date and site picked(based on siteID) correspond to what is in the dataset
            if (siteIDPicked == solarData.getSiteID() && datePicked.equals(solarData.getDate())) {
                whsHoursPerDay.add(solarData.getWattPerHour()); // add hourly watt-hours to ArrayList whsHoursPerDay
                hours.add(solarData.getTime());
                System.out.println(solarData.getWattPerHour());
            }

            // month
            if (siteIDPicked == solarData.getSiteID() && datePicked.getMonthValue() == solarData.getMonth() && datePicked.getYear() == solarData.getYear()) {
                if (dayCounter == solarData.getDay()) {
                    whsProducedInDay += solarData.getWattPerHour();
                } else {
                    whsDaysPerMonth.add(whsProducedInDay);
                    whsProducedInDay = 1; // reset
                    days.add(dayCounter);
                    dayCounter++;
                }

            }

        }


        errorMessage.setText(""); // hide error message


        if (whsHoursPerDay.isEmpty())
        {
            errorMessage.setText("No data for\nchosen date");
        }
        else
        {
           createDayChart(siteIDPicked, datePicked, whsHoursPerDay, hours);
           createMonthChart(siteIDPicked, datePicked, whsDaysPerMonth, days);
           barChartDay.setVisible(true);

           dayButton.setVisible(true);
           monthButton.setVisible(true);
        }

    }


        /**
         * Create and fill data into a barchart to be shown
         * @param siteIDPicked id for the chosen site
         * @param datePicked chosen date from datepicker
         * @param whsHoursPerDay ArrayList of Watt hours found from data with given siteID
         * @param times What time of day from the chosen date
         */
    private void createDayChart(int siteIDPicked, LocalDate datePicked, ArrayList<Integer> whsHoursPerDay, ArrayList<Integer> times)
    {
        XYChart.Series<String, Integer> series = new XYChart.Series();
        series.setName("Site ID: " + siteIDPicked + "\nDate: " + datePicked.format(formatting));

        for (int i = 0; i < whsHoursPerDay.size(); i++)
        {
            series.getData().add(new XYChart.Data(times.get(i) + ":00", whsHoursPerDay.get(i)));
        }

        // replace data in barchart instead of replacing it
        barChartDay.setData(FXCollections.observableArrayList(series));
        barChartDay.setTitle(datePicked.format(formatting) + " (unit: watt-hours)");
    }


    private void createMonthChart(int siteIDPicked, LocalDate datePicked, ArrayList<Integer> whsDaysPerMonth, ArrayList<Integer> days)
    {
        XYChart.Series<String, Integer> series = new XYChart.Series();
        series.setName("Site ID: " + siteIDPicked + "\nMonth: " + datePicked.getMonth());

        for (int i = 0; i < whsDaysPerMonth.size(); i++)
        {
            series.getData().add(new XYChart.Data(days.get(i).toString(), whsDaysPerMonth.get(i)));
        }

        // replace data in barchart instead of replacing it
        barChartMonth.setData(FXCollections.observableArrayList(series));
        barChartMonth.setTitle(datePicked.getMonth() + " " + datePicked.getYear() + " (unit: watt-hours)");
    }

    public void showBarChartDay() {
        barChartMonth.setVisible(false);
        barChartDay.setVisible(true);
    }

    public void showBarChartMonth() {
        barChartDay.setVisible(false);
        barChartMonth.setVisible(true);
    }

}