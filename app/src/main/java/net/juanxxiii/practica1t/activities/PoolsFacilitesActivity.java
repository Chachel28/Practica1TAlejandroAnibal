package net.juanxxiii.practica1t.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.juanxxiii.practica1t.R;
import net.juanxxiii.practica1t.common.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PoolsFacilitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pools_facilites);
        
    }

    private void getAllFacilites(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}