package com.example.tarkovbuilder.logic;

import com.example.tarkovbuilder.parts.Barrel;
import com.example.tarkovbuilder.parts.Bullet;
import com.example.tarkovbuilder.parts.Mod;
import com.example.tarkovbuilder.parts.Weapon;

import java.util.List;

public abstract class WeaponStats {
    public static double getHorizontalRecoil(List<Mod> parts) {
        double recoilChange = 0;
        double baseRecoilH = 0;
        for (Mod part : parts) {
            if (part instanceof Weapon) {
                Weapon partWeapon = (Weapon) part;
                baseRecoilH = partWeapon.getRecoilH();
            }
            recoilChange += part.getRecoilChange();
        }
        return baseRecoilH * (1 + recoilChange);
    }
    public static double getVerticalRecoil(List<Mod> parts) {
        double recoilChange = 0;
        double baseRecoilV = 0;
        for (Mod part : parts) {
            if (part instanceof Weapon) {
                Weapon partWeapon = (Weapon) part;
                baseRecoilV = partWeapon.getRecoilV();
            }
            recoilChange += part.getRecoilChange();
        }
        return baseRecoilV * (1 + recoilChange);
    }
    public static double getErgo(List<Mod> parts) {
        double ergo = 0;
        for (Mod part : parts) {
            ergo += part.getErgoChange();
        }
        return ergo;
    }
    public static double getAccuracy(List<Mod> parts) {
        double accuracyChange = 0;
        double baseAccuracy = 0;
        for (Mod part : parts) {
            if (part instanceof Barrel) {
                Barrel partBarrel = (Barrel) part;
                baseAccuracy = partBarrel.getAccuracy();
            }
            accuracyChange += part.getAccuracyChange();
        }
        return baseAccuracy * (1 + accuracyChange);
    }
    public static double getVelocity(List<Mod> parts) {
        double velocityChange = 0;
        double baseVelocity = 0;
        for (Mod part : parts) {
            if (part instanceof Bullet) {
                Bullet partBullet = (Bullet) part;
                baseVelocity = partBullet.getVelocity();
            }
            velocityChange += part.getVelocityChange();
        }
        return baseVelocity * (1 + velocityChange);
    }
    public static int getFireRate(List<Mod> parts) {
        for (Mod part : parts) {
            if (part instanceof Weapon) {
                Weapon partWeapon = (Weapon) part;
                return partWeapon.getFireRate();
            }
        }
        return 0;
    }
    public static String getCaliber(List<Mod> parts) {
        for (Mod part : parts) {
            if (part instanceof Weapon) {
                Weapon partWeapon = (Weapon) part;
                return partWeapon.getCaliber();
            }
        }
        return "";
    }
    public static double getWeight(List<Mod> parts) {
        double weight = 0;
        for (Mod part : parts) {
            weight += part.getWeight();
        }
        return weight;
    }
    public static int getDamage(List<Mod> parts) {
        for (Mod part : parts) {
            if (part instanceof Bullet) {
                Bullet partBullet = (Bullet) part;
                return partBullet.getDamage();
            }
        }
        return 0;
    }
    public static int getPenetration(List<Mod> parts) {
        for (Mod part : parts) {
            if (part instanceof Bullet) {
                Bullet partBullet = (Bullet) part;
                return partBullet.getPenetration();
            }
        }
        return 0;
    }
    public static int[] getSize(WeaponBuild weapon) {
        return weapon.getSize();
    }
}
