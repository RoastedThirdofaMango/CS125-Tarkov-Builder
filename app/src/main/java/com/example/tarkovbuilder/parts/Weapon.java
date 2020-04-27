package com.example.tarkovbuilder.parts;

import java.util.List;
import java.util.Map;


public class Weapon extends Mod {
    private double recoilV;
    private double recoilH;
    private int fireRate;
    private String caliber;
    /**
     * Size in stash in rows x columns format.
     */
    private int[] size;
    public Weapon(Map<String, List<String>> setAttachmentPoints) {
        super(new String[0], setAttachmentPoints);
        /* Initialize all the stats based on a passed JSON object */
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
