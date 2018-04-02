package com.sbpm.locationlibraryapp;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.vishalsojitra.easylocation.EasyLocationRequest;

import com.vishalsojitra.easylocation.EasyLocationAppCompatActivity;

import com.google.android.gms.location.LocationRequest;
import com.vishalsojitra.easylocation.EasyLocationRequestBuilder;

public class LocationActivity extends EasyLocationAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (Build.VERSION.SDK_INT >= 23) {


            Log.d("MapaActivity", "Prueba Build.VERSION.SDK_INT >= 23 ");

            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

            if (pm.isIgnoringBatteryOptimizations(packageName))
                intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            else {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
            }

            startActivity(intent);

        }


        addLocation();

    }

    private void addLocation() {
        try {

            LocationRequest locationRequest = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_NO_POWER)
                    .setInterval(5000)

                    .setFastestInterval(5000);

            EasyLocationRequest easyLocationRequest = new EasyLocationRequestBuilder()
                    .setLocationRequest(locationRequest)

                    .setFallBackToLastLocationTime(3000)
                    .build();

            requestLocationUpdates(easyLocationRequest);

        } catch (Exception e) {
        }

    }


    @Override
    public void onLocationPermissionGranted() {

    }

    @Override
    public void onLocationPermissionDenied() {

    }

    @Override
    public void onLocationReceived(Location location) {

        Log.e("onLocationReceived", "lan: " + location.getLatitude() + " lon:" + location.getLongitude());
    }

    @Override
    public void onLocationProviderEnabled() {

    }

    @Override
    public void onLocationProviderDisabled() {

    }
}
