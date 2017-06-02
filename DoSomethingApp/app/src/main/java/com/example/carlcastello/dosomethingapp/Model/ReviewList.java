package com.example.carlcastello.dosomethingapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by carlcastello on 31/05/17.
 */

public class ReviewList implements Serializable {
    ArrayList<Review> reviews = new ArrayList<>();

    public ReviewList() {
    }

    public ReviewList(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public int getSize() {
        return this.reviews.size();
    }

    public Review getReview(int i) {
        return this.reviews.get(i);
    }
}
