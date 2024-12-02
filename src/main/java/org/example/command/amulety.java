package org.example.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.Main;
import org.example.Utils.Color;
import org.example.Utils.RemoveCrystal;
import org.example.Utils.HasEnough;

public class amulety implements CommandExecutor {
    //private final Main plugin;

    //public amulety(Main plugin) {
    //    this.plugin = plugin;
    //}


    @Override
    public boolean onCommand(CommandSender p, Command cmd, String s, String[] args) {
        if (!(p instanceof Player player)) {
            p.sendMessage(Color.fix("&cTa komenda jest dostępna tylko dla graczy!"));
            return true;
        }

        if (args.length == 0 ){
            p.sendMessage(Color.fix("&cPoprawnie uzycie: /amulet [speed, autokrysztal, autosell]"));
            return true;
        }
        Inventory playerInventory = player.getInventory();
        if (args[0].equalsIgnoreCase("speed")){
            if (HasEnough.hasEnoughCrystalSpeed(playerInventory)) {
                new RemoveCrystal().removeSCrystal(playerInventory, 10);
                ItemStack amulet = new ItemStack(Material.SUGAR);
                ItemMeta meta = amulet.getItemMeta();
                assert meta != null;
                meta.setDisplayName(Color.fix("&9&lAmulet Szybkosci III"));
                meta.setUnbreakable(true);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                amulet.setItemMeta(meta);
                player.getInventory().addItem(amulet);
                return true;
            } else {
                p.sendMessage(Color.fix("&cNie masz wystarczajacej ilosci &5&lSuperKrysztalow!"));
                return false;
            }
        }
        if (args[0].equalsIgnoreCase("autokrysztal")){
            if (HasEnough.hasEnoughCrystalAmulety(playerInventory)) {
                new RemoveCrystal().removeCrystals(playerInventory, 400);

                ItemStack wymiana = new ItemStack(Material.FIRE_CHARGE);
                ItemMeta meta = wymiana.getItemMeta();
                assert meta != null;
                meta.setDisplayName(Color.fix("&9&lAutoKrysztal"));

                meta.setUnbreakable(true);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                wymiana.setItemMeta(meta);
                player.getInventory().addItem(wymiana);
                return true;
            } else {
                p.sendMessage(Color.fix("&cNie masz wystarczajacej ilosci &d&lKrysztalow!"));
                return false;
            }
        }
        if (args[0].equalsIgnoreCase("autosell")){
            if (HasEnough.hasEnoughCrystalAmulety(playerInventory)) {
                new RemoveCrystal().removeCrystals(playerInventory, 400);

                ItemStack sell = new ItemStack(Material.END_CRYSTAL);

                ItemMeta meta = sell.getItemMeta();
                assert meta != null;
                meta.setDisplayName(Color.fix("&9&lAutoSell"));

                meta.setUnbreakable(true);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                sell.setItemMeta(meta);
                player.getInventory().addItem(sell);
                return true;
            } else {
                p.sendMessage(Color.fix("&cNie masz wystarczajacej ilosci &d&lKrysztalow!"));
                return false;
            }
        }
        return false;
    }
}


