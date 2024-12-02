package org.example.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SuperCrystalManager {

    private int numberOfSuperCrystals;

    public SuperCrystalManager(int numberOfCrystals) {
        this.numberOfSuperCrystals = numberOfCrystals;
    }

    public void addSuperCrystalsToInventory(Inventory inventory) {
        // Dodajemy określoną liczbę kryształów do inwentarza gracza
        ItemStack enchantedSuperCrystal = new ItemStack(Material.ECHO_SHARD, numberOfSuperCrystals);

        ItemMeta meta = enchantedSuperCrystal.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Super Krysztal");

        // Ustaw efekt polysku
        meta.setUnbreakable(true); // Sprawia, że item nie ma paska wytrzymałości
        meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        enchantedSuperCrystal.setItemMeta(meta);

        inventory.addItem(enchantedSuperCrystal);
    }

}

