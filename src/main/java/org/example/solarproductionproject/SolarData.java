package org.example.solarproductionproject;

import java.util.Date;

public class SolarData {
    private int id;
    private Date date;
    private int hour;
    private int siteID;
    private int totalEnergyProduction;
    private int online;

    public SolarData(int id, Date date, int hour, int siteID, int totalEnergyProduction, int online) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.siteID = siteID;
        this.totalEnergyProduction = totalEnergyProduction;
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getHour() {
        return hour;
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