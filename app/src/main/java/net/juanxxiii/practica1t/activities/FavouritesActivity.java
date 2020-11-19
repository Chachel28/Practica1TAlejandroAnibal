package net.juanxxiii.practica1t.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.juanxxiii.practica1t.R;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    public ListView listview;
    public ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        listview = findViewById(R.id.listViewFavourites);

        names = new ArrayList<String>();

        names.add("Veracruz");
        names.add("Tabasco");
        names.add("Chiapas");
        names.add("Campeche");
        names.add("Quintana Roo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);
    }
}