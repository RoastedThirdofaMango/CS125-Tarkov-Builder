package com.example.tarkovbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int newId = 10000034;
    private static final int oldId = 10000345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Intent intent = new Intent(this, oldBuildActivity.class);
            startActivity(intent);
        });
    }
}
