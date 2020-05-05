package com.example.tarkovbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tarkovbuilder.logic.SaveLoadHandler;
import com.example.tarkovbuilder.logic.WeaponBuild;
import com.example.tarkovbuilder.logic.WeaponStats;
import com.example.tarkovbuilder.parts.Mod;
import com.example.tarkovbuilder.parts.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainBuild extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private WeaponBuild build;
    private Map<LinearLayout, WeaponBuild.Component> buildMap = new HashMap<>();
    private LinearLayout.LayoutParams standard = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_build);
        setUpUI();
    }
    private void setUpUI() {
        // Save/load buttons

        Button save = findViewById(R.id.saveBuild);
        save.setOnClickListener(unused -> {
            trySave();
        });
        Button load = findViewById(R.id.loadBuild);
        load.setOnClickListener(unused -> {
            // build = SaveLoadHandler.load(null);
        });

        LinearLayout hideThis = findViewById(R.id.rootHlayout);
        hideThis.setVisibility(View.GONE);

        Spinner rootSpinner = findViewById(R.id.spinnerRoot);
        List<String> weapons = new ArrayList<>();
        weapons.add(Mod.tagMap.get("emptyWeapon").get(0).getName());
        List<Mod> assaultRifles = (Mod.tagMap.get("assaultRifle"));
        for (Mod m :assaultRifles) {
            weapons.add(m.getName());
        }
        ArrayAdapter<String> rootAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weapons);
        rootSpinner.setAdapter(rootAdapter);
        rootSpinner.setOnItemSelectedListener(this);
    }
    private boolean trySave() {
        if (build == null) {
            return false;
        }
        SaveLoadHandler.save(build);
        return true;
    }
    private void updateStats() {
        String weaponNameText = build.getRoot().getValue().getName();
        String weightValueText = "" + WeaponStats.getWeight(build);
        String accuracyValueText = "" + WeaponStats.getAccuracy(build);
        String recoilVValueText = "" + WeaponStats.getVerticalRecoil(build);
        String recoilHValueText = "" + WeaponStats.getHorizontalRecoil(build);
        String ergoValueText = "" + WeaponStats.getErgo(build);
        String fireRateValueText = "" + WeaponStats.getFireRate(build);
        String velocityValueText = "" + WeaponStats.getVelocity(build);
        String damageValueText = "" + WeaponStats.getDamage(build);
        String penValueText = "" + WeaponStats.getPenetration(build);
        // int[] size = WeaponStats.getSize(build);
        // String sizeValueText = "" + size[1] + "x" + size[0];

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
        TextView caliberValue = findViewById(R.id.caliberValue);
        // TextView sizeValue = findViewById(R.id.sizeValue);

        weaponName.setText(weaponNameText);
        weightValue.setText(weightValueText);
        accuracyValue.setText(accuracyValueText);
        recoilVValue.setText(recoilVValueText);
        recoilHValue.setText(recoilHValueText);
        ergoValue.setText(ergoValueText);
        fireRateValue.setText(fireRateValueText);
        velocityValue.setText(velocityValueText);
        damageValue.setText(damageValueText);
        penValue.setText(penValueText);
        caliberValue.setText(WeaponStats.getCaliber(build));
        // sizeValue.setText(sizeValueText);

    }
    private void cleanMap() {
        List<LinearLayout> keys = new ArrayList<>(buildMap.keySet());
        for (LinearLayout key : keys) {
            if (buildMap.get(key).isRemoved()) {
                buildMap.remove(key);
            }
        }
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Find the layout holding the spinner that was used
        LinearLayout linearLayoutParent = (LinearLayout) view.getParent().getParent();
        Spinner s = (Spinner) view.getParent();
        TextView t = (TextView) linearLayoutParent.getChildAt(0);
        linearLayoutParent.removeAllViews();
        linearLayoutParent.addView(t);
        linearLayoutParent.addView(s);

        // Find the text that was selected
        TextView textView = (TextView) view;
        String data = (String) textView.getText();
        // Find the Mod referenced by the text selected
        Mod mod = Mod.mods.get(data);
        // If this Mod is a weapon, start a new build
        if (mod instanceof Weapon) {
            build = new WeaponBuild((Weapon) mod);
            buildMap.put(linearLayoutParent, build.getRoot());
        } else {
            LinearLayout ancestor = (LinearLayout) linearLayoutParent.getParent().getParent();
            WeaponBuild.Component parentComponent = buildMap.get(ancestor);
            if (buildMap.containsKey(linearLayoutParent)) {
                WeaponBuild.Component old = buildMap.get(linearLayoutParent);
                parentComponent.removeChild(old);
            }
            WeaponBuild.Component current = parentComponent.addAttachment(mod);
            buildMap.put(linearLayoutParent, current);
        }
        updateStats();
        cleanMap();

        // Retrieve the map of attachment point names to tags of mods that fit
        Map<String, List<String>> attachmentPoints = mod.getAttachmentPoints();
        // Extract a List of only the attachment point names (to iterate over)
        List<String> attachmentList = new ArrayList<>(attachmentPoints.keySet());
        for (String attachmentPoint : attachmentList) {
            if (attachmentPoints.get(attachmentPoint) != null) {
                // Array adapter with those names
                ArrayAdapter<String> compatible = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Mod.getCompatible(attachmentPoints.get(attachmentPoint)));
                Spinner spinner = new Spinner(this);
                spinner.setAdapter(compatible);
                spinner.setOnItemSelectedListener(this);
                // TextView with the name of the attachment point
                TextView text = new TextView(this);
                text.setText(attachmentPoint);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                // Vertical layout holding attachment point name and the spinner
                LinearLayout layoutV = new LinearLayout(this);
                layoutV.setLayoutParams(standard);
                layoutV.setOrientation(LinearLayout.VERTICAL);
                layoutV.addView(text);
                layoutV.addView(spinner);
                // Build the UI component
                Space space = new Space(this);
                // NOTE: width 100 is exaggerated for testing!
                space.setLayoutParams(new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.MATCH_PARENT));
                // Horizontal layout so that we can add spacing, contains a spacer and the vertical layout with the content
                LinearLayout layoutH = new LinearLayout(this);
                layoutH.setOrientation(LinearLayout.HORIZONTAL);
                layoutH.setLayoutParams(standard);
                layoutH.addView(space);
                layoutH.addView(layoutV);
                linearLayoutParent.addView(layoutH);

                // Probably want to make some way to reference back to this particular horizontal layout in the future...
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
