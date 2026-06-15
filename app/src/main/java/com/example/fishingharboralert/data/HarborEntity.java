package com.example.fishingharboralert.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "harbors")
public class HarborEntity {
    @PrimaryKey
    public int id;
    public String name;
    public String city;
    public double latitude;
    public double longitude;
    public String description;

    public HarborEntity(int id, String name, String city, double latitude, double longitude, String description) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }
}
