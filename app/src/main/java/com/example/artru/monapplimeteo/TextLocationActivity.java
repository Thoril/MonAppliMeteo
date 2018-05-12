package com.example.artru.monapplimeteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.artru.monapplimeteo.task.parser.MeteoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TextLocationActivity extends AppCompatActivity {
    private static String TAG = TextLocationActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afichage_meteo);

        Bundle b = getIntent().getExtras();
        MeteoList maListe  = null;
        if (b != null) {
            maListe = b.getParcelable("data");
        }
        if (maListe != null) {
            Log.i(TAG, "Arrivé des données  Nombre de data Météo : " + maListe.size());
            if (maListe.size() != 0) {
                Date d = new Date();
                SimpleDateFormat f = new SimpleDateFormat("EEE d MMMM");
                String s = f.format(d);
                TextView dateText = findViewById(R.id.date);
                dateText.setText(s);

                TextView tempText = findViewById(R.id.temperature);
                tempText.setText(String.format("%s°C", maListe.get(0).getTemperature()));

                TextView heure = findViewById(R.id.heure);
                f = new SimpleDateFormat("HH:mm ");
                heure.setText(f.format(d));

                TextView symbol = findViewById( R.id.symbol);
                symbol.setText(maListe.get(0).getSymbole());

                TextView wind = findViewById(R.id.wind);
                wind.setText(maListe.get(0).getWindDescription());

                TextView windSpeed = findViewById(R.id.windSpeed);
                windSpeed.setText(String.format("%s%s Km / h", maListe.get(0).getWindDirection(), maListe.get(0).getWindSpeed()));
                //ImageView meteo = findViewById(R.id.meteo);

                //Placement des heures 4 prochaines données météos
                TextView h1 = findViewById(R.id.h1);
                h1.setText(maListe.get(1).getDateDebut().substring(11,16));
                TextView h2 = findViewById(R.id.h2);
                h2.setText(maListe.get(2).getDateDebut().substring(11,16));
                TextView h3 = findViewById(R.id.h3);
                h3.setText(maListe.get(3).getDateDebut().substring(11,16));
                TextView h4 = findViewById(R.id.h4);
                h4.setText(maListe.get(4).getDateDebut().substring(11,16));

                //Placement des températures des 4 prochaines données météos
                TextView temph1 = findViewById(R.id.temph1);
                temph1.setText(String.format("%s°C", maListe.get(1).getTemperature()));
                TextView temph2 = findViewById(R.id.temph2);
                temph2.setText(String.format("%s°C", maListe.get(2).getTemperature()));
                TextView temph3 = findViewById(R.id.temph3);
                temph3.setText(String.format("%s°C", maListe.get(3).getTemperature()));
                TextView temph4 = findViewById(R.id.temph4);
                temph4.setText(String.format("%s°C", maListe.get(4).getTemperature()));

                //Modification des Boutons pour avoir le details d'un jour
                ArrayList<String> days = maListe.getDays();
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

                //Modification des minimums des 5 prochains jours
                TextView minDay2 = findViewById(R.id.minDay2);
                minDay2.setText(String.format("%s°C", Float.toString(maListe.getMinDay(days.get(1)))));
                TextView minDay3 = findViewById(R.id.minDay3);
                minDay3.setText(String.format("%s°C", Float.toString(maListe.getMinDay(days.get(2)))));
                TextView minDay4 = findViewById(R.id.minDay4);
                minDay4.setText(String.format("%s°C", Float.toString(maListe.getMinDay(days.get(3)))));
                TextView minDay5 = findViewById(R.id.minDay5);
                minDay5.setText(String.format("%s°C", Float.toString(maListe.getMinDay(days.get(4)))));

                //Modification des maximums des 5 prochaines jours
                TextView maxDay2 = findViewById(R.id.maxDay2);
                maxDay2.setText(String.format("%s°C", Float.toString(maListe.getMaxDay(days.get(1)))));
                TextView maxDay3 = findViewById(R.id.maxDay3);
                maxDay3.setText(String.format("%s°C", Float.toString(maListe.getMaxDay(days.get(2)))));
                TextView maxDay4 = findViewById(R.id.maxDay4);
                maxDay4.setText(String.format("%s°C", Float.toString(maListe.getMaxDay(days.get(3)))));
                TextView maxDay5 = findViewById(R.id.maxDay5);
                maxDay5.setText(String.format("%s°C", Float.toString(maListe.getMaxDay(days.get(4)))));


            }
        }
    }
}
