package org.example.solarproductionproject;

import java.time.LocalDate;

public class SolarData
{
    private int id;
    private LocalDate date;
    private int time;
    private int day;
    private int month;
    private int year;
    private int siteID;
    private int totalEnergyProduction;
    private int wattPerHour;

    public SolarData(int id, LocalDate date, int time, int siteID, int totalEnergyProduction, int wattPerHour)
    {
        this.id = id;
        this.time = time;
        this.date = date;
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
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

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
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