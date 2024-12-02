package org.example.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.Utils.Color;
import org.example.Utils.HasEnough;
import org.example.Utils.RemoveCrystal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class getMistyk implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }

        Inventory playerInventory = player.getInventory();

        if (HasEnough.hasEnoughScrystalMistyk(playerInventory)) {
            new RemoveCrystal().removeSCrystal(playerInventory, 600);

            ItemStack superKilof = new ItemStack(Material.DIAMOND_PICKAXE);

            ItemMeta meta = superKilof.getItemMeta();
            assert  meta != null;
            meta.setDisplayName(ChatColor.AQUA + "Niebianski Kilof");
            meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Wlasciciel: " + ChatColor.YELLOW + player.getDisplayName()));

            meta.setUnbreakable(true);
            //meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            superKilof.setItemMeta(meta);

            player.getInventory().addItem(superKilof);
            player.sendMessage("Otrzymałeś " + ChatColor.AQUA + "Niebianski kilof!");
            return true;
        } else {
            player.sendMessage(Color.fix("&7Nie masz wystarczającej ilości &5&lSuperKryształow!"));
            return false;
        }
    }
}