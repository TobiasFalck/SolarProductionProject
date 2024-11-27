package org.example.solarproductionproject;

import java.time.LocalDate;

public class SolarData {
    private int id;
    private LocalDate date;
    private int time;
    private int siteID;
    private int totalEnergyProduction;
    private int online;

    public SolarData(int id, LocalDate date, int time, int siteID, int totalEnergyProduction, int online) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.siteID = siteID;
        this.totalEnergyProduction = totalEnergyProduction;
        this.online = online;
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

    public int getOnline() {
        return online;
    }
}