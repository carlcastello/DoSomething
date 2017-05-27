package com.example.carlcastello.dosomethingapp.Fragments;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.carlcastello.dosomethingapp.Dialog.CategoryDialog;
import com.example.carlcastello.dosomethingapp.Dialog.PricePointDialog;
import com.example.carlcastello.dosomethingapp.Dialog.RadiusDialog;
import com.example.carlcastello.dosomethingapp.R;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by carlcastello on 26/05/17.
 */

public class MainFragment extends Fragment implements View.OnClickListener{

    private TextView categoryEdit;
    private TextView pricePointEdit;
    private TextView radiusEdit;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        this.categoryEdit = (TextView) view.findViewById(R.id.category_edit);
        this.pricePointEdit = (TextView) view.findViewById(R.id.price_point_edit);
        this.radiusEdit = (TextView) view.findViewById(R.id.radius_edit);

        this.categoryEdit.setOnClickListener(this);
        this.pricePointEdit.setOnClickListener(this);
        this.radiusEdit.setOnClickListener(this);


        return view;
    }

    public void onClick(View v) {

        switch(v.getId()) {
            // Just like you were doing
            case R.id.category_edit:
                DialogFragment dialogFragment = new CategoryDialog();
                dialogFragment.show(getFragmentManager(),"category-dialog");
                break;
            case R.id.price_point_edit:
                PricePointDialog pricePointDialog = new PricePointDialog();
                pricePointDialog.show(getFragmentManager(),"price-point-dialog");
                break;
            case R.id.radius_edit:
                RadiusDialog radiusDialog = new RadiusDialog();
                radiusDialog.show(getFragmentManager(),"radius-dialog");
                break;
        }
    }
}
