package org.example.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class buyKey implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }

        Inventory playerInventory = player.getInventory();

        // Sprawdzamy czy gracz ma co najmniej 100 Crystal
        if (hasEnoughSCrystal(playerInventory)) {
            // Usuwamy 100 Crystal z inwentarza gracza
            removeSCrystal(playerInventory);

            addKey(player.getInventory());

            player.sendMessage("Wymieniłeś " + ChatColor.DARK_PURPLE + "Superkrysztal" + ChatColor.RESET +" na 100 " + ChatColor.LIGHT_PURPLE + "Krysztalow");
        } else {
            player.sendMessage("Nie masz wystarczającej ilości " + ChatColor.DARK_PURPLE + "SuperKrysztalow");
        }

        return true;
    }
    public void addKey(Inventory inventory) {
        // Dodajemy określoną liczbę kryształów do inwentarza gracza
        ItemStack addKey = new ItemStack(Material.TRIPWIRE_HOOK, 1);

        ItemMeta meta = addKey.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "SuperKrysztal");

        // Ustaw efekt polysku
        meta.setUnbreakable(true); // Sprawia, że item nie ma paska wytrzymałości
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        addKey.setItemMeta(meta);

        inventory.addItem(addKey);
    }
    private boolean hasEnoughSCrystal(Inventory inventory) {
        int crystalCount = 0;

        // Sprawdzamy ilość Crystal w inwentarzu gracza
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == Material.ECHO_SHARD) {
                crystalCount += item.getAmount();
            }
        }

        return crystalCount >= 1;
    }

    private void removeSCrystal(Inventory inventory) {
        int sCrystalsToRemove = 1;

        // Usuwamy 100 crystal z inwentarza gracza
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType() == Material.ECHO_SHARD && hasEnchantment(item, Enchantment.DEPTH_STRIDER)) {
                int amount = item.getAmount();
                if (amount <= sCrystalsToRemove) {
                    sCrystalsToRemove -= amount;
                    inventory.setItem(i, null);
                } else {
                    item.setAmount(amount - sCrystalsToRemove);
                    inventory.setItem(i, item);
                    sCrystalsToRemove = 0;
                }

                if (sCrystalsToRemove == 0) {
                    break;
                }
            }
        }
    }

    private boolean hasEnchantment(ItemStack item, Enchantment enchantment) {
        return item.getEnchantments().containsKey(enchantment);
    }
}
