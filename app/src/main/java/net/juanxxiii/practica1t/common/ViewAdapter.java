package net.juanxxiii.practica1t.common;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.juanxxiii.practica1t.R;
import net.juanxxiii.practica1t.domain.Graph;
import net.juanxxiii.practica1t.domain.JsonResponse;

import java.util.List;

public class ViewAdapter extends BaseAdapter {
    private Context context;
    private List<Graph> locations;
    private List<Graph> favourites;
    private int type;

    public ViewAdapter(Context mContext, List<Graph> locations, int type, List<Graph> favourites) {
        this.context = mContext;
        this.locations = locations;
        this.type = type;
        this.favourites = favourites;
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public Graph getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_listview, null);
        }
        ImageView image = convertView.findViewById(R.id.mImage);
        switch (type) {
            case 1:
                image.setImageResource(R.drawable.running);
                break;
            case 2:
                image.setImageResource(R.drawable.swimming);
                break;
            case 3:
                image.setImageResource(R.drawable.star);
                break;
        }

        if (type != 3) {
            ImageView image1 = convertView.findViewById(R.id.imageFav);
            if (favourites.contains(locations.get(position))) {
                Log.d("TAG", "estoy marcando un favorito");
                image1.setImageResource(R.drawable.star);
            }
        }

        TextView title = convertView.findViewById(R.id.title);
        title.setText(locations.get(position).toString());
        return convertView;
    }

}
