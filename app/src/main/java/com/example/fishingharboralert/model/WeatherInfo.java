package com.example.fishingharboralert.model;

import java.io.Serializable;

public class WeatherInfo implements Serializable {
    private String harborName;
    private String weatherDescription;
    private double temperature;
    private int rainProbability;
    private double windSpeed;
    private double waveHeight;
    private String tideStatus;
    private String updateTime;

    public WeatherInfo(String harborName, String weatherDescription, double temperature, int rainProbability,
                       double windSpeed, double waveHeight, String tideStatus, String updateTime) {
        this.harborName = harborName;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.rainProbability = rainProbability;
        this.windSpeed = windSpeed;
        this.waveHeight = waveHeight;
        this.tideStatus = tideStatus;
        this.updateTime = updateTime;
    }

    public String getHarborName() {
        return harborName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getRainProbability() {
        return rainProbability;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWaveHeight() {
        return waveHeight;
    }

    public String getTideStatus() {
        return tideStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }
}
