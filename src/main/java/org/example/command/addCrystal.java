package org.example.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example.Utils.Color;
import org.example.item.CrystalManager;
import org.example.item.SuperCrystalManager;

public class addCrystal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (cmd.getName().equalsIgnoreCase("addcrystal")) {
                // Sprawdzanie, czy gracz podał liczbę kryształów
                if (args.length == 1) {
                    try {
                        int numberOfCrystals = Integer.parseInt(args[0]);

                        // Tworzenie instancji CrystalManager
                        CrystalManager crystalManager = new CrystalManager(numberOfCrystals);

                        // Dodawanie kryształów do inwentarza gracza
                        crystalManager.addCrystalsToInventory(player.getInventory());
                        player.sendMessage("Dodano " + numberOfCrystals + " krysztalow do Twojego inwentarza!");
                    } catch (NumberFormatException e) {
                        player.sendMessage("Podaj poprawną liczbę krysztalow.");
                    }
                } else if (args.length == 2) {

                    String targetPlayerNick = args[0];
                    Player targetPlayer = Bukkit.getPlayer(targetPlayerNick);

                    if(targetPlayer == null){
                        player.sendMessage(Color.fix("&c&lUpss! &7Nie ma takiego gracza lub jest offline!"));
                        return true;
                    }

                    try {
                        int numberOfCrystals = Integer.parseInt(args[1]);
                        if (numberOfCrystals <= 200) {
                            // Tworzenie instancji CrystalManager
                            CrystalManager crystalManager = new CrystalManager(numberOfCrystals);

                            // Dodawanie kryształów do inwentarza gracza
                            crystalManager.addCrystalsToInventory(targetPlayer.getInventory());
                            player.sendMessage(Color.fix("&7Dodano " + numberOfCrystals + " &d&lKrysztalow &7do inventory gracza &a&l" + targetPlayerNick + "&7!"));
                            targetPlayer.sendMessage(Color.fix("&7Otrzymales " + numberOfCrystals + " &d&lKrysztalow &7do inventory!"));
                            return true;
                        } else if(numberOfCrystals % 100 == 0){
                            numberOfCrystals = numberOfCrystals/100;
                            SuperCrystalManager superCrystalManager = new SuperCrystalManager(numberOfCrystals);
                            superCrystalManager.addSuperCrystalsToInventory(targetPlayer.getInventory());

                            player.sendMessage(Color.fix("&7Dodano " + numberOfCrystals + " &5&lSuperKrysztalow &7do inventory gracza &a&l" + targetPlayerNick + "&7!"));
                            targetPlayer.sendMessage(Color.fix("&7Otrzymales " + numberOfCrystals + " &5&lSuperKrysztalow &7do inventory!"));
                            return true;
                        } else {
                            int reszta = numberOfCrystals % 100;
                            numberOfCrystals = numberOfCrystals/100;

                            SuperCrystalManager superCrystalManager = new SuperCrystalManager(numberOfCrystals);
                            superCrystalManager.addSuperCrystalsToInventory(targetPlayer.getInventory());

                            CrystalManager crystalManager = new CrystalManager(reszta);
                            crystalManager.addCrystalsToInventory(targetPlayer.getInventory());

                            player.sendMessage(Color.fix("&7Dodano " + numberOfCrystals + " &5&lSuperKrysztalow &7oraz " + reszta + " &d&lKrysztalow &7do inventory gracza &a&l" + targetPlayerNick + "&7!"));
                            targetPlayer.sendMessage(Color.fix("&7Otrzymales " + numberOfCrystals + " &5&lSuperKrysztalow &7oraz " + reszta + " &d&lKrysztalow &7do inventory!"));
                            return true;

                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage("Podaj poprawną liczbę krysztalow.");
                    }
                }else {
                    player.sendMessage("Użycie: /addcrystals <gracz> <liczba>");
                }
            }
        } else if (args.length == 2) {

            String targetPlayerNick = args[0];
            Player targetPlayer = Bukkit.getPlayer(targetPlayerNick);

            if (targetPlayer == null) {
                sender.sendMessage(Color.fix("&c&lUpss! &7Nie ma takiego gracza lub jest offline!"));
            }

            try {
                int numberOfCrystals = Integer.parseInt(args[1]);
                if (numberOfCrystals <= 200) {
                    // Tworzenie instancji CrystalManager
                    CrystalManager crystalManager = new CrystalManager(numberOfCrystals);

                    // Dodawanie kryształów do inwentarza gracza
                    crystalManager.addCrystalsToInventory(targetPlayer.getInventory());
                    sender.sendMessage(Color.fix("&7Dodano " + numberOfCrystals + " &d&lKrysztalow &7do inventory gracza &a&l" + targetPlayerNick + "&7!"));
                    targetPlayer.sendMessage(Color.fix("&7Otrzymales " + numberOfCrystals + " &d&lKrysztalow &7do inventory!"));
                    return true;
                } else if (numberOfCrystals % 100 == 0) {
                    numberOfCrystals = numberOfCrystals / 100;
                    SuperCrystalManager superCrystalManager = new SuperCrystalManager(numberOfCrystals);
                    superCrystalManager.addSuperCrystalsToInventory(targetPlayer.getInventory());

                    sender.sendMessage(Color.fix("&7Dodano " + numberOfCrystals + " &5&lSuperKrysztalow &7do inventory gracza &a&l" + targetPlayerNick + "&7!"));
                    targetPlayer.sendMessage(Color.fix("&7Otrzymales " + numberOfCrystals + " &5&lSuperKrysztalow &7do inventory!"));
                    return true;
                } else {
                    int reszta = numberOfCrystals % 100;
                    numberOfCrystals = numberOfCrystals / 100;

                    SuperCrystalManager superCrystalManager = new SuperCrystalManager(numberOfCrystals);
                    superCrystalManager.addSuperCrystalsToInventory(targetPlayer.getInventory());

                    CrystalManager crystalManager = new CrystalManager(reszta);
                    crystalManager.addCrystalsToInventory(targetPlayer.getInventory());

                    sender.sendMessage(Color.fix("&7Dodano " + numberOfCrystals + " &5&lSuperKrysztalow &7oraz " + reszta + " &d&lKrysztalow &7do inventory gracza &a&l" + targetPlayerNick + "&7!"));
                    targetPlayer.sendMessage(Color.fix("&7Otrzymales " + numberOfCrystals + " &5&lSuperKrysztalow &7oraz " + reszta + " &d&lKrysztalow &7do inventory!"));
                    return true;

                }
            }catch (NumberFormatException e) {
                sender.sendMessage("Podaj poprawną liczbę krysztalow.");
                return true;
            }
        } else {
            sender.sendMessage("/addcrystal <gracz> <ilosc>");
            return true;
        }
        return true;
    }
}
