package com.example.tarkovbuilder.logic;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public abstract class SaveLoadHandler {
    public static void save(WeaponBuild toSave) {
        JsonObject saved = new JsonObject();
        WeaponBuild.Component root = toSave.getRoot();
        String name = root.getValue().getName();
        saved.addProperty("part", name);
        saved.add("parts", addToSave(root.getAttachments()));
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
        return new WeaponBuild(toLoad);
    }
}
