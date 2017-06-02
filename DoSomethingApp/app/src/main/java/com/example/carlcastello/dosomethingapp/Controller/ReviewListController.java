package com.example.carlcastello.dosomethingapp.Controller;

import com.example.carlcastello.dosomethingapp.Model.Review;
import com.example.carlcastello.dosomethingapp.Model.ReviewList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by carlcastello on 31/05/17.
 */

public class ReviewListController implements Serializable{
    ReviewList reviewList;

    public ReviewListController(JSONArray jsonArray) {
        this.reviewList = new ReviewList();

        for (int i = 0; i < jsonArray.length(); ++i) {
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                ReviewController reviewController = new ReviewController(jsonObject);
                this.reviewList.addReview(reviewController.getReview());
            } catch (JSONException error) {
                System.err.println(error);
            }
        }
    }

    public ReviewListController(ReviewList reviewList) {
        this.reviewList = reviewList;
    }

    public ReviewList getReviewList() {
        return this.reviewList;
    }

    public Review getReview(int i) {
        return this.reviewList.getReview(i);
    }

    public String getString() {
        String string = "";
        for (int i = 0; i < this.reviewList.getSize(); ++i) {
            string += this.reviewList.getReview(i).getString();
        }
        return string;
    }
}
