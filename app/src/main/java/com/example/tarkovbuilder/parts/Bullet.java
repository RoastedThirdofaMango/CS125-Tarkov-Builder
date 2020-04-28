package com.example.tarkovbuilder.parts;

import com.google.gson.JsonObject;

public class Bullet extends Mod {
    private int damage;
    private int penetration;
    private double velocity;
    public Bullet(JsonObject stats) {
        super(stats);
        damage = stats.get("damage").getAsInt();
        penetration = stats.get("penetration").getAsInt();
        velocity = stats.get("velocity").getAsDouble();
    }
    public int getDamage() {
        return damage;
    }
    public int getPenetration() {
        return penetration;
    }
    public double getVelocity() {
        return velocity;
    }
}
