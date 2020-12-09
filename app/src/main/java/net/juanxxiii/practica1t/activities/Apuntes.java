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
    * */
}
