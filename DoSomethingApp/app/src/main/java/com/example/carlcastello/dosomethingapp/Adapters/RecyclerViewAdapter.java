package com.example.carlcastello.dosomethingapp.Adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlcastello.dosomethingapp.Controller.PlaceController;
import com.example.carlcastello.dosomethingapp.Fragments.InfoFragment;
import com.example.carlcastello.dosomethingapp.Model.PlaceList;
import com.example.carlcastello.dosomethingapp.R;

/**
 * Created by carlcastello on 01/06/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PlaceViewHolder> {

    PlaceList placeList;

    public RecyclerViewAdapter(PlaceList placeList){
        this.placeList = placeList;
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView placeName;
        TextView placeAddress;
        ImageView placePhoto;
        TextView placeRatings;

        PlaceViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            placeName = (TextView) itemView.findViewById(R.id.place_name);
            placeAddress = (TextView) itemView.findViewById(R.id.place_address);
            placePhoto = (ImageView) itemView.findViewById(R.id.place_photo);
            placeRatings = (TextView) itemView.findViewById(R.id.place_ratings);

        }
    }

    @Override
    public int getItemCount() {
        return placeList.getSize();
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_card, viewGroup, false);
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(v);
        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder placeViewHolder, int i) {
        placeViewHolder.placeName.setText(placeList.getPlace(i).getName());
        placeViewHolder.placeAddress.setText(placeList.getPlace(i).getAddress());
//        placeViewHolder.placePhoto.setImageResource(placeList.getPlace(i).photoId);
        placeViewHolder.placeRatings.setText("" + placeList.getPlace(i).getRating());

        final int index = i;
        placeViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();


                PlaceController placeController = new PlaceController(placeList.getPlace(index));
                InfoFragment infoFragment = new InfoFragment().newInstance(placeController,index);
                transaction.replace(R.id.fragment_container, infoFragment, "mainFragment");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
