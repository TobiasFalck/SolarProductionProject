package org.example.solarproductionproject;

import java.time.LocalDate;

public class SolarData
{
    private int id;
    private LocalDate date;
    private int time;
    private int siteID;
    private int totalEnergyProduction;
    private int wattPerHour;

    public SolarData(int id, LocalDate date, int time, int siteID, int totalEnergyProduction, int wattPerHour)
    {
        this.id = id;
        this.time = time;
        this.date = date;
        this.siteID = siteID;
        this.totalEnergyProduction = totalEnergyProduction;
        this.wattPerHour = wattPerHour;

    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public int getSiteID() {
        return siteID;
    }

    public int getTotalEnergyProduction() {
        return totalEnergyProduction;
    }

    public int getWattPerHour() {
        return wattPerHour;
    }

}