package com.example.artru.monapplimeteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artru.monapplimeteo.task.parser.MeteoList;

import java.text.SimpleDateFormat;
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
                SimpleDateFormat f = new SimpleDateFormat("EEE, MMM d, yyyy");
                String s = f.format(d);
                TextView dateText = findViewById(R.id.date);
                dateText.setText(s);
                TextView tempText = findViewById(R.id.temperature);
                tempText.setText(maListe.get(0).getTemperature());
                TextView heure = findViewById(R.id.heure);
                f = new SimpleDateFormat("HH:mm ");
                s = f.format(d);
                heure.setText(s);
                TextView symbol = findViewById( R.id.symbol);
                symbol.setText(maListe.get(0).getSymbole());
                TextView wind = findViewById(R.id.wind);
                wind.setText(maListe.get(0).getWindDescription());
                ImageView meteo = findViewById(R.id.meteo);
                //meteo.setImageResource(R.id.);
            }
        }
    }
}
