package org.example.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example.item.CrystalManager;

public class tradeexp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if(player.getLevel() >= 20){
                CrystalManager crystalManager = new CrystalManager(1);
                crystalManager.addCrystalsToInventory(player.getInventory());
                int a = player.getLevel();
                player.setLevel(a - 20);
            }

        } else {
            sender.sendMessage("Ta komenda jest dostępna tylko dla graczy!");
        }
        return true;
    }

/*
    public void tradeExp (Player player){
        if(player.getLevel() >= 20){
            CrystalManager crystalManager = new CrystalManager(1);
            crystalManager.addCrystalsToInventory(player.getInventory());
            int a = player.getLevel();
            player.setLevel(a - 20);
        }
    }*/
}
