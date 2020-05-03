package com.example.tarkovbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.Button;

import com.example.tarkovbuilder.logic.ModsInitializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {
    private static final int newId = 10000034;
    private static final int oldId = 10000345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream inputStream = this.getResources().openRawResource(R.raw.mods);
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        JsonObject mods = JsonParser.parseReader(in).getAsJsonObject();
        ModsInitializer.initializeMods(mods);
        setUpUI();
    }

    /**
     *Sets up the UI.
     */
    private void setUpUI() {
        Button newBuild = findViewById(R.id.createNewBuild);
        newBuild.setOnClickListener(v -> {
            Intent intent = new Intent(this, newBuildActivity.class);
            startActivity(intent);
        });
        Button oldBuild = findViewById(R.id.accessOldBuild);
        oldBuild.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainBuild.class);
            startActivity(intent);
        });
    }
}
