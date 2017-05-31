package com.example.carlcastello.dosomethingapp.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.carlcastello.dosomethingapp.Dialog.CategoryDialog;
import com.example.carlcastello.dosomethingapp.Dialog.PricePointDialog;
import com.example.carlcastello.dosomethingapp.Dialog.RadiusDialog;
import com.example.carlcastello.dosomethingapp.Listeners.DialogListener;
import com.example.carlcastello.dosomethingapp.Listeners.FragmentListener;
import com.example.carlcastello.dosomethingapp.R;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by carlcastello on 26/05/17.
 */

public class MainFragment extends Fragment implements View.OnClickListener, DialogListener{

    private View view;
    private Button proceedButton;
    private TextView radiusEdit;
    private TextView categoryEdit;
    private TextView pricePointEdit;

    private RelativeLayout priceGroup;
    private RelativeLayout radiusGroup;

    private ArrayList<Boolean> categoriesSelected;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_main, container, false);

        // Updating the text in the main fragment
        this.radiusEdit = (TextView) view.findViewById(R.id.radius_edit);

        // onButtonClick
        this.proceedButton = (Button) this.view.findViewById(R.id.proceed_button);

        this.proceedButton.setOnClickListener(this);

        // ClickListener
        this.categoryEdit = (TextView) this.view.findViewById(R.id.category_edit);
        this.pricePointEdit = (TextView) this.view.findViewById(R.id.price_point_edit);
        this.radiusGroup = (RelativeLayout) this.view.findViewById(R.id.radius_group);
        this.priceGroup = (RelativeLayout) this.view.findViewById(R.id.price_group);

        this.categoryEdit.setOnClickListener(this);
        this.priceGroup.setOnClickListener(this);
        this.radiusGroup.setOnClickListener(this);

        // Initializing the category isCheckList
        this.categoriesSelected = new ArrayList<>();
        for (int i = 0; i < getResources().getStringArray(R.array.categories).length; ++i) {
            categoriesSelected.add(true);
        }
        return this.view;
    }

    public void onClick(View v) {
        String title = "";
        MainFragment context = MainFragment.this;
        int radius = Integer.parseInt(this.radiusEdit.getText().toString());
        double pricePoint = Double.parseDouble(this.pricePointEdit.getText().toString());
        switch(v.getId()) {
            case R.id.category_edit:
                title = getString(R.string.categories);
                DialogFragment dialogFragment = new CategoryDialog().newInstance(title,this.categoriesSelected,context);
                dialogFragment.show(getFragmentManager(),"category-dialog");
                break;
            case R.id.price_group:
                PricePointDialog pricePointDialog = new PricePointDialog();
                pricePointDialog.show(getFragmentManager(),"price-point-dialog");
                break;
            case R.id.radius_group:
                title = getString(R.string.radius);
                RadiusDialog radiusDialog = new RadiusDialog().newInstance(title,radius,context);
                radiusDialog.show(getFragmentManager(),"radius-dialog");
                break;
            case R.id.proceed_button:
                FragmentListener fragmentListener = (FragmentListener) getContext();
                fragmentListener.proceedButtonResponse(this.categoriesSelected,pricePoint,radius);
                break;
        }
    }

    @Override
    public void categoryDialogResponse(ArrayList<Boolean> stateOfList,Integer selectedSize) {
        this.categoriesSelected = stateOfList;
        System.out.println(selectedSize);
        String result = "";
        int categoriesSize = stateOfList.size();
        if (selectedSize == categoriesSize)  {
            result = "ALL";
        } else if (selectedSize == 0) {
            result = "ALL";
            for (int i = 0; i < categoriesSize; i++) {
                stateOfList.set(i,true);
            }
        } else {
            result = ("" + selectedSize) + " Selected";
        }
        this.categoryEdit.setText(result);
    }

    @Override
    public void pricePointDialogResponse(String params) {
        this.pricePointEdit.setText(params);
    }

    @Override
    public void radiusDialogResponse(String param) {
        this.radiusEdit.setText(param);
    }
}
