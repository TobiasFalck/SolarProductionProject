package org.example.solarproductionproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadData
{
    /**
     * Reads a file located at a given path to create SolarData objects
     * witch are then returned as a list
     * @param filepath Path to the file to read
     * @return ArrayList of SolarData to be used in program
     * @throws FileNotFoundException if the given path does not exist
     */
    public static ArrayList<SolarData> readFileData(String filepath) throws FileNotFoundException
    {
        int id = 0;
        String date = "";
        String time = "";
        int siteID = 0;
        int totalEnergyProduction = 0;
        int wattPerHour = 0;

        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // set formatting for date
        ArrayList<SolarData> dataset = new ArrayList<SolarData>();

        File inputFile = new File(filepath);
        Scanner in = new Scanner(inputFile).useDelimiter("[\t|T]"); // split by tabs and T

        in.nextLine(); // skip first line (tsv table header)

        // go through each word with scanner
        while (in.hasNext())
        {
            // read data from tsv file
            id = in.nextInt();
            date = in.next();
            time = in.next();
            siteID = in.nextInt();
            totalEnergyProduction = in.nextInt();
            wattPerHour = in.nextInt();

            in.nextLine();

            // convert date and time to the appropriate datatype and class
            LocalDate dateConverted = LocalDate.parse(date, formatting);
            int timeConverted = Integer.parseInt(time.substring(0, 2));

            // add SolarData objects with arguments to arraylist
            dataset.add(new SolarData(id, dateConverted, timeConverted, siteID, totalEnergyProduction, wattPerHour));
        }
        return dataset;
    }
}