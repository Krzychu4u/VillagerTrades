package org.example.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.example.Utils.HasEnough;
import org.example.Utils.RemoveCrystal;
import org.example.item.SuperCrystalManager;


public class exchangeCrystal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }

        Inventory playerInventory = player.getInventory();

        // Sprawdzamy czy gracz ma co najmniej 100 Crystal
        if (HasEnough.hasEnoughCrystal(playerInventory) >= 100) {
            // Usuwamy 100 Crystal z inwentarza gracza

            new RemoveCrystal().removeCrystals(playerInventory, 100);

            // Dodajemy 1 sCrystal do inwentarza gracza
            int numberOfSuperCrystals = Integer.parseInt(String.valueOf(1));

            // Tworzenie instancji CrystalManager
            SuperCrystalManager superCrystalManager = new SuperCrystalManager(numberOfSuperCrystals);

            // Dodawanie kryształów do inwentarza gracza
            superCrystalManager.addSuperCrystalsToInventory(player.getInventory());

            player.sendMessage("Wymieniłeś " + ChatColor.LIGHT_PURPLE + "krysztaly" + ChatColor.RESET +" na 1 " + ChatColor.DARK_PURPLE + "SuperKrysztal");
        } else {
            player.sendMessage("Nie masz wystarczającej ilości " + ChatColor.LIGHT_PURPLE + "Krysztalow");
        }

        return true;
    }

}