package org.example.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.Utils.Color;
import org.example.Utils.HasEnough;
import org.example.Utils.RemoveCrystal;

public class UpFor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }

        Player player = (Player) sender;
        ItemStack heldItem = player.getInventory().getItemInMainHand();

        if (heldItem.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "Musisz trzymać narzędzie w ręce!");
            return true;
        }

        Inventory playerInventory = player.getInventory();
        int currentEfficiencyLevel = getEfficiencyLevel(heldItem);
        int costToUpgrade = calculateUpgradeCost(currentEfficiencyLevel);

        if (costToUpgrade == 0) {
            int costToUpgrade2 = calculateUpgradeCost2(currentEfficiencyLevel);
            if (costToUpgrade2 == 0){
                player.sendMessage(ChatColor.RED + "Masz juz maksymalna fortune.");
                return true;
            }
            if (HasEnough.hasEnoughScrystal(playerInventory) >= costToUpgrade2)  {
                new RemoveCrystal().removeSCrystal(playerInventory, costToUpgrade2);
                setEfficiencyLevel(heldItem, currentEfficiencyLevel + 1);
                player.sendMessage(ChatColor.GREEN + "Ulepszono fortune narzędzia! Nowa fortuna: " + (currentEfficiencyLevel + 1));
                return true;

            }
            player.sendMessage(Color.fix("&7Nie masz wystarczająco &5&lSuper &5&lKrysztalow &7do ulepszenia wydajności narzędzia."));
            return true;

        }

        if (HasEnough.hasEnoughCrystal(playerInventory) >= costToUpgrade) {

            new RemoveCrystal().removeCrystals(playerInventory, costToUpgrade);

            setEfficiencyLevel(heldItem, currentEfficiencyLevel + 1);
            player.sendMessage(ChatColor.GREEN + "Ulepszono wydajność narzędzia! Nowa fortuna: " + (currentEfficiencyLevel + 1));
            return true;
        }

        player.sendMessage(Color.fix("&7Nie masz wystarczająco &dKrysztalow &7do ulepszenia szczescia narzędzia."));
        return true;
    }

    private int getEfficiencyLevel(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null && meta.hasEnchants() && meta.getEnchants().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
            return meta.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);
        }
        return 0;
    }

    private void setEfficiencyLevel(ItemStack itemStack, int level) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, level, true);
            itemStack.setItemMeta(meta);
        }
    }

    private int calculateUpgradeCost(int currentEfficiencyLevel) {
        if(currentEfficiencyLevel == 0)  {return 1;}
        if (currentEfficiencyLevel == 1) {return 3;}
        if (currentEfficiencyLevel == 2) {return 8;}
        if (currentEfficiencyLevel == 3) {return 25;}
        if (currentEfficiencyLevel == 4) {return 80;}
        else {return 0;}
    }
    private int calculateUpgradeCost2(int currentEfficiencyLevel) {
        if (currentEfficiencyLevel == 5)  {return 2;}
        if (currentEfficiencyLevel == 6)  {return 5;}
        if (currentEfficiencyLevel == 7)  {return 10;}
        if (currentEfficiencyLevel == 8)  {return 20;}
        if (currentEfficiencyLevel == 9)  {return 40;}
        if (currentEfficiencyLevel == 10) {return 55;}
        if (currentEfficiencyLevel == 11) {return 70;}
        if (currentEfficiencyLevel == 12) {return 85;}
        if (currentEfficiencyLevel == 13) {return 100;}
        if (currentEfficiencyLevel == 14) {return 115;}
        if (currentEfficiencyLevel == 15) {return 130;}
        if (currentEfficiencyLevel == 16) {return 145;}
        if (currentEfficiencyLevel == 17) {return 160;}
        if (currentEfficiencyLevel == 18) {return 175;}
        if (currentEfficiencyLevel == 19) {return 200;}
        if (currentEfficiencyLevel == 20) {return 0;}
        else {return 0;}
    }
}

