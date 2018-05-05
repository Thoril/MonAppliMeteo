package com.example.artru.monapplimeteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.artru.monapplimeteo.task.parser.MeteoList;

public class TextLocationActivity extends AppCompatActivity {
    private static String TAG = TextLocationActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_location);

        Bundle b = getIntent().getExtras();
        MeteoList maListe  = null;
        if (b != null) {
            maListe = b.getParcelable("data");
        }
        if (maListe != null) {
            Log.i(TAG, "Arrivé des données  Nombre de data Météo : " + maListe.size());
            if (maListe.size() != 0) {
                TextView latText = findViewById(R.id.latitude);
                latText.setText(maListe.get(0).getSymbole());
                TextView lonText = findViewById(R.id.longitude);
                lonText.setText(maListe.get(0).getTemperature());
            }
        }
    }
}
