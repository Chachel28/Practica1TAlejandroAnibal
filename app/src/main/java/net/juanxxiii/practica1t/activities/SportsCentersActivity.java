package net.juanxxiii.practica1t.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import net.juanxxiii.practica1t.R;
import net.juanxxiii.practica1t.common.Constants;
import net.juanxxiii.practica1t.domain.Graph;
import net.juanxxiii.practica1t.domain.JsonResponse;
import net.juanxxiii.practica1t.interfaces.ApiDatosMadrid;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pools_facilites);

        listview = findViewById(R.id.listViewPoolsFacilities);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Funcion para ir a dicha ubicación
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(SportsCentersActivity.this)
                        .setIcon(android.R.drawable.star_big_on)
                        .setTitle("Add to favourites?")
                        .setMessage("Are you sure you want add this to your favourites page?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("No", null).show();
                return true;
            }
        });


        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra(LATITUDE, 0);
        double longitude = intent.getDoubleExtra(LONGITUDE, 0);
        Log.d(TAG, "sport " + latitude + " - " + longitude);

        getAllFacilites(latitude, longitude);

        ArrayAdapter<Graph> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sportCenters);
        listview.setAdapter(adapter);
    }

    private void getAllFacilites(Double latitude, Double longitude){
        Log.d(TAG, "Estoy en getAllFacilities");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiDatosMadrid apiDatosMadrid = retrofit.create(ApiDatosMadrid.class);
        apiDatosMadrid.getSportCenterList(latitude,longitude, Constants.DISTANCE).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                Log.d(TAG, "respuesta positiva peticion");
                if (response.body() != null) {
                    sportCenters = response.body().graph;
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }
}