package com.example.carlcastello.dosomethingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlcastello.dosomethingapp.Adapters.RecyclerViewAdapter;
import com.example.carlcastello.dosomethingapp.Controller.FavouriteListController;
import com.example.carlcastello.dosomethingapp.Controller.RecentListController;
import com.example.carlcastello.dosomethingapp.Model.Place;
import com.example.carlcastello.dosomethingapp.R;

/**
 * Created by carlcastello on 31/05/17.
 */

public class FavouritesFragment extends Fragment {
    private View view;

    public static FavouritesFragment newInstance() {
        FavouritesFragment frag = new FavouritesFragment();

        Bundle args = new Bundle();
        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_favourites, container, false);

//        JSONObject placeObject = getArguments().getSerializable("placeObject");
//        System.out.println(placeObject.toString());

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        FavouriteListController favouriteListController = new FavouriteListController(getContext());
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(favouriteListController.getFavouriteList());
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
