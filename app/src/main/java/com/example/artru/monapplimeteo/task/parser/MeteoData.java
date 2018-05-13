package com.example.artru.monapplimeteo.task.parser;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.artru.monapplimeteo.R;

public class MeteoData implements Parcelable{
    private String dateDebut;
    private String dateFin;
    private String temperature;
    private String symbole;
    private String windSpeed;
    private String windDirection;
    private String windDescription;
    private String clouds;
    private String humidity;

    public MeteoData() {
        this.dateDebut = "";
        this.dateFin = "";
        this.temperature = "";
        this.symbole = "";
        this.windSpeed = "";
        this.windDirection = "";
        this.windDescription = "";
        this.clouds = "";
        this.humidity = "";
    }

    @Override
    public String toString() {
        return dateDebut+dateFin+temperature+symbole+windSpeed+windDirection+windDescription+clouds+humidity;
    }

    public MeteoData(Parcel in) {
        getFromParcel(in);
    }


    public void getFromParcel(Parcel in)
    {
       this.dateDebut = in.readString();
       this.dateFin = in.readString();
       this.temperature = in.readString();
       this.symbole = in.readString();
       this.windSpeed = in.readString();
       this.windDirection = in.readString();
       this.windDescription = in.readString();
       this.clouds = in.readString();
       this.humidity = in.readString();
    }


    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindDescription() {
        return windDescription;
    }

    public void setWindDescription(String windDescription) {
        this.windDescription = windDescription;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dateDebut);
        parcel.writeString(dateFin);
        parcel.writeString(temperature);
        parcel.writeString(symbole);
        parcel.writeString(windSpeed);
        parcel.writeString(windDirection);
        parcel.writeString(windDescription);
        parcel.writeString(clouds);
        parcel.writeString(humidity);
    }

    public static final Creator<MeteoData> CREATOR = new Creator<MeteoData>() {
        @Override
        public MeteoData createFromParcel(Parcel in) {
            return new MeteoData(in);
        }

        @Override
        public MeteoData[] newArray(int size) {
            return new MeteoData[size];
        }
    };

    public int getHeure(){
        String heure = this.getDateDebut().substring(11,13);
        return Integer.parseInt(heure);
    }

    public int getImage(){
        int image;
        boolean night;
        int heure = this.getHeure();
        String symbol = this.getSymbole();
        night = heure < 8 || heure > 20;

        switch (symbol){
            case "clear sky":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            case "few clouds":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            case "scattered clouds":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            case "broken clouds":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            case "shower rain":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            case "rain":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            case "thunderstorm":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            case "snow":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            case "mist":
                image = night ? R.drawable.background : R.drawable.background;
                break;
            default:
                image = R.drawable.background;
                break;
        }
        return image;
    }
}
