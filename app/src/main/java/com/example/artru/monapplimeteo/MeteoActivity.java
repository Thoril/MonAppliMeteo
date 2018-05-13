package com.example.artru.monapplimeteo;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.example.artru.monapplimeteo.task.parser.MeteoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MeteoActivity extends AppCompatActivity {

    private static String TAG = MeteoActivity.class.getName();
    private MeteoList listMeteoData; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afichage_meteo);

        Bundle b = getIntent().getExtras();
       
        if (b != null) {
            listMeteoData = b.getParcelable("data");
        }
        if (listMeteoData != null) {
            Log.i(TAG, "Arrivé des données  Nombre de data Météo : " + listMeteoData.size());
            if (listMeteoData.size() != 0) {

                //Placement de la ville
                TextView city = findViewById(R.id.city);
                city.setText(listMeteoData.get(0).getSymbole());
                //Placement de la date
                Date d = new Date();
                SimpleDateFormat f = new SimpleDateFormat("EEE d MMMM");
                String s = f.format(d);
                TextView dateText = findViewById(R.id.date);
                dateText.setText(s);

                //Placement de la température
                TextView tempText = findViewById(R.id.temperature);
                tempText.setText(String.format("%s°C", listMeteoData.get(1).getTemperature()));

                //Placement de l'heure actuelle
                TextView heure = findViewById(R.id.heure);
                f = new SimpleDateFormat("HH:mm ");
                heure.setText(f.format(d));

                //Placement de la description du temps actuelle
                TextView symbol = findViewById( R.id.symbol);
                String sym = listMeteoData.get(1).getSymbole();
                sym = sym.replaceFirst(".",(sym.charAt(0)+"").toUpperCase());
                symbol.setText(sym);

                //Placement du type de vent actuelle
                TextView wind = findViewById(R.id.wind);
                wind.setText(listMeteoData.get(1).getWindDescription());

                //Placement de la vitesse du vent actuelle
                TextView windSpeed = findViewById(R.id.windSpeed);
                windSpeed.setText(String.format("%s Km / h", listMeteoData.get(1).getWindSpeed()));

                //Placement de l'image de la meteo actuelle
                ImageView meteo = findViewById(R.id.meteo);
                meteo.setImageResource(listMeteoData.get(1).getImage());

                //Placement des heures 4 prochaines données météos
                TextView h1 = findViewById(R.id.h1);
                h1.setText(listMeteoData.get(1).getDateDebut().substring(11,16));
                TextView h2 = findViewById(R.id.h2);
                h2.setText(listMeteoData.get(2).getDateDebut().substring(11,16));
                TextView h3 = findViewById(R.id.h3);
                h3.setText(listMeteoData.get(3).getDateDebut().substring(11,16));
                TextView h4 = findViewById(R.id.h4);
                h4.setText(listMeteoData.get(4).getDateDebut().substring(11,16));

                //Placement des températures des 4 prochaines données météos
                TextView temph1 = findViewById(R.id.temph1);
                temph1.setText(String.format("%s°C", listMeteoData.get(1).getTemperature()));
                TextView temph2 = findViewById(R.id.temph2);
                temph2.setText(String.format("%s°C", listMeteoData.get(2).getTemperature()));
                TextView temph3 = findViewById(R.id.temph3);
                temph3.setText(String.format("%s°C", listMeteoData.get(3).getTemperature()));
                TextView temph4 = findViewById(R.id.temph4);
                temph4.setText(String.format("%s°C", listMeteoData.get(4).getTemperature()));

                //Image des 4 prochaines données météos
                ImageView symbh1 = findViewById(R.id.symbh1);
                symbh1.setImageResource(listMeteoData.get(2).getImage());
                ImageView symbh2 = findViewById(R.id.symbh2);
                symbh2.setImageResource(listMeteoData.get(3).getImage());
                ImageView symbh3 = findViewById(R.id.symbh3);
                symbh3.setImageResource(listMeteoData.get(4).getImage());
                ImageView symbh4 = findViewById(R.id.symbh4);
                symbh4.setImageResource(listMeteoData.get(5).getImage());

                //Modification des Boutons pour avoir le details d'un jour
                ArrayList<String> days = listMeteoData.getDays();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                SimpleDateFormat sdfAffichage = new SimpleDateFormat("EEEE");
                ArrayList<Date> daysFormatte = new ArrayList<>();
                for (String day : days) {
                    try {
                        date = sdf.parse(day);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    daysFormatte.add(date);
                    Log.i(TAG, sdfAffichage.format(date));
                }
                Button day2 = findViewById(R.id.day2);
                day2.setText(sdfAffichage.format(daysFormatte.get(1)));
                Button day3 = findViewById(R.id.day3);
                day3.setText(sdfAffichage.format(daysFormatte.get(2)));
                Button day4 = findViewById(R.id.day4);
                day4.setText(sdfAffichage.format(daysFormatte.get(3)));
                Button day5 = findViewById(R.id.day5);
                day5.setText(sdfAffichage.format(daysFormatte.get(4)));

                //Placement des images des 5 prochaines jours
                ImageView symbd2 = findViewById(R.id.symbd2);
                symbd2.setImageResource(listMeteoData.getImageDay(days.get(1)));
                ImageView symbd3 = findViewById(R.id.symbd3);
                symbd3.setImageResource(listMeteoData.getImageDay(days.get(2)));
                ImageView symbd4 = findViewById(R.id.symbd4);
                symbd4.setImageResource(listMeteoData.getImageDay(days.get(3)));
                ImageView symbd5 = findViewById(R.id.symbd5);
                symbd5.setImageResource(listMeteoData.getImageDay(days.get(4)));


                //Modification des minimums des 5 prochains jours
                TextView minDay2 = findViewById(R.id.minDay2);
                minDay2.setText(String.format("%s°C", Float.toString(listMeteoData.getMinDay(days.get(1)))));
                TextView minDay3 = findViewById(R.id.minDay3);
                minDay3.setText(String.format("%s°C", Float.toString(listMeteoData.getMinDay(days.get(2)))));
                TextView minDay4 = findViewById(R.id.minDay4);
                minDay4.setText(String.format("%s°C", Float.toString(listMeteoData.getMinDay(days.get(3)))));
                TextView minDay5 = findViewById(R.id.minDay5);
                minDay5.setText(String.format("%s°C", Float.toString(listMeteoData.getMinDay(days.get(4)))));

                //Modification des maximums des 5 prochaines jours
                TextView maxDay2 = findViewById(R.id.maxDay2);
                maxDay2.setText(String.format("%s°C", Float.toString(listMeteoData.getMaxDay(days.get(1)))));
                TextView maxDay3 = findViewById(R.id.maxDay3);
                maxDay3.setText(String.format("%s°C", Float.toString(listMeteoData.getMaxDay(days.get(2)))));
                TextView maxDay4 = findViewById(R.id.maxDay4);
                maxDay4.setText(String.format("%s°C", Float.toString(listMeteoData.getMaxDay(days.get(3)))));
                TextView maxDay5 = findViewById(R.id.maxDay5);
                maxDay5.setText(String.format("%s°C", Float.toString(listMeteoData.getMaxDay(days.get(4)))));


            }
        }
    }

    public void detailsDay1(View view){
        Intent intent  = new Intent(this,DetailsDayActivity.class);
        ArrayList<String> days = listMeteoData.getDays();
        MeteoList dataDay = this.listMeteoData.getDataDay(days.get(4));
        intent.putExtra("data", (Parcelable) dataDay);
        this.startActivity(intent);
    }

    public void detailsDay2(View view){
        Intent intent  = new Intent(this,DetailsDayActivity.class);
        ArrayList<String> days = listMeteoData.getDays();
        MeteoList dataDay = this.listMeteoData.getDataDay(days.get(1));
        intent.putExtra("data", (Parcelable) dataDay);
        this.startActivity(intent);
    }

    public void detailsDay3(View view){
        Intent intent  = new Intent(this,DetailsDayActivity.class);
        ArrayList<String> days = listMeteoData.getDays();
        MeteoList dataDay = this.listMeteoData.getDataDay(days.get(2));
        intent.putExtra("data", (Parcelable) dataDay);
        this.startActivity(intent);
    }

    public void detailsDay4(View view){
        Intent intent  = new Intent(this,DetailsDayActivity.class);
        ArrayList<String> days = listMeteoData.getDays();
        MeteoList dataDay = this.listMeteoData.getDataDay(days.get(3));
        intent.putExtra("data", (Parcelable) dataDay);
        this.startActivity(intent);
    }

}


