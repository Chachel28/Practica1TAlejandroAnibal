package net.juanxxiii.practica1t.activities;

public class Apuntes {
    //Elemento de un list view
    /*
    * setOnCreateContextMenuListener(new View.OnCreate..
    * menu.add(0,1,0,"OPT_ONE")
    *
    * onContextItemSelected()
    * AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    * */

    //WebView
    /*
    * WebView mWebView = findViewById(R.id.webView);
    * mWebView.getSettings().setJavaScriptEnabled(true);
    * mWebView.getSettings().setAppCacheEnabled(true);
    * mWebView.loadUrl("http://www.google.es");
    * */

    //Json para datos
    /*
    * getFileDir()+/user.json
    * pv write(String file, Miobjeto objeto){
    * Writer writer = new FileWriter(file)
    * Gson gson = new GsonBuilder().setPrettyPrinting().create();
    * gson.toJson(user, writer)
    * }
    *
    * pv read(String file){
    * Reader reader = new FileReader(file)
    * Gson gson = new Gson();
    * Type types = new TypeToken<ArrayList<Objeto>>(){}.getTypes()
    *
    * objeto = gsonFromJson(reader, types);
    * }
    *
    * package com.example.practica_moviles.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practica_moviles.R;
import com.example.practica_moviles.domain.ModeloJson;

import java.util.List;

public class ViewAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ModeloJson> ubicacion;
    private int tipo;

    public ViewAdapter(Context mContext, int layout, List<ModeloJson> ubicacion, int tipo) {
        this.context = mContext;
        this.layout = layout;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }

    @Override
    public int getCount() {
        return ubicacion.size();
    }

    @Override
    public Object getItem(int position) {
        return ubicacion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_listview,null);
        }
        ImageView imagen = convertView.findViewById(R.id.imagen);
        if(tipo==1) {
            imagen.setImageResource(R.drawable.deportito);
        }else if(tipo==2){
            imagen.setImageResource(R.drawable.piscinita);
        }
        TextView titulo = convertView.findViewById(R.id.titulo);
        titulo.setText(ubicacion.get(position).getTitulo());
        return convertView;
    }

}
    * */
}
