package com.example.fishingharboralert.model;

import java.io.Serializable;

public class Harbor implements Serializable {
    private final int id;
    private final String name;
    private final String city;
    private final double latitude;
    private final double longitude;
    private final String description;

    public Harbor(int id, String name, String city, double latitude, double longitude, String description) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }
}
