package net.juanxxiii.practica1t.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.juanxxiii.practica1t.R;
import net.juanxxiii.practica1t.common.Constants;
import net.juanxxiii.practica1t.common.FavouriteManager;
import net.juanxxiii.practica1t.common.ViewAdapter;
import net.juanxxiii.practica1t.domain.Graph;
import net.juanxxiii.practica1t.domain.JsonResponse;
import net.juanxxiii.practica1t.interfaces.ApiDatosMadrid;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.juanxxiii.practica1t.common.Constants.LATITUDE;
import static net.juanxxiii.practica1t.common.Constants.LONGITUDE;

public class SportsCentersActivity extends AppCompatActivity {

    public final String TAG = getClass().getName();
    public ListView listview;
    public List<Graph> sportCenters;
    public List<Graph> favourites;
    public ViewAdapter adapter;
    public FavouriteManager favouriteManager = new FavouriteManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_facilities);

        listview = findViewById(R.id.listViewPoolsFacilities);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            Graph selected = adapter.getItem(position);
            Intent mapIntent = new Intent(SportsCentersActivity.this, MapActivity.class);
            mapIntent.putExtra(LATITUDE, selected.location.latitude);
            mapIntent.putExtra(LONGITUDE, selected.location.longitude);
            startActivity(mapIntent);
        });

        SharedPreferences sharedPref2 = getApplicationContext().getSharedPreferences("LocationSaved", Context.MODE_PRIVATE);
        String locationParseable = sharedPref2.getString(getString(R.string.saved_location), "");
        String[] splitted = locationParseable.split(" ");
        double latitude = Double.parseDouble(splitted[0]);
        double longitude = Double.parseDouble(splitted[1]);

        getAllFacilites(latitude, longitude);

        listview.setOnItemLongClickListener((adapterView, view, i, l) -> {
            new AlertDialog.Builder(SportsCentersActivity.this)
                    .setIcon(android.R.drawable.star_big_on)
                    .setTitle("Add to favourites?")
                    .setMessage("Are you sure you want add this to your favourites page?")
                    .setPositiveButton("Yes", (dialogInterface, i1) -> {
                        favouriteManager.addFavourite(adapter.getItem(i), SportsCentersActivity.this);
                        Intent recharge = new Intent(SportsCentersActivity.this, SportsCentersActivity.class);
                        startActivity(recharge);
                        finish();
                    })
                    .setNegativeButton("No", null).show();
            return true;
        });
    }

    private void getAllFacilites(Double latitude, Double longitude) {
        Log.d(TAG, "Estoy en getAllFacilities");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiDatosMadrid apiDatosMadrid = retrofit.create(ApiDatosMadrid.class);
        apiDatosMadrid.getSportCenterList(latitude, longitude, Constants.DISTANCE).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                Log.d(TAG, "respuesta positiva peticion");
                if (response.body() != null) {
                    sportCenters = response.body().graph;
                    favourites = favouriteManager.readFavourites(SportsCentersActivity.this);
                    adapter = new ViewAdapter(SportsCentersActivity.this, sportCenters, 1, favourites);
                    listview.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }
}