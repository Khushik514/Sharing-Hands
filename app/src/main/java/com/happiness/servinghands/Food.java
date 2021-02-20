package com.happiness.servinghands;

public class Food {
    public String noOfPeople, type, expiry;

    public Food() {
    }

    public Food(String noOfPeople, String type, String expiry) {
        this.noOfPeople = noOfPeople;
        this.type = type;
        this.expiry = expiry;
    }
}
