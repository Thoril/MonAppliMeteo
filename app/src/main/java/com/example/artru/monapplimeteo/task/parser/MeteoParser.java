package com.example.artru.monapplimeteo.task.parser;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class MeteoParser {
    public static final String TAG = MeteoParser.class.getName();
    private String xmlData;
    private XmlPullParserFactory factory;
    private MeteoList data ;

    public MeteoParser(String xmlData) throws XmlPullParserException {
        this.xmlData = xmlData;
        factory = XmlPullParserFactory.newInstance();
        data = new MeteoList();
    }

    private MeteoList parse(){
        MeteoList dataList = new MeteoList();
        Log.d(TAG, "Debut fonction parse");
        try {
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader(this.xmlData));
            int eventType = xpp.getEventType();

            MeteoData data = new MeteoData();
            Log.d(TAG, "try fonction parse");
            boolean location =false;
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(TAG, "Start Document");
                        break;
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "Start tag " + xpp.getName());

                        switch (xpp.getName()){
                            case "time":
                                data = new MeteoData();
                                data.setDateDebut(xpp.getAttributeValue(0));
                                data.setDateFin(xpp.getAttributeValue(1));
                                Log.d(TAG, xpp.getAttributeValue(0));
                                break;
                            case "symbol":
                                data.setSymbole(xpp.getAttributeValue(1));
                                Log.d(TAG, xpp.getAttributeValue(1));
                                break;
                            case "precipitation":
                                break;
                            case "windDirection":
                                data.setWindDirection(xpp.getAttributeValue(2)  );
                                Log.d(TAG, xpp.getAttributeValue(2));
                                break;
                            case "windSpeed":
                                double windSpeed = Double.parseDouble(xpp.getAttributeValue(0));
                                windSpeed = windSpeed * 3.6;
                                NumberFormat nf = new DecimalFormat("0.#");
                                data.setWindSpeed(nf.format(windSpeed).replaceAll(",","."));
                                data.setWindDescription(xpp.getAttributeValue(1));
                                Log.d(TAG, xpp.getAttributeValue(0));
                                break;
                            case "temperature":
                                double tmp = Double.parseDouble(xpp.getAttributeValue(1));
                                tmp = tmp - 273;
                                NumberFormat nf2 = new DecimalFormat("0.#");
                                data.setTemperature(nf2.format(tmp).replaceAll(",","."));
                                Log.d(TAG, xpp.getAttributeValue(1));
                                break;
                            case "pressure":
                                break;
                            case "humidity":
                                data.setHumidity(xpp.getAttributeValue(0));
                                Log.d(TAG, xpp.getAttributeValue(0));
                                break;
                            case "clouds":
                                data.setClouds(xpp.getAttributeValue(0));
                                Log.d(TAG, xpp.getAttributeValue(0));
                                break;
                            case "name":
                                location = true;
                                data = new MeteoData();
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(location){
                            data.setSymbole(xpp.getText());
                            location = false;
                        }

                         break;
                    case XmlPullParser.END_TAG:
                         Log.d(TAG, "End tag " + xpp.getName());
                        if (xpp.getName().equals("time") || xpp.getName().equals("name")){
                           dataList.add(data);
                        }
                        break;

                }
                eventType = xpp.next();
            }
            return dataList;

        }catch (XmlPullParserException | IOException e) {
            Log.e(TAG,"error while parsing ", e);
        }
        return dataList;

    }

    public MeteoList getData(){
        if(data.size() == 0){
            data = parse();
        }
        return data;
    }
}
