package com.example.tarkovbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tarkovbuilder.logic.ModsInitializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream inputStream = this.getResources().openRawResource(R.raw.mods);
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        JsonObject mods = JsonParser.parseReader(in).getAsJsonObject();
        ModsInitializer.initializeMods(mods);
        Intent intent = new Intent(this, MainBuild.class);
        startActivity(intent);
    }
}
