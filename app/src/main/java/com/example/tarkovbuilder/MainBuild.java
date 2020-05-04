package com.example.tarkovbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tarkovbuilder.logic.SaveLoadHandler;
import com.example.tarkovbuilder.logic.WeaponBuild;
import com.example.tarkovbuilder.logic.WeaponStats;
import com.example.tarkovbuilder.parts.Mod;

import java.util.ArrayList;
import java.util.List;

public class MainBuild extends AppCompatActivity {
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
            SaveLoadHandler.load(null);
        });

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
        // String sizeValueText = "" + size[0] + "x" + size[1];

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
        // sizeValue.setText(sizeValueText);

    }
    private void addPart(Mod toAdd, Node current) {
        Node newPart = new Node(toAdd);
        current.addToNodes(newPart);
        updateStats();
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
