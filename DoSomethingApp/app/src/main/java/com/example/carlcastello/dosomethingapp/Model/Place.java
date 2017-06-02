package com.example.carlcastello.dosomethingapp.Model;

import com.example.carlcastello.dosomethingapp.Controller.ReviewListController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by carlcastello on 30/05/17.
 */

public class Place implements Serializable{

    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    private String website;
    private String url;
    private double rating;

    private ReviewList reviewList;

    private String description = "Lorem Ipsum";
    public boolean isFavourite = false;

    public Place(String id, String name, String address, double latitude, double longitude, String website, String url, double rating, ReviewList reviewList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.website = website;
        this.url = url;
        this.rating = rating;
        this.reviewList = reviewList;

//        System.out.println(getId());
//        System.out.println(getName());
//        System.out.println(getAddress());
//        System.out.println(getLatitude());
//        System.out.println(getLongitude());
//        System.out.println(getWebsite());
//        System.out.println(getUrl());
//        System.out.println(getRating());
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getUrl() {
        return this.url;
    }

    public double getRating() {
        return this.rating;
    }

    public ReviewList getReviewList() {
        return this.reviewList;
    }

    public String getDescription() {
        return description;
    }

    public String getString() {
        String string = "";

        string += "Website: " + getWebsite() + "\n";
        string += "Url: " + getUrl() + "\n";

        return string;
    }
}
