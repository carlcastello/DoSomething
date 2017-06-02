package com.example.carlcastello.dosomethingapp.Model;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by carlcastello on 31/05/17.
 */

public class PlaceList {
    ArrayList<Place> placeList;

    public PlaceList() {
        this.placeList = new ArrayList<>();
    }

    public PlaceList(ArrayList<Place> arrayList) {
        this.placeList = arrayList;
    }

    public ArrayList<Place> getPlaceList() {
        return this.placeList;
    }

    public int getSize() {
        return this.placeList.size();
    }

    public Place getPlace(int i) {
        return this.placeList.get(i);
    }

    public void add(Place place) {
        this.placeList.add(place);
    }

    public void replacePlace(Place place, int index) {
//        System.out.println(index);
        placeList.set(index,place);
    }
}
