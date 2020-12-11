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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import net.juanxxiii.practica1t.R;
import net.juanxxiii.practica1t.common.Constants;
import net.juanxxiii.practica1t.domain.Graph;
import net.juanxxiii.practica1t.domain.JsonResponse;
import net.juanxxiii.practica1t.domain.MyLocation;
import net.juanxxiii.practica1t.interfaces.ApiDatosMadrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.juanxxiii.practica1t.common.Constants.LATITUDE;
import static net.juanxxiii.practica1t.common.Constants.LONGITUDE;

public class PoolsActivity extends AppCompatActivity {

    public final String TAG = getClass().getName();
    public ListView listview;
    public List<Graph> facilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pools_facilites);


        listview = findViewById(R.id.listViewPoolsFacilities);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedObject = (listview.getItemAtPosition(i)).toString();
                Log.d(TAG, "Nombre: " + selectedObject);


                new AlertDialog.Builder(PoolsActivity.this)
                        .setIcon(android.R.drawable.star_big_on)
                        .setTitle("Add to favourites?")
                        .setMessage("Are you sure you want add this to your favourites page?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences favourites = getSharedPreferences("Favourite", MODE_PRIVATE);
                                SharedPreferences.Editor editor = favourites.edit();
                                editor.putStringSet("Favourite", Collections.singleton(selectedObject));
                                editor.apply();
                                Log.d(TAG, "Guardado en favoritos: "+ selectedObject);
                            }
                        })
                        .setNegativeButton("No", null).show();
                return true;
            }
        });

        SharedPreferences sharedPref2 = getApplicationContext().getSharedPreferences("LocationSaved", Context.MODE_PRIVATE);
        String locationParseable = sharedPref2.getString(getString(R.string.saved_location), "");
        String[] splitted = locationParseable.split(" ");
        double latitude = Double.parseDouble(splitted[0]);
        double longitude = Double.parseDouble(splitted[1]);

        getAllFacilites(latitude, longitude);
    }

    private void getAllFacilites(Double latitude, Double longitude){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiDatosMadrid apiDatosMadrid = retrofit.create(ApiDatosMadrid.class);
        apiDatosMadrid.getPoolList(latitude,longitude, Constants.DISTANCE).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                if (response.body() != null) {
                    facilities = response.body().graph;
                    ArrayAdapter<Graph> adapter = new ArrayAdapter<>(PoolsActivity.this, android.R.layout.simple_list_item_1, facilities);
                    listview.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }
}