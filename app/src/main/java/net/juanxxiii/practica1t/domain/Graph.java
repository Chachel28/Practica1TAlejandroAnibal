package net.juanxxiii.practica1t.domain;

public class Graph {
    private String title;
    private MyLocation location;

    @Override
    public String toString() {
        return "Graph{" +
                "name='" + title + '\'' +
                ", location=" + location +
                '}';
    }
}
