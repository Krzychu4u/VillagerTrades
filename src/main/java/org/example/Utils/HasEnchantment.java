package org.example.Utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class HasEnchantment {
    public boolean hasEnchantment(ItemStack item, Enchantment enchantment) {
        return item.getEnchantments().containsKey(enchantment);
    }
}
