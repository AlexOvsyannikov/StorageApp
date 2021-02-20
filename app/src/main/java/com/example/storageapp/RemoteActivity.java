package com.example.storageapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class RemoteActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        try {
            TalkToBack talker = new TalkToBack();
            talker.getRemoteData();
            JSONObject json = talker.getRemote();
            Log.d("JSON", json.toString());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
            Toast toast = Toast.makeText(getApplicationContext(), "Не удалось подключиться к серверу", Toast.LENGTH_LONG);
            toast.show();
        }


    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
