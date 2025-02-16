package com.example.tarkovbuilder.logic;

import com.example.tarkovbuilder.parts.Mod;
import com.example.tarkovbuilder.parts.Weapon;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class WeaponBuild {
    public class Component {
        private List<Component> attachments = new ArrayList<>();
        private Mod value;
        private boolean removed;
        private Component(Mod setMod) {
            value = setMod;
            removed = false;
        }
        public List<Component> getAttachments() {
            return attachments;
        }
        public Mod getValue() {
            return value;
        }
        public Component addAttachment(Mod toAdd) {
            Component toReturn = new Component(toAdd);
            attachments.add(toReturn);
            return toReturn;
        }
        public boolean isRemoved() {
            return removed;
        }
        public void removeChild(Component toRemove) {
            attachments.remove(toRemove);
            toRemove.destroyChildren();
        }
        private void destroyChildren() {
            removed = true;
            for (Component child : attachments) {
                child.destroyChildren();
            }
            attachments.clear();
        }
    }
    // Constructors
    public WeaponBuild (Weapon base) {
        root = new Component(base);
    }
    public WeaponBuild(JsonObject toLoad) {
        String rootName = toLoad.get("part").getAsString();
        root = new Component(Mod.mods.get(rootName));
        addAll(root, toLoad.getAsJsonArray("parts"));
    }
    // The root of the weapon build, always instanceOf Weapon
    private Component root;
    // Public getter for root.
    public Component getRoot() {
        return root;
    }
    // Recursive tree building function for loading a build from JSON.
    private void addAll(Component current, JsonArray parts) {
        for (JsonElement partE : parts) {
            JsonObject part = (JsonObject) partE;
            String partName = part.get("part").getAsString();
            Component toAdd = new Component(Mod.mods.get(partName));
            current.attachments.add(toAdd);
            addAll(toAdd, part.getAsJsonArray("parts"));
        }
    }
}
