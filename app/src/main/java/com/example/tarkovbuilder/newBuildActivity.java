package com.example.tarkovbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarkovbuilder.logic.SaveLoadHandler;
import com.example.tarkovbuilder.logic.WeaponBuild;
import com.google.gson.JsonObject;

public class newBuildActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_build);
        setUpUI();
    }
    private void setUpUI() {
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //new one
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        Button load = findViewById(R.id.loadButton);
        load.setOnClickListener(unused -> {
            SaveLoadHandler.load(new JsonObject());
        });
        Button save = findViewById(R.id.saveButton);
        save.setOnClickListener(unused -> {
            SaveLoadHandler.save(new WeaponBuild(null));
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
