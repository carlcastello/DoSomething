package com.example.carlcastello.dosomethingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlcastello.dosomethingapp.Model.Place;
import com.example.carlcastello.dosomethingapp.R;

/**
 * Created by carlcastello on 31/05/17.
 */

public class CategoriesFragment extends Fragment {
    private View view;

    public static CategoriesFragment newInstance() {
        CategoriesFragment frag = new CategoriesFragment();

        Bundle args = new Bundle();
        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_categories, container, false);

//        JSONObject placeObject = getArguments().getSerializable("placeObject");
//        System.out.println(placeObject.toString());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
