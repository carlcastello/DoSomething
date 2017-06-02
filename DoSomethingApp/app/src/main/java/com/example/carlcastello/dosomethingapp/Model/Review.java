package com.example.carlcastello.dosomethingapp.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by carlcastello on 31/05/17.
 */

public class Review implements Serializable {

    private int ratings;
    private String text;
    private String author;
    private BigInteger date;

    public Review(int ratings, String text, String author, BigInteger date) {
        this.ratings = ratings;
        this.text = text;
        this.author = author;
        this.date = date;
//
//        System.out.println("----------------------------");
//        System.out.println(getRatings());
//        System.out.println(getText());
//        System.out.println(getAuthor());
//        System.out.println(getDate());
    }


    //    public Review(JSONObject jsonObject) {
//        try {
//            this.ratings = jsonObject.getInt("rating");
//            this.text = jsonObject.getString("text");
//            this.author = jsonObject.getString("author_name");
//            this.date = jsonObject.getInt("time");
//
//        } catch (JSONException error) {
//            System.err.println(error);
//        }
//
//        System.out.println("----------------------------");
//        System.out.println(getRatings());
//        System.out.println(getText());
//        System.out.println(getAuthor());
//        System.out.println(getDate());
//    }

    public int getRatings() {
        return this.ratings;
    }

    public String getText() {
        return this.text;
    }

    public String getAuthor() {
        return this.author;
    }

    public BigInteger getDate() {
        return this.date;
    }

    public String getString() {
        String string = "";

        string += "Ratings: " + "" + getRatings() + "\n";
        string += "Text: " + "" + getText() + "\n";
        string += "Author: " + "" + getAuthor() + "\n";

        return string;
    }
}
