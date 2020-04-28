package com.example.tarkovbuilder.parts;

import com.google.gson.JsonObject;

public class Barrel extends Mod {
    private double accuracy;
    public Barrel (JsonObject stats) {
        super(stats);
        accuracy = stats.get("accuracy").getAsDouble();
    }
    public double getAccuracy() {
        return accuracy;
    }
}
