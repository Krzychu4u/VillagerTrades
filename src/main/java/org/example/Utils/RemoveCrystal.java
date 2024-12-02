package org.example.Utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RemoveCrystal {

    public void removeSCrystal(Inventory inventory, int crystalsToRemove) {
        // Usuwamy 1 Scrystal z inwentarza gracza
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType() == Material.ECHO_SHARD && new HasEnchantment().hasEnchantment(item, Enchantment.DEPTH_STRIDER)) {
                int amount = item.getAmount();
                if (amount <= crystalsToRemove) {
                    crystalsToRemove -= amount;
                    inventory.setItem(i, null);
                } else {
                    item.setAmount(amount - crystalsToRemove);
                    inventory.setItem(i, item);
                    crystalsToRemove = 0;
                }

                if (crystalsToRemove == 0) {
                    break;
                }
            }
        }
    }
    public void removeCrystals (Inventory inventory,int crystalsToRemove){
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);

            if (item != null && item.getType() == Material.AMETHYST_SHARD && new HasEnchantment().hasEnchantment(item, Enchantment.DEPTH_STRIDER)) {
                int amount = item.getAmount();

                if (amount <= crystalsToRemove) {
                    inventory.setItem(i, null);
                    crystalsToRemove -= amount;
                } else {
                    item.setAmount(amount - crystalsToRemove);
                    inventory.setItem(i, item);
                    crystalsToRemove = 0;
                }

                if (crystalsToRemove == 0) {
                    break;
                }
            }
        }
    }
}



