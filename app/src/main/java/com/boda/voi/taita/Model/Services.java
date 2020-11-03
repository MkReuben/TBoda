package com.boda.voi.taita.Model;


public class Services {


    private String riderName, bikeDescription, image, riderlocation, rid, date, time, serviceState, riderPhone;


    public Services() {


    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getBikeDescription() {
        return bikeDescription;
    }

    public void setBikeDescription(String bikeDescription) {
        this.bikeDescription = bikeDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRiderLocation() {
        return riderlocation;
    }

    public void setRiderLocation(String riderLocation) {
        this.riderlocation = riderLocation;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getServiceState() {
        return serviceState;
    }

    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
    }

    public String getRiderPhone() {
        return riderPhone;
    }

    public void setRiderPhone(String riderPhone) {
        this.riderPhone = riderPhone;
    }

    public Services(String riderName, String bikeDescription, String image, String riderLocation, String rid, String date, String time, String serviceState, String riderPhone) {
        this.riderName = riderName;
        this.bikeDescription = bikeDescription;
        this.image = image;
        this.riderlocation = riderLocation;
        this.rid = rid;
        this.date = date;
        this.time = time;
        this.serviceState = serviceState;
        this.riderPhone = riderPhone;
    }


}