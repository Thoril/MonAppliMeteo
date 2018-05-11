package com.example.artru.monapplimeteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artru.monapplimeteo.task.parser.MeteoList;

import java.text.DateFormat;
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
                tempText.setText(maListe.get(0).getTemperature());

                TextView heure = findViewById(R.id.heure);
                f = new SimpleDateFormat("HH:mm ");
                heure.setText(f.format(d));

                TextView symbol = findViewById( R.id.symbol);
                symbol.setText(maListe.get(0).getSymbole());

                TextView wind = findViewById(R.id.wind);
                wind.setText(maListe.get(0).getWindDescription());

                //ImageView meteo = findViewById(R.id.meteo);

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


            }
        }
    }
}
