package com.example.carlcastello.dosomethingapp.Controller;

import com.example.carlcastello.dosomethingapp.Model.Review;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by carlcastello on 31/05/17.
 */

public class ReviewController implements Serializable {
    Review review;

    public ReviewController(JSONObject jsonObject) {
        try {
            int ratings = jsonObject.getInt("rating");
            String text = jsonObject.getString("text");
            String author = jsonObject.getString("author_name");
            BigInteger date = new BigInteger(jsonObject.getString("time"));

            review = new Review(ratings,text,author,date);

        } catch (JSONException error) {
            System.err.println(error);
        }
    }

    public ReviewController(Review review) {
        this.review = review;
    }

    public Review getReview() {
        return this.review;
    }

}
