package com.example.storageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {
    ImageView schemeImg;
    Button refresh;
    Button storage;
    Button remote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh = (Button) findViewById(R.id.refresh);

        try {
            TalkToBack talker = new TalkToBack();
            if (talker.getScheme()==null){
                throw new Exception();
            }
            schemeImg = (ImageView) findViewById(R.id.schemeImg);
            schemeImg.setImageBitmap(talker.getScheme());
            Log.d("MADE", String.valueOf(talker.getScheme()));
            refresh.setVisibility(View.INVISIBLE);
            refresh.setEnabled(false);

        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Не удалось подключиться к серверу", Toast.LENGTH_LONG);
            toast.show();
            refresh.setVisibility(View.VISIBLE);
            refresh.setEnabled(true);
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }
            });
        }

//        remote = (Button) findViewById(R.id.remote);
//        remote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, RemoteActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void RemoteActivityStart(View view){
        Intent intent = new Intent(this, RemoteActivity.class);
        startActivity(intent);
        finish();

    }

    public void StorageActivityStart(View view) {
        Intent intent = new Intent(this, StorageActivity.class);
        startActivity(intent);
        finish();

    }
}
