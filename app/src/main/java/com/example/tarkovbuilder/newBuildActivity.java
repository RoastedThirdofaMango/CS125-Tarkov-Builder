package com.example.tarkovbuilder;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class newBuildActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Spinner
    private Spinner spinnerWeapon;
    private Spinner spinnerGrip;
    private Spinner spinnerUpper;
    private Spinner spinnerBuffer;
    private Spinner spinnerStock;
    private Spinner spinnerScope;
    private Spinner spinnerBarrel;
    private Spinner spinnerGas;
    private Spinner spinnerMuzzle;
    private Spinner spinnerSuppressor;
    private Spinner spinnerHandGuard;
    private Spinner spinnerForeGrip;
    private Spinner spinnerLasers;
    private Spinner spinnerIron;
    private Spinner spinnerMount;
    private Spinner spinnerBack;
    //TextView
    private TextView textViewWeapon;
    private TextView textViewGrip;
    private TextView textViewUpper;
    private TextView textViewBuffer;
    private TextView textViewStock;
    private TextView textViewScope;
    private TextView textViewBarrel;
    private TextView textViewGas;
    private TextView textViewMuzzle;
    private TextView textViewSuppressor;
    private TextView textViewHandGuard;
    private TextView textViewForeGrip;
    private TextView textViewLasers;
    private TextView textViewIron;
    private TextView textViewMount;
    private TextView textViewBack;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_build);
        setUpUI();
    }
    private void setUpUI() {
        //weapon
        spinnerWeapon = findViewById(R.id.spinner_weapon);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeapon.setAdapter(adapter);
        spinnerWeapon.setOnItemSelectedListener(this);
        //grip
        spinnerGrip = findViewById(R.id.spinner_grip);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrip.setAdapter(adapter2);
        spinnerGrip.setOnItemSelectedListener(this);
        //upper receiver
        spinnerUpper = findViewById(R.id.spinner_upper);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUpper.setAdapter(adapter3);
        spinnerUpper.setOnItemSelectedListener(this);
        //Barrel
        spinnerBarrel = findViewById(R.id.spinner_barrel);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBarrel.setAdapter(adapter7);
        spinnerBarrel.setOnItemSelectedListener(this);
        //gas block
        spinnerGas = findViewById(R.id.spinner_gas);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGas.setAdapter(adapter8);
        spinnerGas.setOnItemSelectedListener(this);
        //muzzle device
        spinnerMuzzle = findViewById(R.id.spinner_muzzle);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMuzzle.setAdapter(adapter9);
        spinnerMuzzle.setOnItemSelectedListener(this);
        //Suppressor
        spinnerSuppressor = findViewById(R.id.spinner_suppressor);
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSuppressor.setAdapter(adapter10);
        spinnerSuppressor.setOnItemSelectedListener(this);
        //HandGuard
        spinnerHandGuard = findViewById(R.id.spinner_handguard);
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHandGuard.setAdapter(adapter11);
        spinnerHandGuard.setOnItemSelectedListener(this);
        //ForeGrip
        spinnerForeGrip = findViewById(R.id.spinner_foregrip);
        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForeGrip.setAdapter(adapter12);
        spinnerForeGrip.setOnItemSelectedListener(this);
        //Lasers/Flashlights
        spinnerLasers = findViewById(R.id.spinner_light);
        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLasers.setAdapter(adapter13);
        spinnerLasers.setOnItemSelectedListener(this);
        //Front IronSight
        spinnerIron = findViewById(R.id.spinner_frontsight);
        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIron.setAdapter(adapter14);
        spinnerIron.setOnItemSelectedListener(this);
        //Backup Sight Mount
        spinnerMount = findViewById(R.id.spinner_backmount);
        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMount.setAdapter(adapter15);
        spinnerMount.setOnItemSelectedListener(this);
        //Backup Sight
        spinnerBack = findViewById(R.id.spinner_backsight);
        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBack.setAdapter(adapter16);
        spinnerBack.setOnItemSelectedListener(this);
        //scope
        spinnerScope = findViewById(R.id.spinner_scope);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerScope.setAdapter(adapter6);
        spinnerScope.setOnItemSelectedListener(this);
        //buffer tube
        spinnerBuffer = findViewById(R.id.spinner_buffer);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuffer.setAdapter(adapter4);
        spinnerBuffer.setOnItemSelectedListener(this);
        //stock
        spinnerStock = findViewById(R.id.spinner_stock);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStock.setAdapter(adapter5);
        spinnerStock.setOnItemSelectedListener(this);
        //setUp text\\
            //weapon
        textViewWeapon = findViewById(R.id.weapon_text);
        textViewWeapon.setVisibility(View.VISIBLE);
            //grip
        textViewGrip = findViewById(R.id.grip_text);
        textViewGrip.setVisibility(View.VISIBLE);
            //upper
        textViewUpper = findViewById(R.id.upper_text);
        textViewUpper.setVisibility(View.VISIBLE);
            //barrel
        textViewBarrel = findViewById(R.id.barrel_text);
        textViewBarrel.setVisibility(View.VISIBLE);
            //gas block
        textViewGas = findViewById(R.id.gas_text);
        textViewGas.setVisibility(View.VISIBLE);
            //muzzle device
        textViewMuzzle = findViewById(R.id.muzzle_text);
        textViewMuzzle.setVisibility(View.VISIBLE);
            //suppressor
        textViewSuppressor = findViewById(R.id.suppressor_text);
        textViewSuppressor.setVisibility(View.VISIBLE);
            //handguard
        textViewHandGuard = findViewById(R.id.handguard_text);
        textViewHandGuard.setVisibility(View.VISIBLE);
            //foregrip
        textViewForeGrip = findViewById(R.id.foregrip_text);
        textViewForeGrip.setVisibility(View.VISIBLE);
            //lasers/flashlights
        textViewLasers = findViewById(R.id.light_text);
        textViewLasers.setVisibility(View.VISIBLE);
            //front ironsight
        textViewIron = findViewById(R.id.frontsight_text);
        textViewIron.setVisibility(View.VISIBLE);
            //backup sight mount
        textViewMount = findViewById(R.id.backmount_text);
        textViewMount.setVisibility(View.VISIBLE);
            //backup sight
        textViewBack = findViewById(R.id.backsight_text);
        textViewBack.setVisibility(View.VISIBLE);
            //scope
        textViewScope = findViewById(R.id.scope_text);
        textViewScope.setVisibility(View.VISIBLE);
            //buffer
        textViewBuffer = findViewById(R.id.buffer_text);
        textViewBuffer.setVisibility(View.VISIBLE);
            //stock
        textViewStock = findViewById(R.id.stock_text);
        textViewStock.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Here is my example for how I understood visibility:
        if (!text.equals("One")) {
            spinnerStock.setVisibility(View.INVISIBLE);
            textViewStock.setVisibility(View.INVISIBLE);
        } else {
            spinnerStock.setVisibility(View.VISIBLE);
            textViewStock.setVisibility(View.VISIBLE);
        }
        if (!text.equals("Two")) {
            spinnerScope.setVisibility(View.INVISIBLE);
            textViewScope.setVisibility(View.INVISIBLE);
        } else {
            spinnerScope.setVisibility(View.VISIBLE);
            textViewScope.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
