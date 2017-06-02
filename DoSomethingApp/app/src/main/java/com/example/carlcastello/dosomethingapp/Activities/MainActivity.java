package com.example.carlcastello.dosomethingapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.carlcastello.dosomethingapp.Controller.GetPlacesController;
import com.example.carlcastello.dosomethingapp.Controller.PlaceController;
import com.example.carlcastello.dosomethingapp.Controller.PlaceListController;
import com.example.carlcastello.dosomethingapp.Fragments.CategoriesFragment;
import com.example.carlcastello.dosomethingapp.Fragments.EventsFragment;
import com.example.carlcastello.dosomethingapp.Fragments.FavouritesFragment;
import com.example.carlcastello.dosomethingapp.Fragments.InfoFragment;
import com.example.carlcastello.dosomethingapp.Fragments.MainFragment;
import com.example.carlcastello.dosomethingapp.Fragments.RecentFragment;
import com.example.carlcastello.dosomethingapp.Listeners.AsyncListener;
import com.example.carlcastello.dosomethingapp.Listeners.FragmentListener;
import com.example.carlcastello.dosomethingapp.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentListener, LocationListener, AsyncListener {

    private LocationManager locationManager;
    private FragmentManager fragmentManager;
    private Location location;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Fragment manager
        this.fragmentManager = getSupportFragmentManager();

        // Initialize Location
        initLocation();

        // Initialize Bottom Bar
        initBottomBar();
    }

    // This initialize the bottom bar
    private void initBottomBar(){
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                tabRespond(tabId);
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                tabRespond(tabId);
            }
        });
    }

    // This handles the tab onClicks
    private void tabRespond(int tabId) {
        boolean canback = getSupportFragmentManager().getBackStackEntryCount()>0;
        if (canback) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        if (tabId == R.id.tab_random) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            MainFragment mainFragment = new MainFragment().newInstance();
            transaction.replace(R.id.fragment_container, mainFragment, "mainFragment");
            transaction.commit();
        } else if (tabId == R.id.tab_favourites) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            FavouritesFragment favouritesFragment = new FavouritesFragment().newInstance();
            transaction.replace(R.id.fragment_container, favouritesFragment, "favouritesFragment");
            transaction.commit();
        } else if (tabId == R.id.tab_recent) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            RecentFragment recentFragment = new RecentFragment().newInstance();
            transaction.replace(R.id.fragment_container, recentFragment, "recentFragment");
            transaction.commit();
        } else if (tabId == R.id.tab_events) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            EventsFragment eventsFragment = new EventsFragment().newInstance();
            transaction.replace(R.id.fragment_container, eventsFragment, "eventFragment");
            transaction.commit();
        } else if (tabId == R.id.tab_categories) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            CategoriesFragment categoriesFragment = new CategoriesFragment().newInstance();
            transaction.replace(R.id.fragment_container, categoriesFragment, "categoriesFragment");
            transaction.commit();
        }
    }

    // This handles the main Fragment button
    public void proceedButtonResponse(ArrayList<Boolean> categories, double pricePoint, int radius) {
        GetPlacesController getPlacesController = new GetPlacesController(this,location,categories,pricePoint, radius);
        getPlacesController.execute();
    }

    // Show Response of the query
    @Override
    public void googlePlaceData(PlaceController placeController) {


        PlaceListController placeListController = new PlaceListController(this);
        placeListController.addPlace(placeController.getPlace());
        placeListController.save();

        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        InfoFragment infoFragment = new InfoFragment().newInstance(placeController,placeListController.getSize() - 1);
        transaction.replace(R.id.fragment_container,infoFragment,"infoFragment");
        transaction.addToBackStack(null);
        transaction.commit();

    }


    // This initialize the location
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initLocation() {
        this.locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            }, 10);
            return;
        }
        getLocationOnRequest();
    }

    // Initialize location
    private void getLocationOnRequest(){
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        locationManager.requestLocationUpdates(provider, 0,10, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLocationOnRequest();
                }
                break;
        }
    }

}
