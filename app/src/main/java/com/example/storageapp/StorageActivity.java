package com.example.storageapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StorageActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
    }

    public void Back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
