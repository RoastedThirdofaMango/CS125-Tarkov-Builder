package com.example.tarkovbuilder.parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mod {
    /**
     * Map holding all the mods that have a given tag.
     */
    public static Map<String, List<Mod>> tagMap = new HashMap<>();
    protected String name;
    protected double recoilChange = 0;
    protected double ergoChange = 0;
    protected double accuracyChange = 0;
    protected double velocityChange = 0;
    protected double weight = 0;
    /**
     * Maps the name of an attachment point with a list of tags corresponding to lists of parts.
     */
    protected Map<String, List<String>> attachmentPoints;
    /**
     * Effect the mod has on the size of the weapon.
     * Format is (left, right, down, up).
     */
    private int[] sizeChange = new int[4];
    public Mod(/*stats*/ String[] tags, Map<String, List<String>> setAttachmentPoints) {
        for (String tag : tags) {
            /* Initialize the stats */
            if (!(tagMap.containsKey(tag))) {
                tagMap.put(tag, new ArrayList<Mod>());
            }
            tagMap.get(tag).add(this);
            attachmentPoints = setAttachmentPoints;
        }
    }
    public static List<Mod> getCompatable(List<String> tags) {
        List<Mod> toReturn = new ArrayList<>();
        for (String tag : tags) {
            toReturn.addAll(tagMap.get(tag));
        }
        return toReturn;
    }
    public double getRecoilChange() {
        return recoilChange;
    }
    public double getErgoChange() {
        return ergoChange;
    }
    public double getAccuracyChange() {
        return accuracyChange;
    }
    public double getVelocityChange() {
        return velocityChange;
    }
    public double getWeight() {
        return weight;
    }
    public int[] getSizeChange() {
        return sizeChange;
    }
    public String getName() {
        return name;
    }
}
