package net.juanxxiii.practica1t.domain;

public class MyLocation {
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return latitude +
                "," + longitude;
    }
}
