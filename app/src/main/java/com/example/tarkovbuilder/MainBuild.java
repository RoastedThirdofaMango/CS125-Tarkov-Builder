package com.example.tarkovbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tarkovbuilder.logic.SaveLoadHandler;
import com.example.tarkovbuilder.logic.WeaponBuild;
import com.example.tarkovbuilder.logic.WeaponStats;
import com.example.tarkovbuilder.parts.Mod;
import com.example.tarkovbuilder.parts.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainBuild extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    // private String sizeValueText = "";
    private WeaponBuild build;
    private class Node {
        private WeaponBuild.Component component;
        private List<Node> nodes = new ArrayList<>();
        private Node(Mod part) {
            /* Create a horizontal layout containing a small spacer with height match_parent and width 20dp
              and a vertical layout with height wrap_content and width match_parent.  In the vertical layout,
              create text boxes with the name of the attachment point over spinners populated with the available
              attachments for that attachment point using the getCompatible method in Mod.
            */

        }
        private void addToNodes(Node toAdd) {
            nodes.add(toAdd);
        }
    }

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

        LinearLayout rootHlayout = findViewById(R.id.rootHlayout);
        rootHlayout.setVisibility(View.GONE);

        Spinner rootSpinner = findViewById(R.id.spinnerRoot);
        List<String> weapons = new ArrayList<>();
        weapons.add("none");
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
    private void addPart(Mod toAdd, Node current) {
        Node newPart = new Node(toAdd);
        current.addToNodes(newPart);
        updateStats();
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // This doesn't crash the emulator, so I guess everything works sort of?  Currently the spinners don't show up, but then again I haven't assigned them to a view.
        TextView textView = (TextView) view;
        String data = (String) textView.getText();
        Mod mod = Mod.mods.get(data);
        if (mod instanceof Weapon) {
            build = new WeaponBuild((Weapon) mod);
            updateStats();
        }
        LinearLayout linearLayout1 = findViewById(R.id.LL1);
        if (mod != null) {
            Map<String, List<String>> attachmentPoints = mod.getAttachmentPoints();
            List<String> attachmentList = new ArrayList<>(attachmentPoints.keySet());
            for (String attachmentPoint : attachmentList) {
                if (attachmentPoints.get(attachmentPoint) != null) {
                    LinearLayout layout = new LinearLayout(this);
                    List<String> compatNames = new ArrayList<>();
                    for (Mod m : Mod.getCompatible(attachmentPoints.get(attachmentPoint))) {
                        compatNames.add(m.getName());
                    }

                    ArrayAdapter<String> compatible = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, compatNames);
                    Spinner spinner = new Spinner(this);
                    spinner.setAdapter(compatible);
                    spinner.setOnItemSelectedListener(this);
                    Space space = new Space(this);
                    space.setLayoutParams(new LinearLayout.LayoutParams(20, LinearLayout.LayoutParams.MATCH_PARENT));
                    TextView text = new TextView(this);
                    text.setText(attachmentPoint);
                    text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    layout.addView(space);
                    layout.addView(text);
                    layout.addView(spinner);
                    linearLayout1.addView(layout);
                }
            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
