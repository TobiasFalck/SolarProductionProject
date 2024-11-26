package org.example.solarproductionproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SolarController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        int id = 0;
        String date = "";
        String time = "";
        int siteID = 0;
        int totalEnergyProduction = 0;
        int online = 0;

        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // set formatting for date
        ArrayList<SolarData> dataset = new ArrayList<SolarData>();

        File inputFile = new File("C:\\Users\\simo0\\Desktop\\solardata.tsv");
        Scanner in = new Scanner(inputFile).useDelimiter("[\t|T]"); // split by tabs and T

        in.nextLine(); // skip first line (tsv table header)

        while (in.hasNext()) {
            // read data from tsv file
            id = in.nextInt();
            date = in.next();
            time = in.next();
            siteID = in.nextInt();
            totalEnergyProduction = in.nextInt();
            online = in.nextInt();

            in.nextLine();

            // convert date and time to the appropriate datatype and class
            LocalDate dateConverted = LocalDate.parse(date, formatting);
            int timeConverted = Integer.parseInt(time.substring(0, 2));

            // add SolarData objects with arguments to arraylist
            dataset.add(new SolarData(id, dateConverted, timeConverted, siteID, totalEnergyProduction, online));
        }

        System.out.println(dataset.get(0).getDate());
    }

}