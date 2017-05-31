package com.example.carlcastello.dosomethingapp.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.carlcastello.dosomethingapp.Listeners.AsyncListener;
import com.example.carlcastello.dosomethingapp.Model.Search;
import com.example.carlcastello.dosomethingapp.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by carlcastello on 28/05/17.
 */

public class GooglePlaceAPI {
    public static class GetPlaceTask extends AsyncTask<Search, Void, JSONObject> {
        private Context ContextAsync;
        private ProgressDialog dialog;

        private final String googleAPIKey = "AIzaSyBvMtsTrMaArVyD8P0JwNHWkD5Z4EddyD8";
        private final String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

        public GetPlaceTask(Context context) {
            this.ContextAsync = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog = ProgressDialog.show(ContextAsync, "Loading", "Please wait...", true);

        }

        @Override
        protected JSONObject doInBackground(Search... params) {

            Search parameters = params[0];

//            ArrayList<JSONObject> arrayList = getPlacesArray(parameters);
//            JSONObject jsonObject = getRandomPlace(arrayList);
            for (int i = 0; i < 1000000000; ++i){}

            return null;

//            try {
//                JSONArray typeArray = jsonObject.getJSONArray("typeSearch");
//
//                double latitude = jsonObject.getJSONObject("location").getDouble("latitude");
//                double longitude = jsonObject.getJSONObject("location").getDouble("longitude");
//                double radius = jsonObject.getDouble("radius");
//                double price = jsonObject.getDouble("pricePoint");
//
//
//                int low = 0;
//                int high = jsonArray.length();
//
//
//                if (high > 0) {
//                    try {
//                        // Randomization of the category
//                        int randomOne = randomGenerator.nextInt(high - low) - low;
//                        JSONObject placesObject = (JSONObject) jsonArray.get(randomOne);
//
//                        // Get the results for each category
//                        JSONArray placesArray = (JSONArray) placesObject.get("results");
//
//                        // Randomization of places
//                        int randomTwo = randomGenerator.nextInt(placesArray.length() - low) - low;
//                        JSONObject placeObject = (JSONObject) placesArray.get(randomTwo);
//
//
//
//                    } catch (JSONException error) {
//                        System.err.println(error);
//                    }
//                } else {
//
//                }
//
//
//            } catch (Exception error) {
//                Log.i("Error",error.toString());
//            } finally {
//                if (client != null) {
//                    client.disconnect();
//                }
//            }
        }

        private ArrayList<JSONObject> getPlacesArray(Search searchParameters) {

            ArrayList<String> typeArray = searchParameters.getCategories();
            String locationString = searchParameters.getLocationString();
            String radiusString = searchParameters.getRadiusString();

            ArrayList<JSONObject> arrayList = new ArrayList<>();
            for (int i = 0; i < typeArray.size(); ++i) {
                String typeString = "type=" + typeArray.get(i);
                String parameterURL = this.urlString+locationString+"&"+radiusString+"&"+typeString+"&key="+this.googleAPIKey;
                try {
                    URL url = new URL(parameterURL);
                    HttpURLConnection client = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = new BufferedInputStream(client.getInputStream());
                    String result = IOUtils.toString(inputStream, "UTF-8");
                    inputStream.close();

                    System.out.println(parameterURL);
                    System.out.println(result);
                    JSONObject placeObject = new JSONObject(result);

                    if (placeObject.getString("status").matches("OK")) {
                        arrayList.add(placeObject);
                    }

                    client.disconnect();

                } catch (Exception error) {
                    System.err.println(error);
                }
            }
            return arrayList;
        }

        private JSONObject getRandomPlace(ArrayList<JSONObject> arrayList) {
            JSONObject jsonObject = null;

            Random randomGenerator = new Random();
            int numberOfCategories = arrayList.size();
            if (numberOfCategories > 0) {
                // Randomization of the category
                int randomOne = randomGenerator.nextInt(numberOfCategories);
                JSONObject placesObject = arrayList.get(randomOne);

                try {
                    // Get the results for each category
                    JSONArray placesArray = (JSONArray) placesObject.get("results");

                    // Randomization of places
                    int randomTwo = randomGenerator.nextInt(placesArray.length());
                    jsonObject = (JSONObject) placesArray.get(randomTwo);
                    System.out.println(jsonObject.toString());
                } catch (JSONException error) {
                    System.err.println(error.toString());
                }

            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            AsyncListener asyncListener = (AsyncListener) ContextAsync;
            asyncListener.googlePlaceData(jsonObject);
            this.dialog.dismiss();
        }

    }
}
