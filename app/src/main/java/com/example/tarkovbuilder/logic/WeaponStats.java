package com.example.tarkovbuilder.logic;

import com.example.tarkovbuilder.parts.Barrel;
import com.example.tarkovbuilder.parts.Bullet;
import com.example.tarkovbuilder.parts.Mod;
import com.example.tarkovbuilder.parts.Weapon;

public abstract class WeaponStats {
    public static double getHorizontalRecoil(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        Weapon base = (Weapon) root.getValue();
        double baseRecoilH = base.getRecoilH();
        double recoilChange = getHorizontalRecoil(root);
        return baseRecoilH * (1 + recoilChange);
    }
    private static double getHorizontalRecoil(WeaponBuild.Component current) {
        double recoilChange = current.getValue().getRecoilChange();
        for (WeaponBuild.Component attachment : current.getAttachments()) {
            recoilChange += getHorizontalRecoil(attachment);
        }
        return recoilChange;
    }
    public static double getVerticalRecoil(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        Weapon base = (Weapon) root.getValue();
        double baseRecoilV = base.getRecoilV();
        double recoilChange = getVerticalRecoil(root);
        return baseRecoilV * (1 + recoilChange);
    }
    private static double getVerticalRecoil (WeaponBuild.Component current) {
        double recoilChange = current.getValue().getRecoilChange();
        for (WeaponBuild.Component attachment : current.getAttachments()) {
            recoilChange += getVerticalRecoil(attachment);
        }
        return recoilChange;
    }
    public static double getErgo(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        Weapon base = (Weapon) root.getValue();
        double baseErgo = base.getErgoChange();
        double ergoChange = getErgo(root);
        return baseErgo + ergoChange;
    }
    private static double getErgo(WeaponBuild.Component current) {
        double ergoChange = current.getValue().getErgoChange();
        for (WeaponBuild.Component attachment : current.getAttachments()) {
            ergoChange += getErgo(attachment);
        }
        return ergoChange;
    }
    public static double getAccuracy(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        Weapon base = (Weapon) root.getValue();
        double baseAccuracy = base.getAccuracyChange();
        double accuracyChange = getAccuracy(root);
        return baseAccuracy * (1 + accuracyChange);
    }
    private static double getAccuracy(WeaponBuild.Component current) {
        double accuracyChange = current.getValue().getAccuracyChange();
        for (WeaponBuild.Component attachment : current.getAttachments()) {
            if (attachment.getValue() instanceof Barrel) {
                accuracyChange += getAccuracy(attachment);
            }
        }
        return accuracyChange;
    }
    private static double getVelocity(WeaponBuild.Component current) {
        double velocityChange = current.getValue().getAccuracyChange();
        for (WeaponBuild.Component attachment : current.getAttachments()) {
            if (attachment.getValue() instanceof Bullet) {
                velocityChange += getAccuracy(attachment);
            }

        }
        return velocityChange;
    }
    public static double getVelocity(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        Weapon base = (Weapon) root.getValue();
        double baseVelocity = base.getVelocityChange();
        double velocityChange = getVelocity(root);
        return baseVelocity * (1 + velocityChange);
    }
    public static int getFireRate(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        Weapon base = (Weapon) root.getValue();
        return base.getFireRate();
    }
    public static String getCaliber(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        Weapon base = (Weapon) root.getValue();
        return base.getCaliber();
    }
    public static double getWeight(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        Weapon base = (Weapon) root.getValue();
        double baseWeight = base.getWeight();
        double weightChange = getWeight(root);
        return baseWeight + weightChange;
    }
    private static double getWeight(WeaponBuild.Component current) {
        double weightChange = current.getValue().getWeight();
        for (WeaponBuild.Component attachment : current.getAttachments()) {
            weightChange += getWeight(attachment);
        }
        return weightChange;
    }
    private static int getDamage(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        for (WeaponBuild.Component attachment : root.getAttachments()) {
            if (attachment.getValue() instanceof Bullet) {
                Bullet partBullet = (Bullet) attachment.getValue();
                return partBullet.getDamage();
            }
        }
        return 0;
    }
    public static int getPenetration(WeaponBuild build) {
        WeaponBuild.Component root = build.getRoot();
        for (WeaponBuild.Component attachment : root.getAttachments()) {
            if (attachment.getValue() instanceof Bullet) {
                Bullet partBullet = (Bullet) attachment.getValue();
                return partBullet.getPenetration();
            }
        }
        return 0;
    }
    public static int[] getSize(WeaponBuild build) {
        Weapon baseWeapon = (Weapon) build.getRoot().getValue();
        int[] baseSize = baseWeapon.getSize();
        int[] sizeChange = getSizeMod(build.getRoot());
        return new int[] {baseSize[0] + sizeChange[0] + sizeChange[1],
                baseSize[1] + sizeChange[2] + sizeChange[3]};
    }
    private static int[] getSizeMod(WeaponBuild.Component current) {
        if (current.getAttachments().isEmpty()) {
            return current.getValue().getSizeChange();
        }
        int[] biggestChange = {0, 0, 0, 0};
        for (WeaponBuild.Component attachment : current.getAttachments()) {
            int[] candidate = getSizeMod(attachment);
            for (int i = 0; i < 3; i++) {
                if (candidate[i] > biggestChange[i]) {
                    biggestChange[i] = candidate[i];
                }
            }
        }
        return biggestChange;
    }
}
