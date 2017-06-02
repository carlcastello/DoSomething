package com.example.carlcastello.dosomethingapp.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.carlcastello.dosomethingapp.Listeners.AsyncListener;
import com.example.carlcastello.dosomethingapp.Model.Place;
import com.example.carlcastello.dosomethingapp.Model.Search;
import com.example.carlcastello.dosomethingapp.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

//            JSONObject jsonObject = getRandomPlace(arrayList);
//            for (int i = 0; i < 1000000000; ++i){}
//
//            JSONObject placeObject = null;
//            try {
//                placeObject = new JSONObject("{\"html_attributions\" : [],\"result\" : {\"address_components\" : [{\"long_name\" : \"48\", \"short_name\" : \"48\", \"types\" : [ \"street_number\" ] }, {\"long_name\" : \"Pirrama Road\", \"short_name\" : \"Pirrama Road\", \"types\" : [ \"route\" ] }, {\"long_name\" : \"Pyrmont\", \"short_name\" : \"Pyrmont\", \"types\" : [ \"locality\", \"political\" ] }, {\"long_name\" : \"NSW\", \"short_name\" : \"NSW\", \"types\" : [ \"administrative_area_level_1\", \"political\" ] }, {\"long_name\" : \"AU\", \"short_name\" : \"AU\", \"types\" : [ \"country\", \"political\" ] }, {\"long_name\" : \"2009\", \"short_name\" : \"2009\", \"types\" : [ \"postal_code\" ] } ], \"adr_address\" : \"5, \\u003cspan class=\\\"street-address\\\"\\u003e48 Pirrama Rd\\u003c/span\\u003e, \\u003cspan class=\\\"locality\\\"\\u003ePyrmont\\u003c/span\\u003e \\u003cspan class=\\\"region\\\"\\u003eNSW\\u003c/span\\u003e \\u003cspan class=\\\"postal-code\\\"\\u003e2009\\u003c/span\\u003e, \\u003cspan class=\\\"country-name\\\"\\u003eAustralia\\u003c/span\\u003e\", \"formatted_address\" : \"48 Pirrama Road, Pyrmont NSW, Australia\", \"formatted_phone_number\" : \"(02) 9374 4000\", \"geometry\" : {\"location\" : {\"lat\" : -33.8669710, \"lng\" : 151.1958750 }, \"viewport\" : {\"northeast\" : {\"lat\" : -33.8665053, \"lng\" : 151.1960371 }, \"southwest\" : {\"lat\" : -33.8669293, \"lng\" : 151.1952183 } } }, \"icon\" : \"http://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png\", \"id\" : \"4f89212bf76dde31f092cfc14d7506555d85b5c7\", \"international_phone_number\" : \"+61 2 9374 4000\", \"name\" : \"Google Sydney\", \"place_id\" : \"ChIJN1t_tDeuEmsRUsoyG83frY4\", \"scope\" : \"GOOGLE\", \"alt_ids\" : [{\"place_id\" : \"D9iJyWEHuEmuEmsRm9hTkapTCrk\", \"scope\" : \"APP\"} ], \"rating\" : 4.70, \"reference\" : \"CnRsAAAA98C4wD-VFvzGq-KHVEFhlHuy1TD1W6UYZw7KjuvfVsKMRZkbCVBVDxXFOOCM108n9PuJMJxeAxix3WB6B16c1p2bY1ZQyOrcu1d9247xQhUmPgYjN37JMo5QBsWipTsnoIZA9yAzA-0pnxFM6yAcDhIQbU0z05f3xD3m9NQnhEDjvBoUw-BdcocVpXzKFcnMXUpf-nkyF1w\", \"reviews\" : [{\"aspects\" : [{\"rating\" : 3, \"type\" : \"quality\"} ], \"author_name\" : \"Simon Bengtsson\", \"author_url\" : \"https://plus.google.com/104675092887960962573\", \"language\" : \"en\", \"rating\" : 5, \"text\" : \"Just went inside to have a look at Google. Amazing.\", \"time\" : 1338440552869 }, {\"aspects\" : [{\"rating\" : 3, \"type\" : \"quality\"} ], \"author_name\" : \"Felix Rauch Valenti\", \"author_url\" : \"https://plus.google.com/103291556674373289857\", \"language\" : \"en\", \"rating\" : 5, \"text\" : \"Best place to work :-)\", \"time\" : 1338411244325 }, {\"aspects\" : [{\"rating\" : 3, \"type\" : \"quality\"} ], \"author_name\" : \"Chris\", \"language\" : \"en\", \"rating\" : 5, \"text\" : \"Great place to work, always lots of free food!\", \"time\" : 1330467089039 } ], \"types\" : [ \"establishment\" ], \"url\" : \"http://maps.google.com/maps/place?cid=10281119596374313554\", \"vicinity\" : \"48 Pirrama Road, Pyrmont\", \"website\" : \"http://www.google.com.au/\"}, \"status\" : \"OK\"}");
//            } catch (JSONException error) {
//                System.err.println(error);
//            }
//            return jsonObject;

            ArrayList<JSONObject> arrayList = getPlacesArray(parameters);
            JSONObject jsonObject = getRandomPlace(arrayList);
            JSONObject placeDetail = getPlaceDetail(jsonObject);

//            try {
//                JSONArray typeArray = jsonObject.getJSONArray("typeSearch");
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



            return placeDetail;
        }

        private ArrayList<JSONObject> getPlacesArray(Search searchParameters) {
            final String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
            ArrayList<String> typeArray = searchParameters.getCategories();
            String locationString = searchParameters.getLocationString();
            String radiusString = searchParameters.getRadiusString();

            ArrayList<JSONObject> arrayList = new ArrayList<>();
            for (int i = 0; i < typeArray.size(); ++i) {
                String typeString = "type=" + typeArray.get(i);
                String parameterURL = urlString+locationString+"&"+radiusString+"&"+typeString+"&key="+this.googleAPIKey;
                try {
                    URL url = new URL(parameterURL);
                    HttpURLConnection client = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = new BufferedInputStream(client.getInputStream());
                    String result = IOUtils.toString(inputStream, "UTF-8");
                    inputStream.close();

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
//                    System.out.println(jsonObject.toString());
                } catch (JSONException error) {
                    System.err.println(error.toString());
                }

            }
            return jsonObject;
        }

        private JSONObject getPlaceDetail(JSONObject jsonObject) {
            JSONObject placeObject = null;
            String urlString = "https://maps.googleapis.com/maps/api/place/details/json?";

            try {
                urlString += "placeid=" + jsonObject.getString("place_id")+"&key="+googleAPIKey;
                System.out.println(urlString);
                URL url = new URL(urlString);
                HttpURLConnection client = (HttpURLConnection) url.openConnection();

                InputStream inputStream = new BufferedInputStream(client.getInputStream());
                String result = IOUtils.toString(inputStream, "UTF-8");
                inputStream.close();

                placeObject = new JSONObject(result);
            } catch (JSONException error) {
                System.out.println(error);
            } catch (MalformedURLException error) {
                System.out.println(error);
            } catch (IOException error) {
                System.out.println(error);
            }

            System.out.println(placeObject.toString());
            return placeObject;
        }
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            PlaceController placeController = new PlaceController(jsonObject);
            AsyncListener asyncListener = (AsyncListener) ContextAsync;
            asyncListener.googlePlaceData(placeController);
            this.dialog.dismiss();
        }

    }
}
