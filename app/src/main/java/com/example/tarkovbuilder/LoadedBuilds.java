package com.example.tarkovbuilder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tarkovbuilder.logic.WeaponBuild;
import com.example.tarkovbuilder.parts.Weapon;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoadedBuilds extends AppCompatActivity {
    private static int countBuilds = 0;
    private static ArrayList<WeaponBuild> toLoad;
    private static WeaponBuild objectToLoad;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_build);
        setUpUI();
    }
    private void setUpUI() {
        ConstraintLayout layout = findViewById(R.id.CL1);
        SharedPreferences sharedPreferences = getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Weapon Build", null);
        Type type = new TypeToken<ArrayList<WeaponBuild>>() {}.getType();
        toLoad = gson.fromJson(json, type);
        if (toLoad == null) {
            toLoad = new ArrayList<>();
        }
        for (WeaponBuild build: toLoad) {
            countBuilds += 1;
            Button button = new Button(this);
            WeaponBuild.Component root = build.getRoot();
            String name = root.getValue().getName();
            CharSequence name1 = name;
            button.setText(name1);
            button.setOnClickListener(v -> {
                objectToLoad = build;
                Intent intent = new Intent(this, MainBuild.class);
                setResult(150, intent);
                finish();
            });
            layout.addView(button);
        }
        if (countBuilds == 0) {
            Button goBack = findViewById(R.id.goBack_button);
            goBack.setOnClickListener(v -> {
                finish();
            });
        }
    }
    public static WeaponBuild loadData() {
        return objectToLoad;
    }
}
