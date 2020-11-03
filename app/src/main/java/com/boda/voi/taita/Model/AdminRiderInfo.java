package com.boda.voi.taita.Model;

public class AdminRiderInfo {

    private  String riderName,riderlocation,riderPhone;

    public AdminRiderInfo() {
    }

    public AdminRiderInfo(String riderName, String riderLocation, String riderPhone) {
        this.riderName = riderName;
        this.riderlocation = riderLocation;
        this.riderPhone = riderPhone;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderLocation() {
        return riderlocation;
    }

    public void setRiderLocation(String riderLocation) {
        this.riderlocation = riderLocation;
    }

    public String getRiderPhone() {
        return riderPhone;
    }

    public void setRiderPhone(String riderPhone) {
        this.riderPhone = riderPhone;
    }
}
