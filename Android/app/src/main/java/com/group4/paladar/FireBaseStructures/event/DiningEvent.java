package com.group4.paladar.FireBaseStructures.event;

import java.util.List;

/**
 * Created by Surface on 2016-02-23.
 */
public class DiningEvent {

    private String title;
    private String summary;

    private String foodImage;

    private String HostId;

    private int Length; // Length in minutes that the event is.
    private int price; // price currency
    private int seats; // number of seats available.

    int eventYear = 0;
    int eventMonth = 0;
    int eventDay = 0;

    int eventHour = -1;
    int eventMinute = -1;



    private String address;
    private String HomeImage;
    private HomePrefs homePrefs;


    //TODO get more data of the food that is served
    private FoodContiants foodContatins;

    private List<String> Attendings;

    private Double log;
    private Double lat;

    public DiningEvent(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getHostId() {
        return HostId;
    }

    public void setHostId(String hostId) {
        HostId = hostId;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }



    public HomePrefs getHomePrefs() {
        return homePrefs;
    }

    public void setHomePrefs(HomePrefs homePrefs) {
        this.homePrefs = homePrefs;
    }

    public FoodContiants getFoodContatins() {
        return foodContatins;
    }

    public void setFoodContatins(FoodContiants foodContatins) {
        this.foodContatins = foodContatins;
    }

    public List<String> getAttendings() {
        return Attendings;
    }

    public void setAttendings(List<String> attendings) {
        Attendings = attendings;
    }

    public Double getLog() {
        return log;
    }

    public void setLog(Double log) {
        this.log = log;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomeImage() {
        return HomeImage;
    }

    public void setHomeImage(String homeImage) {
        HomeImage = homeImage;
    }

    public int getEventYear() {
        return eventYear;
    }

    public int getEventMonth() {
        return eventMonth;
    }

    public int getEventDay() {
        return eventDay;
    }

    public int getEventHour() {
        return eventHour;
    }

    public int getEventMinute() {
        return eventMinute;
    }

    public void setDate(int eventYear, int eventMonth, int eventDay, int eventHour, int eventMinute) {
        this.eventYear = eventYear;
        this.eventMonth = eventMonth;
        this.eventDay = eventDay;
        this.eventHour = eventHour;
        this.eventMinute = eventMinute;
    }
}
