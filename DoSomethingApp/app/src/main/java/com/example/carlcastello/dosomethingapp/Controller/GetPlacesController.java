package com.example.carlcastello.dosomethingapp.Controller;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.carlcastello.dosomethingapp.Model.Search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by carlcastello on 28/05/17.
 */

public class GetPlacesController {
    private ArrayList<Boolean> categories;
    private Location location;
    private double pricePoint;
    private int radius;
    private Context context;

    public GetPlacesController(Context context, Location location, ArrayList<Boolean> categories, double pricePoint, int radius) {
        this.categories = categories;
        this.pricePoint = pricePoint;
        this.location = new Location("Mock");
        this.location.setLongitude(-113.37710959999998);
        this.location.setLatitude(53.4599596);
        this.radius = radius*1000;
        this.context = context;
    }


    public void execute() {

        ArrayList<String> arrayList = getTypes();
        Search search = new Search(arrayList,this.location,this.radius,this.pricePoint);


        GooglePlaceAPI.GetPlaceTask getPlaceTask = new GooglePlaceAPI.GetPlaceTask(context);
        getPlaceTask.execute(search);
    }

    private ArrayList<String> getTypes() {
        ArrayList<String> arrayList = new ArrayList<>();

//        if (this.categories.get(0)) {
//            arrayList.addAll(getFoodRelated());
//        }
//
//        if (this.categories.get(1)) {
//            arrayList.addAll(getNightLifeRelated());
//        }
//
//        if (this.categories.get(2)) {
//            arrayList.addAll(getActivitiesRelated());
//        }
//
//        if (this.categories.get(3)) {
//            arrayList.addAll(getMultiDayRelated());
//        }

        arrayList.add("bakery");
        return arrayList;
    }

    private ArrayList<String> getFoodRelated() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("bakery");
        arrayList.add("cafe");
        arrayList.add("convenience_store");
        arrayList.add("department_store");
        arrayList.add("gas_station");
        arrayList.add("meal_delivery");
        arrayList.add("meal_takeaway");
        arrayList.add("restaurant");
        return arrayList;
    }

    private ArrayList<String> getNightLifeRelated() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("casino");
        arrayList.add("liquor_store");
        arrayList.add("night_club");
        return arrayList;
    }

    private ArrayList<String> getActivitiesRelated(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("amusement_park");
        arrayList.add("aquarium");
        arrayList.add("art_gallery");
        arrayList.add("beauty_salon");
        arrayList.add("bowling_alley");
        arrayList.add("casino");
        arrayList.add("clothing_store");
        arrayList.add("gym");
        arrayList.add("hair_care");
        arrayList.add("movie_rental");
        arrayList.add("movie_theater");
        arrayList.add("physiotherapist");
        arrayList.add("shopping_mall");
        arrayList.add("spa");
        arrayList.add("zoo");
        arrayList.add("stadium");
        return arrayList;
    }

    public ArrayList<String> getMultiDayRelated() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("campground");
        arrayList.add("lodging");
        arrayList.add("travel_agency");
        arrayList.add("rv_park");
        return arrayList;
    }
}
