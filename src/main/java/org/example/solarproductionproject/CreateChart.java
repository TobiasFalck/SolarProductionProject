package org.example.solarproductionproject;

import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;

public class CreateChart {
    public CreateChart() {}

    public void createChart() {
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
