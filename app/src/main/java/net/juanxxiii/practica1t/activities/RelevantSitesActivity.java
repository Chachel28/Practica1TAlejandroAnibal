package net.juanxxiii.practica1t.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.juanxxiii.practica1t.R;

import java.util.ArrayList;

public class RelevantSitesActivity extends AppCompatActivity {

    public ListView listview;
    public ArrayList<String> sites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relevant_sites);

        listview = findViewById(R.id.listViewRelevantSites);

        sites = new ArrayList<String>();

        sites.add("https://rfen.es/es/");
        sites.add("https://www.marca.com/natacion.html");
        sites.add("https://www.culturaydeporte.gob.es/deporte.html");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sites);
        listview.setAdapter(adapter);

    }
}