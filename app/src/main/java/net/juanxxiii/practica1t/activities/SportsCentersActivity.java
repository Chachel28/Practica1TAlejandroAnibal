package net.juanxxiii.practica1t.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import net.juanxxiii.practica1t.Objects.Facilities;
import net.juanxxiii.practica1t.R;

import java.util.ArrayList;

public class SportsCentersActivity extends AppCompatActivity {

    public ListView listview;
    public ArrayList<Facilities> facilities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pools_facilites);

        listview = findViewById(R.id.listViewFacilities);
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
                                guardarFavoritos();
                            }
                        })
                        .setNegativeButton("No", null).show();
                return true;
            }
        });

        Facilities facilities1 = new Facilities();
        facilities1.setName("Piscina cutre");
        facilities1.setLatitude(14.098);
        facilities1.setLongitude(35.976);
        facilities1.setText("Vaya mierda de piscina");

        facilities = new ArrayList<Facilities>();
        facilities.add(facilities1);

        ArrayAdapter<Facilities> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, facilities);
        listview.setAdapter(adapter);
    }

    public void guardarFavoritos(){
        Facilities facility = new Facilities();
        SharedPreferences favourites =
                getSharedPreferences("Favoritos", Context.MODE_PRIVATE);

    }
}