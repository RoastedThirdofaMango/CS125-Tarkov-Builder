package com.example.tarkovbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tarkovbuilder.logic.SaveLoadHandler;
import com.example.tarkovbuilder.logic.WeaponBuild;
import com.example.tarkovbuilder.parts.Mod;

public class MainBuild extends AppCompatActivity {
    private String weaponNameText = "";
    private String weightValueText = "";
    private String accuracyValueText = "";
    private String recoilVValueText = "";
    private String recoilHValueText = "";
    private String ergoValueText = "";
    private String fireRateValueText = "";
    private String velocityValueText = "";
    private String damageValueText = "";
    private String penValueText = "";
    // private String sizeValueText = "";
    private WeaponBuild build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_build);
        setUpUI();
    }
    private void setUpUI() {
        // Save/load buttons

        Button save = findViewById(R.id.loadBuild);
        save.setOnClickListener(unused -> {
            SaveLoadHandler.save(build);
        });
        Button load = findViewById(R.id.loadBuild);
        load.setOnClickListener(unused -> {
            SaveLoadHandler.load(null);
        });

        TextView weaponName = findViewById(R.id.weaponName);
        TextView weightValue = findViewById(R.id.weightValue);
        TextView accuracyValue = findViewById(R.id.accuracyValue);
        TextView recoilVValue = findViewById(R.id.recoilVValue);
        TextView recoilHValue = findViewById(R.id.recoilHValue);
        TextView ergoValue = findViewById(R.id.ergoValue);
        TextView fireRateValue = findViewById(R.id.fireRateValue);
        TextView velocityValue = findViewById(R.id.velocityValue);
        TextView damageValue = findViewById(R.id.damageValue);
        TextView penValue = findViewById(R.id.penValue);
        // TextView sizeValue = findViewById(R.id.sizeValue);


    }
    private void updateUI() {

    }
    private void addPart(Mod toAdd) {
        /* Create a horizontal layout containing a small spacer with height match_parent and width 20dp
          and a vertical layout with height wrap_content and width match_parent.  In the vertical layout,
          create text boxes with the name of the attachment point over spinners populated with the available
          attachments for that attachment point using the getCompatible method in Mod.
        */
    }
    /*public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
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
    }*/
}
