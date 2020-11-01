package net.juanxxiii.practica1t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.juanxxiii.practica1t.services.GpsService;

import static net.juanxxiii.practica1t.common.Constants.INTENT_LOCALIZATION_ACTION;
import static net.juanxxiii.practica1t.common.Constants.LATITUDE;
import static net.juanxxiii.practica1t.common.Constants.LONGITUDE;

public class HomeActivity extends AppCompatActivity {

    public final String TAG = getClass().getName();
    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String TITLE = "My location";
    public static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    public static final String DESCRIPTION = "This is my location";
    Double latitude = 0.0;
    Double longitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(HomeActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            startService();
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(INTENT_LOCALIZATION_ACTION));

        Button btnShowLocationInOpenStreetMapsActivity = (Button) findViewById(R.id.btnShowOpenStreetMaps);

        btnShowLocationInOpenStreetMapsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Value of latitude: ".concat(String.valueOf(latitude)));
                Intent locationIntent = new Intent(HomeActivity.this, MapActivity.class);
                locationIntent.putExtra(TITLE_KEY,TITLE);
                locationIntent.putExtra(DESCRIPTION_KEY,DESCRIPTION);
                locationIntent.putExtra(LATITUDE, latitude);
                locationIntent.putExtra(LONGITUDE, longitude);
                startActivity(locationIntent);
            }
        });
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            latitude = intent.getDoubleExtra(LATITUDE,0);
            longitude = intent.getDoubleExtra(LONGITUDE,0);
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
}