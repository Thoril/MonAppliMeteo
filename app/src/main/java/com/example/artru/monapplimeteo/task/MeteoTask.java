package com.example.artru.monapplimeteo.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.artru.monapplimeteo.MeteoActivity;
import com.example.artru.monapplimeteo.task.parser.GeocodingData;
import com.example.artru.monapplimeteo.task.parser.GeocodingParser;
import com.example.artru.monapplimeteo.task.parser.MeteoData;
import com.example.artru.monapplimeteo.task.parser.MeteoList;
import com.example.artru.monapplimeteo.task.parser.MeteoParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MeteoTask extends AsyncTask<String, String, ArrayList<MeteoData>> {
    private static final String meteoKey = "fc39a2dd3caf13c26fa68833765bb98e";
    private static final String geocodingKey = "AIzaSyAU0AGVjWXsl94LUnF9zhCz6jQYVSD16q8";
    private static final String url_prefixed_meteo = "  http://api.openweathermap.org/data/2.5/forecast?";
    private static final String url_prefixed_geocoding = "https://maps.googleapis.com/maps/api/geocode/xml?address=";
    private static final String TAG = MeteoTask.class.getName();

    private OkHttpClient okHttpClient;
    private String url_meteo;
    private String url_geocoding;
    private AppCompatActivity parentActivity;
    private ProgressDialog progressDialog;

    public MeteoTask(AppCompatActivity parent , ProgressDialog progressDialog) {
        okHttpClient = new OkHttpClient();
        this.url_meteo = "";
        this.url_geocoding ="";
        this.parentActivity = parent;
        this.progressDialog = progressDialog;
    }

    @Override
    protected ArrayList<MeteoData> doInBackground(String... strings) {

        for(String place : strings) {
            ArrayList<GeocodingData> dataLocation = null;
            MeteoList dataMeteo;
            this.url_geocoding = url_prefixed_geocoding + place + "&key=" + geocodingKey;

            // PHASE 1: requete http pour recuperer la longitude et latitude du lieu
            publishProgress("Connexion au serveur de localisation");
            try {

                Request request = new Request.Builder().url(url_geocoding).build();
                Response http_response = this.okHttpClient.newCall(request).execute();
                Log.i(TAG, "Url geocoding:" + url_geocoding);

                String xmlData = http_response.body().string();

                // PHASE 2: parsing des données GPS
                publishProgress("Traitement des données GPS");

                GeocodingParser parser = new GeocodingParser(xmlData);
                dataLocation = parser.getData();
                Log.i(TAG, "Lat:" + dataLocation.get(0).getLatitude());
                Log.i(TAG, "Long:" + dataLocation.get(0).getLongitude());

            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }


            //PHASE 3: recherche des données météos
            publishProgress("Connexion au serveur météo");
            try {
                this.url_meteo = url_prefixed_meteo + "lat=" + dataLocation.get(0).getLatitude() + "&lon=" + dataLocation.get(0).getLongitude() + "&mode=xml&APPID=" + meteoKey+"&lang=en";
                Request request = new Request.Builder().url(url_meteo).build();
                Response http_response = this.okHttpClient.newCall(request).execute();
                String xmlData = http_response.body().string();
                Log.i(TAG, "Url Météo:" + url_meteo);

                // PHASE 4: parsing des données Météo
                publishProgress("Traitement des données Météo");
                MeteoParser parser = new MeteoParser(xmlData);
                dataMeteo = parser.getData();

                Log.i(TAG, "Nombre de data Météo: " + dataMeteo.size());
                return dataMeteo;

            }catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MeteoData> meteoData) {
        super.onPostExecute(meteoData);
        Intent intent  = new Intent(this.parentActivity,MeteoActivity.class);
        Log.i(TAG, "On post Execute Nombre de data Météo : " + meteoData.size());
        Log.i(TAG, meteoData.get(0).toString());
        intent.putExtra("data", meteoData);
        progressDialog.dismiss();
        this.parentActivity.startActivity(intent);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        this.progressDialog.setMessage(values[0]);
    }
}
