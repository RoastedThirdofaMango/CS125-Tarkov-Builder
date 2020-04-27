package com.example.tarkovbuilder.logic;

import com.google.gson.JsonObject;

public abstract class SaveLoadHandler {
    public static boolean save(WeaponBuild toSave) {
        return true;
    }
    public static WeaponBuild load(JsonObject toLoad) {
        return null;
    }
}
