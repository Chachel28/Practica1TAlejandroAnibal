package net.juanxxiii.practica1t.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.juanxxiii.practica1t.R;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    public ListView listview;
    public ArrayList<String> favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        listview = findViewById(R.id.listViewFavourites);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Funcion para ir a dicha ubicación
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(FavouritesActivity.this)
                        .setIcon(android.R.drawable.btn_star)
                        .setTitle("Remove from favourites?")
                        .setMessage("Are you sure you want to remove this from your favourites page?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Función de eliminar de favoritos
                            }
                        })
                        .setNegativeButton("No", null).show();
                return true;
            }
        });

        favourites = new ArrayList<String>();
        favourites.add("Veracruz");
        favourites.add("Tabasco");
        favourites.add("Chiapas");
        favourites.add("Campeche");
        favourites.add("Quintana Roo");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favourites);
        listview.setAdapter(adapter);
    }
}