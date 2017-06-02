package com.example.carlcastello.dosomethingapp.Controller;

import android.content.Context;

import com.example.carlcastello.dosomethingapp.Model.FavouriteList;
import com.example.carlcastello.dosomethingapp.Model.Place;
import com.example.carlcastello.dosomethingapp.Model.PlaceList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by carlcastello on 01/06/17.
 */

public class FavouriteListController extends PlaceListController implements Serializable {
    private PlaceList favouriteList;

    public FavouriteListController(Context context) {
        super(context);
        ArrayList<Place> arrayList = new ArrayList<>();
        for (int i = 0; i < this.placeList.getSize(); ++i) {
            if (placeList.getPlace(i).isFavourite) {
                arrayList.add(this.placeList.getPlace(i));
            }
        }
        this.favouriteList = new FavouriteList(arrayList);
    }

    public PlaceList getFavouriteList() {
        return favouriteList;
    }
}
