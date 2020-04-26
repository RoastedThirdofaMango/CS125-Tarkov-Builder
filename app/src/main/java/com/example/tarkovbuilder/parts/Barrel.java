package com.example.tarkovbuilder.parts;

import java.util.List;
import java.util.Map;

public class Barrel extends Mod {
    private double accuracy;
    public Barrel (String tag, Map<String, List<String>> setAttachmentPoints, double setAccuracy) {
        super(new String[] {tag}, setAttachmentPoints);
        accuracy = setAccuracy;
    }
    public double getAccuracy() {
        return accuracy;
    }
}
