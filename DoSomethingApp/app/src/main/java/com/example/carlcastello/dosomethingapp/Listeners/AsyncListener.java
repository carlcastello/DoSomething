package com.example.carlcastello.dosomethingapp.Listeners;

import com.example.carlcastello.dosomethingapp.Controller.PlaceController;
import com.example.carlcastello.dosomethingapp.Model.Place;

import org.json.JSONObject;

/**
 * Created by carlcastello on 29/05/17.
 */

public interface AsyncListener {
    public abstract void googlePlaceData(PlaceController placeController);

}
