package net.juanxxiii.practica1t.domain;

import java.io.Serializable;

public class MyLocation implements Serializable {
    private double latitude;
    private double longitude;

    public MyLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return latitude + " " + longitude;
    }
}
