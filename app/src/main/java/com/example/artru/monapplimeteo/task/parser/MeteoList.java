package com.example.artru.monapplimeteo.task.parser;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.artru.monapplimeteo.R;

import java.util.ArrayList;
import java.util.Objects;

public class MeteoList extends ArrayList<MeteoData> implements Parcelable {
    protected MeteoList(Parcel in) {
        this.getFromParcel(in);
    }
    public static final String TAG = MeteoList.class.getName();

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
        days.add(this.get(1).getDateDebut().substring(0,10));
        int indice = 0;
        for(int i =2; i< this.size(); i++){
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
        MeteoList meteoList = this.getDataDay(day);
        for(MeteoData meteoData : meteoList){
            tempDay.add(meteoData.getTemperature());
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
        MeteoList meteoList = this.getDataDay(day);
        for(MeteoData meteoData : meteoList){
            tempDay.add(meteoData.getTemperature());
        }
        for(String temp : tempDay ){
            if(max < Float.parseFloat(temp))
                max = Float.parseFloat(temp);
        }
        return max;
    }

    public MeteoList getDataDay(String day){
        MeteoList dataDay = new MeteoList();
        for (MeteoData meteoData : this ) {
            if (!meteoData.getDateDebut().isEmpty()) {
                if (Objects.equals(meteoData.getDateDebut().substring(0, 10), day)) {
                    dataDay.add(meteoData);
                }
            }
        }
        return dataDay;
    }

    public int getImageDay(String day){
        int image;
        MeteoList dataDay = this.getDataDay(day);
        int[] symbolList =new int[9];
        for(int i =0;i<9;i++){
            symbolList[i] =0;
        }
        for (MeteoData meteoData : dataDay ) {
            switch (meteoData.getSymbole()){
                case "clear sky":
                    symbolList[0]++;
                    break;
                case "few clouds":
                    symbolList[1]++;
                    break;
                case "scattered clouds":
                    symbolList[2]++;
                    break;
                case "broken clouds":
                    symbolList[3]++;
                    break;
                case "shower rain":
                    symbolList[4]++;
                    break;
                case "rain":
                    symbolList[5]++;
                    break;
                case "thunderstorm":
                    symbolList[6]++;
                    break;
                case "snow":
                    symbolList[7]++;
                    break;
                case "mist":
                    symbolList[8]++;
                    break;
                default:
                    Log.d(TAG,meteoData.getSymbole() );
                     break;
            }
        }
        int valeurSymbol = 0;
        int nboccurence = Integer.MIN_VALUE;
        for(int i=0;i<9;i++){
            if(nboccurence <= symbolList[i]){
                nboccurence = symbolList[i];
                valeurSymbol = i;
            }
        }

        switch (valeurSymbol){
            case 0:
                image = R.drawable.clear_day;
                break;
            case 1:
                image = R.drawable.few_clouds_day;
                break;
            case 2:
                image = R.drawable.cloudy;
                break;
            case 3:
                image = R.drawable.overcast;
                break;
            case 4:
                image = R.drawable.shower_rain;
                break;
            case 5:
                image = R.drawable.rain_day;
                break;
            case 6:
                image = R.drawable.thuderstorm;
                break;
            case 7:
                image = R.drawable.snow;
                break;
            case 8:
                image = R.drawable.mist;
                break;
            default:
                image = R.drawable.clear_day;
                break;

        }
        return image;
    }



}
