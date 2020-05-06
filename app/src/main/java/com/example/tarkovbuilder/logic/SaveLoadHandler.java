package com.example.tarkovbuilder.logic;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public abstract class SaveLoadHandler {
    private static ArrayList<JsonObject> savedBuilds = new ArrayList<>();
    private static WeaponBuild loadThis;
    public static void save(WeaponBuild toSave, Context context) {
        JsonObject saved = new JsonObject();
        WeaponBuild.Component root = toSave.getRoot();
        String name = root.getValue().getName();
        saved.addProperty("part", name);
        saved.add("parts", addToSave(root.getAttachments()));
        savedBuilds.add(saved);

        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(savedBuilds);
        editor.putString("Weapon Build", json);
        editor.apply();
    }
    private static JsonArray addToSave(List<WeaponBuild.Component> parts) {
        JsonArray toReturn = new JsonArray();
        for (WeaponBuild.Component attachment : parts) {
            String name = attachment.getValue().getName();
            JsonObject part = new JsonObject();
            part.addProperty("part", name);
            part.add("parts", addToSave(attachment.getAttachments()));
        }
        return toReturn;
    }
    public static WeaponBuild load(JsonObject toLoad) {
        loadThis = new WeaponBuild(toLoad);
        return loadThis;
    }
}
