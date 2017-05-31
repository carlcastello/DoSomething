package com.example.carlcastello.dosomethingapp.Model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by carlcastello on 30/05/17.
 */

public class Place implements Serializable {

    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private ArrayList<String> types;

    public Place(JSONObject jsonObject) {

    }
}
