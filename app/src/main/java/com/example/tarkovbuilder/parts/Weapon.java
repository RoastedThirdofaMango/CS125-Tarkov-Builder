package com.example.tarkovbuilder.parts;

import com.google.gson.JsonObject;



public class Weapon extends Mod {
    private double recoilV;
    private double recoilH;
    private int fireRate;
    private String caliber;
    /**
     * Size in stash in rows x columns format.
     */
    private int[] size;
    public Weapon(JsonObject stats) {
        super(stats);
        recoilV = stats.get("recoilV").getAsDouble();
        recoilH = stats.get("recoilH").getAsDouble();
        fireRate = stats.get("fireRate").getAsInt();
        caliber = stats.get("caliber").getAsString();
        size = new int[] {stats.get("size").getAsJsonArray().get(0).getAsInt(),
                stats.get("size").getAsJsonArray().get(1).getAsInt()};
    }
    public double getRecoilV() {
        return recoilV;
    }
    public double getRecoilH() {
        return recoilH;
    }
    public int getFireRate() {
        return fireRate;
    }
    public String getCaliber() {
        return caliber;
    }
    public int[] getSize() {
        return size;
    }
}
