package com.example.carlcastello.dosomethingapp.Controller;

import com.example.carlcastello.dosomethingapp.Model.Place;
import com.example.carlcastello.dosomethingapp.Model.Review;
import com.example.carlcastello.dosomethingapp.Model.ReviewList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by carlcastello on 31/05/17.
 */

public class PlaceController implements Serializable {
    private Place place;


    public PlaceController(JSONObject jsonObject) {
        try {
            JSONObject placeJson = jsonObject.getJSONObject("result");

            String id = placeJson.getString("place_id");
            String name = placeJson.getString("name");
            String address = placeJson.getString("vicinity");
            JSONObject geometryObject = (JSONObject) placeJson.get("geometry");
            JSONObject locationObject = (JSONObject) geometryObject.get("location");
            double latitude = locationObject.getDouble("lat");
            double longitude = locationObject.getDouble("lng");

            String website = placeJson.getString("website");
            String url = placeJson.getString("url");
            double rating = placeJson.getDouble("rating");

            JSONArray jsonArray = placeJson.getJSONArray("reviews");

            ReviewListController reviewListController = new ReviewListController(jsonArray);
            ReviewList reviewList = reviewListController.getReviewList();

            this.place = new Place(id,name,address,latitude,longitude,website,url,rating,reviewList);

        } catch (JSONException error) {

        }
    }

    public PlaceController(Place place) {
        this.place = place;
    }

    public Place getPlace() {
        return this.place;
    }

}
