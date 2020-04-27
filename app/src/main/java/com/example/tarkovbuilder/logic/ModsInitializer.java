package com.example.tarkovbuilder.logic;

import com.example.tarkovbuilder.parts.Barrel;
import com.example.tarkovbuilder.parts.Bullet;
import com.example.tarkovbuilder.parts.Mod;
import com.example.tarkovbuilder.parts.Weapon;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class ModsInitializer {
    public static void initializeMods(JsonObject modsList) {
        JsonArray mods = modsList.getAsJsonArray("mods");
        for (JsonElement mod : mods) {
            JsonObject modObject = mod.getAsJsonObject();
            String modType = modObject.get("type").getAsString();
            if (modType.equals("bullet")) {
                new Bullet(modObject);
            } else if (modType.equals("barrel")) {
                new Barrel(modObject);
            } else if (modType.equals("weapon")) {
                new Weapon(modObject);
            } else {
                new Mod(modObject);
            }
        }
    }
}
