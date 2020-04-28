package com.example.tarkovbuilder;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarkovbuilder.logic.SaveLoadHandler;

import java.util.ArrayList;
import java.util.List;

public class oldBuildActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;
    private Spinner spinner6;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_build);
        setUpUI();
    }
    private void setUpUI() {

        // Save/load buttons
        Button save = findViewById(R.id.saveBuild);
        save.setOnClickListener(unused -> {
            SaveLoadHandler.save(null);
        });
        Button load = findViewById(R.id.loadBuild);
        load.setOnClickListener(unused -> {
            SaveLoadHandler.load(null);
        });

        // Declare all spinners
        spinner1 = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.spinner5);
        spinner6 = findViewById(R.id.spinner6);
        // Spinner 1 setup
        List<String> spinner1Strings = new ArrayList<>();
        spinner1Strings.add("0");
        spinner1Strings.add("1");
        spinner1Strings.add("2");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner1Strings);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
        // Spinner 2 setup
        List<String> spinner2Strings = new ArrayList<>();
        spinner2Strings.add("0");
        spinner2Strings.add("1");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner2Strings);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        // Spinner 3 setup
        /* Unused because it is the final entry in that tree
        List<String> spinner3Strings = new ArrayList<>();
        spinner3Strings.add("0");
        spinner3Strings.add("1");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner3Strings);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        */
        // Spinner 4 setup
        List<String> spinner4Strings = new ArrayList<>();
        spinner4Strings.add("0");
        spinner4Strings.add("1");
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner4Strings);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(this);
        // Spinner 5 setup
        List<String> spinner5Strings = new ArrayList<>();
        spinner5Strings.add("0");
        spinner5Strings.add("1");
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner5Strings);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(this);
        // Spinner 6 needs no setup because it is also a leaf node

        /* When 0 is selected, none of the children should display.  When 1 is selected, only one child should display, etc.
         */
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        LinearLayout layout1 = findViewById(R.id.layout_subpart1);
        LinearLayout layout2 = findViewById(R.id.layout_subpart2);
        LinearLayout layout3 = findViewById(R.id.layout_subpart3);
        LinearLayout layout4 = findViewById(R.id.layout_subpart4);
        LinearLayout layout5 = findViewById(R.id.layout_subpart5);
        long parentID = parent.getId();
        if (parentID == R.id.spinner) {
            if (text.equals("0")) {
                layout1.setVisibility(LinearLayout.GONE);
                layout2.setVisibility(LinearLayout.GONE);
            } else if (text.equals("1")) {
                layout1.setVisibility(LinearLayout.VISIBLE);
                layout2.setVisibility(LinearLayout.GONE);
            } else {
                layout1.setVisibility(LinearLayout.VISIBLE);
                layout2.setVisibility(LinearLayout.VISIBLE);
            }
       }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
