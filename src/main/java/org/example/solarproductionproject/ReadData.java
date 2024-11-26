package org.example.solarproductionproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadData {
    static int id = 0;
    static String date = "";
    static String time = "";
    static int siteID = 0;
    static int totalEnergyProduction = 0;
    static int online = 0;

    static DateTimeFormatter formatting = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // set formatting for date
    static ArrayList<SolarData> dataset = new ArrayList<SolarData>();

    public static ArrayList<SolarData> readFileData(String filepath) throws FileNotFoundException {

        File inputFile = new File(filepath);
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
            return dataset;
    }
}