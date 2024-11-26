package org.example.solarproductionproject;

public class SolarData {
    private int id;
    private String date;
    private String time;
    private int siteID;
    private int totalEnergyProduction;
    private int online;

    public SolarData(int id, String date, String time, int siteID, int totalEnergyProduction, int online) {
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

    public String getDate() {
        return date;
    }

    public String getTime() {
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