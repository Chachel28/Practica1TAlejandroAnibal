package net.juanxxiii.practica1t.activities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.navigation.NavigationView;

import net.juanxxiii.practica1t.R;
import net.juanxxiii.practica1t.services.GpsService;

import static net.juanxxiii.practica1t.common.Constants.INTENT_LOCALIZATION_ACTION;
import static net.juanxxiii.practica1t.common.Constants.LATITUDE;
import static net.juanxxiii.practica1t.common.Constants.LONGITUDE;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public final String TAG = getClass().getName();
    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String TITLE = "My location";
    public static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    public static final String DESCRIPTION = "This is my location";
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    Double latitude = 0.0;
    Double longitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        findViewById(R.id.ImageMenu).setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(HomeActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            startService();
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(INTENT_LOCALIZATION_ACTION));
    }

    public void startCurrentLocation() {
        Log.d(TAG, "Value of latitude: ".concat(String.valueOf(latitude)));
        Intent locationIntent = new Intent(HomeActivity.this, MapActivity.class);
        locationIntent.putExtra(TITLE_KEY, TITLE);
        locationIntent.putExtra(DESCRIPTION_KEY, DESCRIPTION);
        locationIntent.putExtra(LATITUDE, latitude);
        locationIntent.putExtra(LONGITUDE, longitude);
        startActivity(locationIntent);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            latitude = intent.getDoubleExtra(LATITUDE,0);
            longitude = intent.getDoubleExtra(LONGITUDE,0);
            Log.d(TAG, "recibidor de mensajes ".concat(String.valueOf(latitude)));
        }
    };

    public void startService() {
        Intent mServiceIntent = new Intent(getApplicationContext(), GpsService.class);
        startService(mServiceIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), R.string.gps_granted, Toast.LENGTH_SHORT).show();
                startService();
            } else {
                Toast.makeText(getApplicationContext(), R.string.gps_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.currentLocation:
                startCurrentLocation();
                break;
            case R.id.favourites:
                break;
            default:
                Log.d(TAG,"seleccion menu" +  item.toString());
        }
        return false;
    }
}