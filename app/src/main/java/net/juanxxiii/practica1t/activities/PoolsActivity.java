package net.juanxxiii.practica1t.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.juanxxiii.practica1t.Objects.Facility;
import net.juanxxiii.practica1t.R;
import net.juanxxiii.practica1t.common.Constants;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PoolsActivity extends AppCompatActivity {

    public ListView listview;
    public ArrayList<Facility> facilities;
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
                new AlertDialog.Builder(PoolsActivity.this)
                        .setIcon(android.R.drawable.star_big_on)
                        .setTitle("Add to favourites?")
                        .setMessage("Are you sure you want add this to your favourites page?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                guardarFavoritos();
                            }
                        })
                        .setNegativeButton("No", null).show();
                return true;
            }
        });
        Facility facility1 = new Facility();
        facility1.setName("Piscina cutre");
        facility1.setLatitude(14.098);
        facility1.setLongitude(35.976);
        facility1.setText("Vaya mierda de piscina");

        facilities = new ArrayList<>();
        facilities.add(facility1);

        ArrayAdapter<Facility> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, facilities);
        listview.setAdapter(adapter);
    }

    public void guardarFavoritos(){
        Facility facility = new Facility();
        SharedPreferences favourites =
                getSharedPreferences("Favoritos", Context.MODE_PRIVATE);

        facility.setName(listview.getSelectedItem()./*el getter del nombre*/toString());
    }

    private void getAllFacilites(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}