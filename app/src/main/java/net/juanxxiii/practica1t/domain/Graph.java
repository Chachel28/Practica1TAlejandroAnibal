package net.juanxxiii.practica1t.domain;

public class Graph {
    private String title;
    private MyLocation location;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MyLocation getLocation() {
        return location;
    }

    public void setLocation(MyLocation location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return title + '\'' +
                ", " + location;
    }
}
