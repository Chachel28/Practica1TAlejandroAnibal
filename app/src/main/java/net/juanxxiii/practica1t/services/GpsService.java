package net.juanxxiii.practica1t.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static net.juanxxiii.practica1t.common.Constants.INTENT_LOCALIZATION_ACTION;
import static net.juanxxiii.practica1t.common.Constants.LATITUDE;
import static net.juanxxiii.practica1t.common.Constants.LONGITUDE;

public class GpsService extends Service implements LocationListener {

    private LocationManager mLocManager = null;
    Double latitude = 0.0;
    Double longitude = 0.0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressWarnings({"MissingPermission"})
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.d("d", "Service: started");

        startLocation();

        return START_STICKY;
    }

    @SuppressLint("MissingPermission")
    private void startLocation() {
        mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsProvider = mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean networkProvider = mLocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!gpsProvider) {
            Log.d("d", "Service: provider not enabled");
            Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            callGPSSettingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(callGPSSettingIntent);
        } else {
            Log.d("d", "Service: provider enabled");
            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 100, this);
        }
        if (networkProvider){
            Log.d("d", "Service: network provider enabled");
            mLocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 100, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("d", "Service: location changed");
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Intent intent = new Intent(INTENT_LOCALIZATION_ACTION);
        intent.putExtra(LATITUDE, latitude);
        intent.putExtra(LONGITUDE, longitude);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}