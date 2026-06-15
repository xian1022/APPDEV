package com.example.fishingharboralert.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alert_rules")
public class AlertRuleEntity {
    @PrimaryKey
    public int id;
    public double maxWindSpeed;
    public double maxWaveHeight;
    public boolean enableDangerAlert;

    public AlertRuleEntity(int id, double maxWindSpeed, double maxWaveHeight, boolean enableDangerAlert) {
        this.id = id;
        this.maxWindSpeed = maxWindSpeed;
        this.maxWaveHeight = maxWaveHeight;
        this.enableDangerAlert = enableDangerAlert;
    }
}
