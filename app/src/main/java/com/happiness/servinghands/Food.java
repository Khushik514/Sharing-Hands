package com.happiness.servinghands;

public class Food {
    public String noOfPeople, type, expiry, UID, Username, Email, Phone;

    public Food() {
    }

    public Food(String noOfPeople, String type, String expiry, String UID, String username, String email, String phone) {
        this.noOfPeople = noOfPeople;
        this.type = type;
        this.expiry = expiry;
        this.UID = UID;
        Username = username;
        Email = email;
        Phone = phone;
    }
}
