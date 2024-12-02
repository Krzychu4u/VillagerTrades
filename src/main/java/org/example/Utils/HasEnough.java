package org.example.Utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HasEnough {

    public static int hasEnoughCrystal(Inventory inventory) {
        int crystalCount = 0;
        // Sprawdzamy ilość Crystal w inwentarzu gracza
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == Material.AMETHYST_SHARD && new HasEnchantment().hasEnchantment(item, Enchantment.DEPTH_STRIDER)) {
                crystalCount += item.getAmount();
            }
        }
        return crystalCount;
    }

    public static int hasEnoughScrystal(Inventory inventory) {
        int crystalSCount = 0;
        // Sprawdzamy ilość SCrystal w inwentarzu gracza
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == Material.ECHO_SHARD && new HasEnchantment().hasEnchantment(item, Enchantment.DEPTH_STRIDER)) {
                crystalSCount += item.getAmount();
            }
        }
        return crystalSCount;
    }
    public static boolean hasEnoughScrystalMistyk(Inventory inventory) {
        int crystalCount = 0;
        // Sprawdzamy ilość SCrystal w inwentarzu gracza
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == Material.ECHO_SHARD && new HasEnchantment().hasEnchantment(item, Enchantment.DEPTH_STRIDER)) {
                crystalCount += item.getAmount();
            }
        }
        return crystalCount >= 600;
    }
    public static boolean hasEnoughCrystalSpeed(Inventory inventory) {
        int crystalCount = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == Material.ECHO_SHARD && new HasEnchantment().hasEnchantment(item, Enchantment.DEPTH_STRIDER))
                crystalCount += item.getAmount();
        }
        return (crystalCount >= 10);
    }
    public static boolean hasEnoughCrystalAmulety(Inventory inventory) {
        int crystalCount = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == Material.AMETHYST_SHARD && (new HasEnchantment()).hasEnchantment(item, Enchantment.DEPTH_STRIDER))
                crystalCount += item.getAmount();
        }
        return (crystalCount >= 400);
    }
}

