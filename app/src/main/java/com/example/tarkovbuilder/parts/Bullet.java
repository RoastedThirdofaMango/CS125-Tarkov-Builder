package com.example.tarkovbuilder.parts;

import java.util.HashMap;
import java.util.List;

public class Bullet extends Mod {
    private int damage;
    private int penetration;
    private double velocity;
    public Bullet(String setCaliber, int setDamage, int setPen, double setVelocity) {
        super(new String[] {setCaliber}, new HashMap<String, List<String>>());
        damage = setDamage;
        penetration = setPen;
        velocity = setVelocity;
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
