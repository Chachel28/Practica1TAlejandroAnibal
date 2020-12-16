package net.juanxxiii.practica1t.common;

import android.content.Context;

import net.juanxxiii.practica1t.domain.Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FavouriteManager {
    private File file;

    public void addFavourite(Graph facility, Context context) {
        file = new File(context.getFilesDir() + Constants.FILE);
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        List<Graph> favourites;
        favourites = readFavourites(context);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            favourites.add(facility);
            objectOutputStream.writeObject(favourites);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteFavourite(Graph facility, Context context) {
        file = new File(context.getFilesDir() + Constants.FILE);
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        List<Graph> favourites;
        favourites = readFavourites(context);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            favourites.remove(facility);
            objectOutputStream.writeObject(favourites);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Graph> readFavourites(Context ctx) {
        List<Graph> favourites = new ArrayList<>();
        file = new File(ctx.getFilesDir() + Constants.FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            favourites = (List<Graph>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return favourites;
    }
}
