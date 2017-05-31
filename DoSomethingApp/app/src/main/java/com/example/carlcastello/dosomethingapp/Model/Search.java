package com.example.carlcastello.dosomethingapp.Model;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by carlcastello on 30/05/17.
 */

public class Search implements Serializable {
    private ArrayList<String> categories;
    private double longitude;
    private double latitude;
    private double radius;
    private double price;

    public Search(ArrayList<String> categories, Location location, double radius, double price) {
        this.categories = categories;
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.price = price;
        this.radius = radius;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getLocationString() {
        return "location="+""+latitude+","+""+longitude;
    }

    public String getRadiusString() {
        return "radius="+""+radius;
    }

    public String getPriceString() {
        return "" + price;
    }
}
