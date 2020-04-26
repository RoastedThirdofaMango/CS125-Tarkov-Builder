package com.example.tarkovbuilder.logic;

import com.example.tarkovbuilder.parts.Mod;
import com.example.tarkovbuilder.parts.Weapon;

import java.util.ArrayList;
import java.util.List;

public class WeaponBuild {
    private List<Component> partslist = new ArrayList<>();
    public WeaponBuild (Weapon base) {
        root = new Component(base);
    }
    private class Component {
        private List<Component> attachments = new ArrayList<>();
        private Mod value;
        private Component(Mod setMod) {
            value = setMod;
        }
    }
    private Component root;
    public void add(Mod value, int index) {
        // I need a way to get specific components by index
    }
    private void add(Component current, Mod value) {
        Component toAdd = new Component(value);
        current.attachments.add(toAdd);
    }
    public int[] getSize() {
        Weapon baseWeapon = (Weapon) root.value;
        int[] baseSize = baseWeapon.getSize();
        int[] sizeChange = getSizeMod(root);
        return new int[] {baseSize[0] + sizeChange[0] + sizeChange[1],
                baseSize[1] + sizeChange[2] + sizeChange[3]};
    }
    private int[] getSizeMod(Component current) {
        if (current.attachments.isEmpty()) {
            return current.value.getSizeChange();
        }
        int[] biggestChange = {0, 0, 0, 0};
        for (Component attachment : current.attachments) {
            int[] candidate = getSizeMod(attachment);
            for (int i = 0; i < 3; i++) {
                if (candidate[i] > biggestChange[i]) {
                    biggestChange[i] = candidate[i];
                }
            }
        }
        return biggestChange;
    }
    public List<Mod> getParts() {
        List<Mod> parts =  new ArrayList<>();
        return getParts(root, parts);
    }
    /**
     * Recursively builds the list of parts in the build.
     * @param current the current component
     * @param parts the list that is being built
     * @return the list with the current part and all it's children appended
     */
    private List<Mod> getParts(Component current, List<Mod> parts) {
        parts.add(current.value);
        for (Component part : current.attachments) {
            getParts(part, parts);
        }
        return parts;
    }
}
