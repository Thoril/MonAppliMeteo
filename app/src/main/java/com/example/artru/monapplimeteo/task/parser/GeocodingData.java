package com.example.artru.monapplimeteo.task.parser;

public class GeocodingData {
    private String adresse;
    private String longitude;
    private String latitude;

    public GeocodingData(String adresse, String longitude, String latitude) {
        this.adresse = adresse;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public GeocodingData() {
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
