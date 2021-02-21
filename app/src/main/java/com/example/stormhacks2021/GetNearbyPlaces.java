package com.example.stormhacks2021;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GetNearbyPlaces extends AsyncTask<Object, Void, String>{
    private String googlePlacesData;
    private GoogleMap mMap;

    protected String doInBackground(Object... objects){
        mMap = (GoogleMap)objects[0];
        String url = (String) objects[1];

        try {
            googlePlacesData = downloadUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    protected void onPostExecute(String s){
        List<HashMap<String, String>> nearbyPlaceList;
        JsonParser parser = new JsonParser();
        nearbyPlaceList = parser.parseResult(s);
        Log.d("Nearby Places: ","called parse method");
        showNearbyPlaces(nearbyPlaceList);
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList) {
//        if (mMap != null)
//            mMap.clear();
        for (int i=0; i < nearbyPlaceList.size(); i++) {

            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            //double rating = Double.parseDouble(googlePlace.get("rating"));
            String placeName = googlePlace.get("name");

            Log.d("Search locations", "name: " + placeName);

            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName);
            //markerOptions.snippet("Rating: " + rating);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        }
    }

    private String downloadUrl(String string) throws IOException {
        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String data = builder.toString();
        reader.close();
        //Log.d("Download URL", "Returning data = " + data);
        return data;
    }
}