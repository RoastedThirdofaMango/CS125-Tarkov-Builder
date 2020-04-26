package com.example.tarkovbuilder.parts;

import java.util.Map;
import java.util.HashMap;


public class Weapon {
    protected static Map<String, Weapon> weapons = new HashMap<>();
    private String name;
    private double recoilV;
    private double recoilH;
    private double ergo;
    private double accuracy;
    public Weapon () {
        /* Initialize all the stats based on a passed JSON object */
    }
}
