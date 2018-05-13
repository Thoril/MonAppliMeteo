package com.example.artru.monapplimeteo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artru.monapplimeteo.task.parser.MeteoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by artru on 12/05/2018.
 */

public class DetailsDayActivity extends AppCompatActivity {

    private static String TAG = DetailsDayActivity.class.getName();
    private MeteoList listMeteoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_day);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            listMeteoData = b.getParcelable("data");
        }
        if (listMeteoData != null) {

            //Affichage de la date
            TextView date = findViewById(R.id.date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d =null;
            try {
                d = sdf.parse(listMeteoData.get(0).getDateDebut().substring(0, 10));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat f = new SimpleDateFormat("EEEE d MMMM yyyy");
            date.setText(f.format(d));

            //Affichage du premier creneau horaire
            TextView h1 = findViewById(R.id.h1);
            h1.setText(listMeteoData.get(0).getDateDebut().substring(11,16));
            TextView temp1 = findViewById((R.id.temp1));
            temp1.setText(String.format("%s°C", listMeteoData.get(0).getTemperature()));
            TextView humidity1 = findViewById(R.id.humidity1);
            humidity1.setText(String.format("Humidité: %s%%", listMeteoData.get(0).getHumidity()));
            TextView clouds1 = findViewById(R.id.clouds1);
            clouds1.setText(String.format("Couverture nuageuse: %s", listMeteoData.get(0).getClouds()));
            TextView windSpeed1 = findViewById(R.id.windspeed1);
            windSpeed1.setText(String.format("Vitesse du vent: %s Km / h", listMeteoData.get(0).getWindSpeed()));
            TextView windDirection1 = findViewById(R.id.windDirection1);
            windDirection1.setText(String.format("Direction: %s", listMeteoData.get(0).getWindDirection()));
            ImageView symb1 = findViewById(R.id.symbole1);
            symb1.setImageResource(listMeteoData.get(0).getImage());

            //Affichage du 2eme creneau horaire
            TextView h2 = findViewById(R.id.h2);
            h2.setText(listMeteoData.get(1).getDateDebut().substring(11,16));
            TextView temp2 = findViewById((R.id.temp2));
            temp2.setText(String.format("%s°C", listMeteoData.get(1).getTemperature()));
            TextView humidity2 = findViewById(R.id.humidity2);
            humidity2.setText(String.format("Humidité: %s%%", listMeteoData.get(1).getHumidity()));
            TextView clouds2 = findViewById(R.id.clouds2);
            clouds2.setText(String.format("Couverture nuageuse: %s", listMeteoData.get(1).getClouds()));
            TextView windSpeed2 = findViewById(R.id.windspeed2);
            windSpeed2.setText(String.format("Vitesse du vent: %s Km / h", listMeteoData.get(1).getWindSpeed()));
            TextView windDirection2 = findViewById(R.id.windDirection2);
            windDirection2.setText(String.format("Direction: %s", listMeteoData.get(1).getWindDirection()));
            ImageView symb2 = findViewById(R.id.symbole2);
            symb2.setImageResource(listMeteoData.get(1).getImage());

            //Affichage du 3eme creneau horaire
            TextView h3 = findViewById(R.id.h3);
            h3.setText(listMeteoData.get(2).getDateDebut().substring(11,16));
            TextView temp3 = findViewById((R.id.temp3));
            temp3.setText(String.format("%s°C", listMeteoData.get(2).getTemperature()));
            TextView humidity3 = findViewById(R.id.humidity3);
            humidity3.setText(String.format("Humidité: %s%%", listMeteoData.get(2).getHumidity()));
            TextView clouds3 = findViewById(R.id.clouds3);
            clouds3.setText(String.format("Couverture nuageuse: %s", listMeteoData.get(2).getClouds()));
            TextView windSpeed3 = findViewById(R.id.windspeed3);
            windSpeed3.setText(String.format("Vitesse du vent: %s Km / h", listMeteoData.get(2).getWindSpeed()));
            TextView windDirection3 = findViewById(R.id.windDirection3);
            windDirection3.setText(String.format("Direction: %s", listMeteoData.get(2).getWindDirection()));
            ImageView symb3 = findViewById(R.id.symbole3);
            symb3.setImageResource(listMeteoData.get(2).getImage());

            //Affichage du 4eme creneau horaire
            TextView h4 = findViewById(R.id.h4);
            h4.setText(listMeteoData.get(3).getDateDebut().substring(11,16));
            TextView temp4 = findViewById((R.id.temp4));
            temp4.setText(String.format("%s°C", listMeteoData.get(3).getTemperature()));
            TextView humidity4 = findViewById(R.id.humidity4);
            humidity4.setText(String.format("Humidité: %s%%", listMeteoData.get(3).getHumidity()));
            TextView clouds4 = findViewById(R.id.clouds4);
            clouds4.setText(String.format("Couverture nuageuse: %s", listMeteoData.get(3).getClouds()));
            TextView windSpeed4 = findViewById(R.id.windspeed4);
            windSpeed4.setText(String.format("Vitesse du vent: %s Km / h", listMeteoData.get(3).getWindSpeed()));
            TextView windDirection4 = findViewById(R.id.windDirection4);
            windDirection4.setText(String.format("Direction: %s", listMeteoData.get(3).getWindDirection()));
            ImageView symb4 = findViewById(R.id.symbole4);
            symb4.setImageResource(listMeteoData.get(3).getImage());

            //Affichage du 5eme creneau horaire
            TextView h5 = findViewById(R.id.h5);
            h5.setText(listMeteoData.get(4).getDateDebut().substring(11,16));
            TextView temp5 = findViewById((R.id.temp5));
            temp5.setText(String.format("%s°C", listMeteoData.get(4).getTemperature()));
            TextView humidity5 = findViewById(R.id.humidity5);
            humidity5.setText(String.format("Humidité: %s%%", listMeteoData.get(4).getHumidity()));
            TextView clouds5 = findViewById(R.id.clouds5);
            clouds5.setText(String.format("Couverture nuageuse: %s", listMeteoData.get(4).getClouds()));
            TextView windSpeed5 = findViewById(R.id.windspeed5);
            windSpeed5.setText(String.format("Vitesse du vent: %s Km / h", listMeteoData.get(4).getWindSpeed()));
            TextView windDirection5 = findViewById(R.id.windDirection5);
            windDirection5.setText(String.format("Direction: %s", listMeteoData.get(4).getWindDirection()));
            ImageView symb5 = findViewById(R.id.symbole5);
            symb5.setImageResource(listMeteoData.get(4).getImage());

            //Affichage du 6eme creneau horaire
            TextView h6 = findViewById(R.id.h6);
            h6.setText(listMeteoData.get(5).getDateDebut().substring(11,16));
            TextView temp6 = findViewById((R.id.temp6));
            temp6.setText(String.format("%s°C", listMeteoData.get(5).getTemperature()));
            TextView humidity6 = findViewById(R.id.humidity6);
            humidity6.setText(String.format("Humidité: %s%%", listMeteoData.get(5).getHumidity()));
            TextView clouds6 = findViewById(R.id.clouds6);
            clouds6.setText(String.format("Couverture nuageuse: %s", listMeteoData.get(5).getClouds()));
            TextView windSpeed6 = findViewById(R.id.windspeed6);
            windSpeed6.setText(String.format("Vitesse du vent: %s Km / h", listMeteoData.get(5).getWindSpeed()));
            TextView windDirection6 = findViewById(R.id.windDirection6);
            windDirection6.setText(String.format("Direction: %s", listMeteoData.get(5).getWindDirection()));
            ImageView symb6 = findViewById(R.id.symbole6);
            symb6.setImageResource(listMeteoData.get(5).getImage());

            //Affichage du 7eme creneau horaire
            TextView h7 = findViewById(R.id.h7);
            h7.setText(listMeteoData.get(6).getDateDebut().substring(11,16));
            TextView temp7 = findViewById((R.id.temp7));
            temp7.setText(String.format("%s°C", listMeteoData.get(6).getTemperature()));
            TextView humidity7 = findViewById(R.id.humidity7);
            humidity7.setText(String.format("Humidité: %s%%", listMeteoData.get(6).getHumidity()));
            TextView clouds7 = findViewById(R.id.clouds7);
            clouds7.setText(String.format("Couverture nuageuse: %s", listMeteoData.get(6).getClouds()));
            TextView windSpeed7 = findViewById(R.id.windspeed7);
            windSpeed7.setText(String.format("Vitesse du vent: %s Km / h", listMeteoData.get(6).getWindSpeed()));
            TextView windDirection7 = findViewById(R.id.windDirection7);
            windDirection7.setText(String.format("Direction: %s", listMeteoData.get(6).getWindDirection()));
            ImageView symb7 = findViewById(R.id.symbole7);
            symb7.setImageResource(listMeteoData.get(6).getImage());

            //Affichage du 8eme creneau horaire
            TextView h8 = findViewById(R.id.h8);
            h8.setText(listMeteoData.get(7).getDateDebut().substring(11,16));
            TextView temp8 = findViewById((R.id.temp8));
            temp8.setText(String.format("%s°C", listMeteoData.get(7).getTemperature()));
            TextView humidity8 = findViewById(R.id.humidity8);
            humidity8.setText(String.format("Humidité: %s%%", listMeteoData.get(7).getHumidity()));
            TextView clouds8 = findViewById(R.id.clouds8);
            clouds8.setText(String.format("Couverture nuageuse: %s", listMeteoData.get(7).getClouds()));
            TextView windSpeed8 = findViewById(R.id.windspeed8);
            windSpeed8.setText(String.format("Vitesse du vent: %s Km / h", listMeteoData.get(7).getWindSpeed()));
            TextView windDirection8 = findViewById(R.id.windDirection8);
            windDirection8.setText(String.format("Direction: %s", listMeteoData.get(7).getWindDirection()));
            ImageView symb8 = findViewById(R.id.symbole8);
            symb8.setImageResource(listMeteoData.get(7).getImage());
        }
    }

}
