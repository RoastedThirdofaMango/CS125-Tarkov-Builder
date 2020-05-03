package com.example.tarkovbuilder.parts;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Required fields for Gson message: name (String), weight (double), attachmentPoints (array with Strings and arrays of Strings), type (String)
 */
public class Mod {
    /**
     * Map holding all the mods that have a given tag.
     */
    private static Map<String, List<Mod>> tagMap = new HashMap<>();
    public static Map<String, Mod> mods = new HashMap<>();
    private String name;
    private double recoilChange;
    private double ergoChange;
    private double accuracyChange;
    private double velocityChange;
    private double weight;
    /**
     * Maps the name of an attachment point with a list of tags corresponding to lists of parts.
     */
    private Map<String, List<String>> attachmentPoints;
    /**
     * Effect the mod has on the size of the weapon.
     * Format is (left, right, down, up).
     */
    private int[] sizeChange;
    public Mod(JsonObject stats) {
        name = stats.get("name").getAsString();
        if (mods.containsKey(name)) {
            System.out.println("DEBUG: Duplicate mod detected");
        }
        mods.put(name, this);
        weight = stats.get("weight").getAsDouble();
        try {
            recoilChange = stats.get("recoilChange").getAsDouble();
        } catch (Exception e) {
            recoilChange = 0;
        }
        try {
            ergoChange = stats.get("ergoChange").getAsDouble();
        } catch (Exception e) {
            ergoChange = 0;
        }
        try {
            accuracyChange = stats.get("accuracyChange").getAsDouble();
        } catch (Exception e) {
            accuracyChange = 0;
        }
        try {
            velocityChange = stats.get("velocityChange").getAsDouble();
        } catch (Exception e) {
            velocityChange = 0;
        }
        try {
            sizeChange = new int[]{stats.get("sizeChange").getAsJsonArray().get(0).getAsInt(),
                    stats.get("sizeChange").getAsJsonArray().get(1).getAsInt(),
                    stats.get("sizeChange").getAsJsonArray().get(2).getAsInt(),
                    stats.get("sizeChange").getAsJsonArray().get(3).getAsInt()};
        } catch (Exception e) {
            sizeChange = new int[] {0, 0, 0, 0};
        }

        String tag = stats.get("tag").getAsString();
        if (!(tagMap.containsKey(tag))) {
            tagMap.put(tag, new ArrayList<Mod>());
        }
        tagMap.get(tag).add(this);

        JsonArray attachmentPointsJson = stats.getAsJsonArray("attachmentPoints");
        Map<String, List<String>> setAttachmentPoints = new HashMap<>();
        for (JsonElement element : attachmentPointsJson) {
            JsonObject attachmentPoint = element.getAsJsonObject();
            String key = attachmentPoint.get("attachmentPoint").getAsString();
            List<String> values = new ArrayList<>();
            JsonArray attachmentTags = attachmentPoint.get("tags").getAsJsonArray();
            for (JsonElement attachmentTag : attachmentTags) {
                values.add(attachmentTag.getAsString());
            }
            setAttachmentPoints.put(key, values);
        }
        attachmentPoints = setAttachmentPoints;
    }
    public static List<Mod> getCompatible(List<String> tags) {
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
    public Map<String, List<String>> getAttachmentPoints() {
        return attachmentPoints;
    }
}
