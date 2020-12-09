package net.juanxxiii.practica1t.Objects;

public class Facilities {
    private String name;
    private String text;
    private Double latitude;
    private Double longitude;

    public Facilities(String name, String text, Double latitude, Double longitude) {
        this.name = name;
        this.text = text;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public Facilities() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}


