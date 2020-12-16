package net.juanxxiii.practica1t.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.juanxxiii.practica1t.R;
import net.juanxxiii.practica1t.common.FavouriteManager;
import net.juanxxiii.practica1t.common.ViewAdapter;
import net.juanxxiii.practica1t.domain.Graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static net.juanxxiii.practica1t.common.Constants.LATITUDE;
import static net.juanxxiii.practica1t.common.Constants.LONGITUDE;

public class FavouritesActivity extends AppCompatActivity {

    public final String TAG = getClass().getName();
    public ListView listview;
    public List<Graph> favourites;
    public FavouriteManager favouriteManager = new FavouriteManager();
    public ViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        favourites = favouriteManager.readFavourites(getApplicationContext());
        adapter = new ViewAdapter(FavouritesActivity.this, favourites, 3, favourites);

        listview = findViewById(R.id.listViewFavourites);
        listview.setOnItemClickListener((adapterView, view, i, l) -> {
            Graph selected = adapter.getItem(i);
            Intent mapIntent = new Intent(FavouritesActivity.this, MapActivity.class);
            mapIntent.putExtra(LATITUDE, selected.location.latitude);
            mapIntent.putExtra(LONGITUDE, selected.location.longitude);
            startActivity(mapIntent);
        });

        listview.setOnItemLongClickListener((adapterView, view, i, l) -> {
            new AlertDialog.Builder(FavouritesActivity.this)
                    .setIcon(android.R.drawable.btn_star)
                    .setTitle("Remove from favourites?")
                    .setMessage("Are you sure you want to remove this from your favourites page?")
                    .setPositiveButton("Yes", (dialogInterface, i1) -> {
                        favouriteManager.deleteFavourite(favourites.get(i), FavouritesActivity.this);
                        Intent recharge = new Intent(FavouritesActivity.this, FavouritesActivity.class);
                        startActivity(recharge);
                        finish();
                    })
                    .setNegativeButton("No", null).show();
            return true;
        });

        listview.setAdapter(adapter);
    }
}