package com.example.artru.monapplimeteo.task.parser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class GeocodingParser {
    public  static final String TAG = GeocodingParser.class.getName();
    private GeocodingData data;
    private String xmlData;
    private XmlPullParserFactory factory;
    public enum Element {Null, Other, Adresse, Longitude, Latitude, Location}

    public GeocodingParser(String xmlData) throws XmlPullParserException {
        this.xmlData = xmlData;
        factory = XmlPullParserFactory.newInstance();
        data = null;
    }

    private GeocodingData parse(){
        GeocodingData result = new GeocodingData();
        try {
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));
            int eventType = xpp.getEventType();
            Element lastElement = Element.Null;
            Boolean location = false;
            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(TAG, "Start Document");
                        break;
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "Start tag " + xpp.getName());
                        if (xpp.getName().equals("location")) {
                            lastElement = Element.Location;
                            location = true;
                        } else if (xpp.getName().equals("formatted_adress")) {
                            lastElement = Element.Adresse;
                        }else if (xpp.getName().equals("lat")) {
                            lastElement = Element.Latitude;
                        }else if (xpp.getName().equals("lng")) {
                            lastElement = Element.Longitude;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        Log.d(TAG, "Text " + xpp.getText());
                        if (lastElement.equals(Element.Latitude) && location) {
                            result.setLatitude(xpp.getText());
                        } else if (lastElement.equals(Element.Longitude) && location) {
                            result.setLongitude(xpp.getText());
                        }else if(lastElement.equals(Element.Adresse)){
                            result.setAdresse(xpp.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "End tag " + xpp.getName());
                        if (xpp.getName().equals("location")) {
                            location = false;
                        }
                        lastElement = Element.Null;
                        break;
                }
                eventType = xpp.next();
            }
            }catch (XmlPullParserException | IOException e) {
                Log.e(TAG,"error while parsing ", e);
            }
        return result;
    }

    public GeocodingData getData(){
        if(data ==null){
            data = parse();
        }
        return data;
    }
}
