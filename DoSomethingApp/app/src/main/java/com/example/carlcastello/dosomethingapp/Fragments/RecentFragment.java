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
import com.example.carlcastello.dosomethingapp.Controller.PlaceListController;
import com.example.carlcastello.dosomethingapp.Controller.RecentListController;
import com.example.carlcastello.dosomethingapp.R;

/**
 * Created by carlcastello on 31/05/17.
 */

public class RecentFragment extends Fragment {
    private View view;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static RecentFragment newInstance() {
        RecentFragment frag = new RecentFragment();

        Bundle args = new Bundle();
        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_recent, container, false);


//        JSONObject placeObject = getArguments().getSerializable("placeObject");
//        System.out.println(placeObject.toString());


        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        PlaceListController placeListController = new PlaceListController(getContext());
        RecentListController recentListController = new RecentListController(getContext());
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(recentListController.getPlaceList());
        rv.setAdapter(adapter);
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
