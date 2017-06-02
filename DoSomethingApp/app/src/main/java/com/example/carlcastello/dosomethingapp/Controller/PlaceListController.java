package com.example.carlcastello.dosomethingapp.Controller;

import android.content.Context;

import com.example.carlcastello.dosomethingapp.Model.Place;
import com.example.carlcastello.dosomethingapp.Model.PlaceList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by carlcastello on 31/05/17.
 */


public class PlaceListController implements Serializable {

    protected PlaceList placeList;
    protected Context context;
    protected static final String FILENAME = "recent.sav";

    public PlaceListController(Context context) {
        this.context = context;
        loadFromFile();
    }

    public PlaceList getPlaceList() {
        return this.placeList;
    }

    public void addPlace(Place place) {
        this.placeList.add(place);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));


//            String message = org.apache.commons.io.IOUtils.toString(in);
//            System.out.println(message);

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Place>>(){}.getType();
            ArrayList<Place> jsonList = gson.fromJson(in,listType);

//            System.out.println(jsonList.get(0));
            this.placeList = new PlaceList(jsonList);


            in.close();
            fis.close();
        } catch (FileNotFoundException e) {
            this.placeList = new PlaceList();
        } catch (IOException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        }
    }

    //This will save the data into a file
    public void save() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(this.placeList.getPlaceList(), out);
            out.flush();

//            out.close();
            fos.close();
        } catch (FileNotFoundException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        } catch (IOException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        }
    }

    public void replacePlace(Place place, int index) {
        this.placeList.replacePlace(place,index);
    }

    public int getSize() {
        return placeList.getSize();
    }
}
