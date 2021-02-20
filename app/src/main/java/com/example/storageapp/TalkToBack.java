package com.example.storageapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;

import static java.lang.System.in;

public class TalkToBack {
    private Bitmap scheme;
    private JSONObject remote;

    public TalkToBack() throws Exception {
        final URL url= new URL("http://192.168.0.109:3000");
        final URL forImage = new URL("http://192.168.0.109:3000/get_storage_scheme");
        try{
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream in = forImage.openStream();
                        scheme =  BitmapFactory.decodeStream(in);
                        Log.d("SCHEME", String.valueOf(scheme));
                        Files.copy(in, Paths.get("scheme.png"), StandardCopyOption.REPLACE_EXISTING);
                        in.close();
                    } catch (IOException e) {
                        Log.e("ERROR IN THREAD", e.getMessage());
                        e.printStackTrace();
                    }

                }
            });
            thread.start();
            thread.join();
            Log.d("OK", "MADE");
        }
        catch (Exception e){
            Log.e("ERROR IN TRY", e.getMessage());
            e.printStackTrace();
            throw new Exception("LOL");
        }
    }

    public Bitmap getScheme() {
        return scheme;
    }

    public void getRemoteData(){
        String url = "http://192.168.0.109:3000/get_remote_json";
        Log.d("START", "GETREMoteDaTa");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        remote = response;
                        Log.d("RESPONSE", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error in json", error.getMessage());
                        remote = null;
                    }
                });
    }

    public JSONObject getRemote() {
        return remote;
    }
}
