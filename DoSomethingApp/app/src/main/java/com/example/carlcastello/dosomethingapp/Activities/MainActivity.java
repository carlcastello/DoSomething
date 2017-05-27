package com.example.carlcastello.dosomethingapp.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.carlcastello.dosomethingapp.Fragments.MainFragment;
import com.example.carlcastello.dosomethingapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            MainFragment mainFragment = new MainFragment();
            transaction.add(R.id.fragment_container,mainFragment,"mainFragment");
            transaction.commit();
        }

    }

    public void proceedButtonListener(View view) {


    }

}
