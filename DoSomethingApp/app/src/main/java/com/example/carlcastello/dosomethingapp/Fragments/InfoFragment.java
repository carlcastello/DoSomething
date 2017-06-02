package com.example.carlcastello.dosomethingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlcastello.dosomethingapp.Controller.PlaceController;
import com.example.carlcastello.dosomethingapp.Controller.PlaceListController;
import com.example.carlcastello.dosomethingapp.Controller.ReviewController;
import com.example.carlcastello.dosomethingapp.Controller.ReviewListController;
import com.example.carlcastello.dosomethingapp.Model.Place;
import com.example.carlcastello.dosomethingapp.Model.Review;
import com.example.carlcastello.dosomethingapp.R;

import org.json.JSONObject;

/**
 * Created by carlcastello on 28/05/17.
 */

public class InfoFragment extends Fragment implements View.OnClickListener {

    private View view;
    private int index;
    private Place place;
    private Button favourite_button;
    private PlaceController placeController;

    public static InfoFragment newInstance(PlaceController placeController,int index) {
        InfoFragment frag = new InfoFragment();

        Bundle args = new Bundle();
        args.putInt("index",index);
        args.putSerializable("placeController", placeController);
        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_info, container, false);

        this.index = getArguments().getInt("index");

        this.placeController = (PlaceController) getArguments().getSerializable("placeController");
        ReviewListController reviewListController = new ReviewListController(placeController.getPlace().getReviewList());

        this.place = placeController.getPlace();

        TextView titleView = (TextView) view.findViewById(R.id.title_view);
        titleView.setText(place.getName());

        TextView addressView = (TextView) view.findViewById(R.id.address_view);
        addressView.setText(place.getAddress());

        TextView reviewView = (TextView) view.findViewById(R.id.review_view);

        String string = reviewListController.getString();
        reviewView.setText(string);

        this.favourite_button = (Button) view.findViewById(R.id.info_button);
        this.favourite_button.setOnClickListener(this);
        if (place.isFavourite) {
            this.favourite_button.setText("UnFavourite");
        }


//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (place.isFavourite) {
            place.isFavourite = false;
            this.favourite_button.setText("Favourite");
        } else {
            this.favourite_button.setText("UnFavourite");
            place.isFavourite = true;
        }
        PlaceListController placeListController = new PlaceListController(getContext());
        placeListController.replacePlace(place,index);
        placeListController.save();
    }

//    @Override
//    public void onBackPressed() {
//        if (mWebView.canGoBack()) {
//            mWebView.goBack();
//            return;
//        }
//
//        // Otherwise defer to system default behavior.
//        super.onBackPressed();
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super(savedInstanceState);
//        //Listen for changes in the back stack
//        getFragmentManager().addOnBackStackChangedListener(this);
//        //Handle when activity is recreated like on orientation Change
//        shouldDisplayHomeUp();
//    }
//

//    @Override
//    public boolean nNavigateUp() {
//        //This method is called when the up button is pressed. Just the pop back stack.
//        getFragmentManager().popBackStack();
//        return true;
//    }


}
