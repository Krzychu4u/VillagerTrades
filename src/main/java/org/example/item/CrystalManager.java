package org.example.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CrystalManager {

    private int numberOfCrystals;

    public CrystalManager(int numberOfCrystals) {
        this.numberOfCrystals = numberOfCrystals;
    }

    public void addCrystalsToInventory(Inventory inventory) {
        // Dodajemy określoną liczbę kryształów do inwentarza gracza
        ItemStack enchantedCrystal = new ItemStack(Material.AMETHYST_SHARD, numberOfCrystals);

        ItemMeta meta = enchantedCrystal.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Krysztal");

        // Ustaw efekt polysku
        meta.setUnbreakable(true); // Sprawia, że item nie ma paska wytrzymałości
        meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setCustomModelData(10000);
        enchantedCrystal.setItemMeta(meta);

        inventory.addItem(enchantedCrystal);
    }
}
