package org.example.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.example.Utils.Color;
import org.example.Utils.HasFreeSlots;
import org.example.Utils.RemoveCrystal;
import org.example.item.CrystalManager;

import org.example.Utils.HasEnough;

public class exchangeSCrystal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }

        Inventory playerInventory = player.getInventory();
        if (!(player.getInventory().firstEmpty() == -1)) {
        //if (HasFreeSlots.hasFreeSlots(player, 2)){
            // Sprawdzamy czy gracz ma co najmniej 1 SCrystal
            if (HasEnough.hasEnoughScrystal(playerInventory) >= 1) {

                new RemoveCrystal().removeSCrystal(playerInventory, 1); // Usuwamy 1 SCrystal z inwentarza gracza

                int numberOfCrystals = Integer.parseInt(String.valueOf(100)); // Dodajemy 100 Crystal do inwentarza gracza

                CrystalManager crystalManager = new CrystalManager(numberOfCrystals); // Tworzenie instancji CrystalManager

                crystalManager.addCrystalsToInventory(player.getInventory()); // Dodawanie kryształów do inwentarza gracza

                player.sendMessage(ChatColor.GRAY + "Wymieniłeś " + ChatColor.DARK_PURPLE + "Superkrysztal" + ChatColor.GRAY + " na 100 " + ChatColor.LIGHT_PURPLE + "Krysztalow");
            } else {
                player.sendMessage("Nie masz wystarczającej ilości " + ChatColor.DARK_PURPLE + "SuperKrysztalow");
            }
            return true;
        }
        player.sendMessage(Color.fix("&c&lUpss! &7Masz za mało miejsca w inventory, musisz mieć 2 wolne sloty!"));
        return true;
    }


}