package com.example.fishingharboralert.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_harbors")
public class FavoriteHarborEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String harborName;
    public String city;
    public double latitude;
    public double longitude;
    public long createdAt;

    public FavoriteHarborEntity(String harborName, String city, double latitude, double longitude, long createdAt) {
        this.harborName = harborName;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
    }
}
