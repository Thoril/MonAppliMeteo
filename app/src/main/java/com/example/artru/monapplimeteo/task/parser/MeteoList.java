package com.example.artru.monapplimeteo.task.parser;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Objects;

public class MeteoList extends ArrayList<MeteoData> implements Parcelable {
    protected MeteoList(Parcel in) {
        this.getFromParcel(in);
    }

    public MeteoList() {
    }

    public static final Creator<MeteoList> CREATOR = new Creator<MeteoList>() {
        @Override
        public MeteoList createFromParcel(Parcel in) {
            return new MeteoList(in);
        }

        @Override
        public MeteoList[] newArray(int size) {
            return new MeteoList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        int size = this.size();
        parcel.writeInt(size);
        for(int i=0; i < size; i++)
        {
            MeteoData meteoData = this.get(i); //On vient lire chaque objet personne
            parcel.writeString(meteoData.getDateDebut());
            parcel.writeString(meteoData.getDateFin());
            parcel.writeString(meteoData.getTemperature());
            parcel.writeString(meteoData.getSymbole());
            parcel.writeString(meteoData.getWindSpeed());
            parcel.writeString(meteoData.getWindDirection());
            parcel.writeString(meteoData.getWindDescription());
            parcel.writeString(meteoData.getClouds());
            parcel.writeString(meteoData.getHumidity());
        }

    }

    public void getFromParcel(Parcel in)
    {
        // On vide la liste avant tout remplissage
        this.clear();

        //Récupération du nombre d'objet
        int size = in.readInt();

        //On repeuple la liste avec de nouveau objet
        for(int i = 0; i < size; i++)
        {
            MeteoData meteoData = new MeteoData();
            meteoData.setDateDebut(in.readString());
            meteoData.setDateFin(in.readString());
            meteoData.setTemperature(in.readString());
            meteoData.setSymbole(in.readString());
            meteoData.setWindSpeed(in.readString());
            meteoData.setWindDirection(in.readString());
            meteoData.setWindDescription(in.readString());
            meteoData.setClouds(in.readString());
            meteoData.setHumidity(in.readString());
            this.add(meteoData);
        }


    }

    public ArrayList<String> getDays() {
        if(this.isEmpty()) return null;
        ArrayList<String> days = new ArrayList<String>();
        days.add(this.get(0).getDateDebut().substring(0,10));
        int indice = 0;
        for(int i =1; i< this.size(); i++){
            if(!Objects.equals(this.get(i).getDateDebut().substring(0, 10), days.get(indice))){
                days.add(this.get(i).getDateDebut().substring(0,10));
                indice++;
            }
        }
        return days;
    }

    public float getMinDay(String day){
        float min = Float.MAX_VALUE;
        ArrayList<String> tempDay = new ArrayList<String>();
        for(int i =0; i< this.size(); i++){
            if(!Objects.equals(this.get(i).getDateDebut().substring(0, 10), day))
                tempDay.add(this.get(i).getTemperature());
        }
        for(String temp : tempDay ){
            if(min > Float.parseFloat(temp))
                min = Float.parseFloat(temp);
        }
        return min;
    }

    public float getMaxDay(String day){
        float max = Float.MIN_VALUE;
        ArrayList<String> tempDay = new ArrayList<String>();
        for(int i =0; i< this.size(); i++){
            if(!Objects.equals(this.get(i).getDateDebut().substring(0, 10), day))
                tempDay.add(this.get(i).getTemperature());
        }
        for(String temp : tempDay ){
            if(max < Float.parseFloat(temp))
                max = Float.parseFloat(temp);
        }
        return max;
    }



}
