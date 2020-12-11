package net.juanxxiii.practica1t.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.juanxxiii.practica1t.R;

import java.util.ArrayList;

public class RelevantSitesActivity extends AppCompatActivity {

    public final String TAG = getClass().getName();
    public ListView listview;
    public ArrayList<String> sites;
    public WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relevant_sites);

        listview = findViewById(R.id.listViewRelevantSites);

        sites = new ArrayList<>();

        sites.add("https://rfen.es/es/");
        sites.add("https://www.marca.com");

        mWebView = findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.setVisibility(View.INVISIBLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sites);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mWebView.setVisibility(View.VISIBLE);
                Log.d(TAG, view.toString());
                mWebView.loadUrl((String) listview.getItemAtPosition(i));
            }
        });
    }
}