package org.example;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.Amulety.Drops;
import org.example.Amulety.Mistyk;
import org.example.Amulety.amulety;
import org.example.command.*;
import java.util.Objects;
import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin implements CommandExecutor, Listener {
    public static WorldGuardPlugin worldGuard;

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Rejestrujemy executor komendy
        Objects.requireNonNull(getCommand("wymien")).setExecutor(new exchangeCrystal());
        Objects.requireNonNull(getCommand("addcrystal")).setExecutor(new addCrystal());
        Objects.requireNonNull(getCommand("enchanter")).setExecutor(new guiCrystal());
        Objects.requireNonNull(getCommand("rozmien")).setExecutor(new exchangeSCrystal());
        Objects.requireNonNull(getCommand("kupklucz")).setExecutor(new buyKey());
        Objects.requireNonNull(getCommand("guikrysztal")).setExecutor(new guiCrystal());
        Objects.requireNonNull(getCommand("getnie")).setExecutor(new getMistyk());
        Objects.requireNonNull(getCommand("niebianski")).setExecutor(new org.example.command.mistyk());
        Objects.requireNonNull(getCommand("upeff")).setExecutor(new UpEff());
        Objects.requireNonNull(getCommand("upfor")).setExecutor(new UpFor());
        Objects.requireNonNull(getCommand("test")).setExecutor(new guiAmulety());
        Objects.requireNonNull(getCommand("tradeexp")).setExecutor(new tradeexp());
        Objects.requireNonNull(getCommand("amulet")).setExecutor(new org.example.command.amulety());
        Bukkit.getPluginManager().registerEvents(new guiCrystal(), this);
        getServer().getPluginManager().registerEvents(new Mistyk(), this);
        Bukkit.getPluginManager().registerEvents(new amulety(), this);
        Bukkit.getPluginManager().registerEvents(new guiAmulety(), this);
        Bukkit.getPluginManager().registerEvents(new Drops(), this);
        Bukkit.getPluginManager().registerEvents(new org.example.command.mistyk(), this);


        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        ConsoleCommandSender console = getServer().getConsoleSender();

        if (plugin instanceof WorldGuardPlugin) {
            worldGuard = (WorldGuardPlugin) plugin;
            String message = ChatColor.translateAlternateColorCodes('&', "&7[&5Mystick&fPickaxe&7] &aWorldGuard Found. Support added.");
            console.sendMessage(message);
        } else {//plugin.getServer().getConsoleSender().sendMessage
            String message = ChatColor.translateAlternateColorCodes('&', "&7[&5Mystick&fPickaxe&7] &4[WARNING] &cWorldGuard not found. Support will not be available.");
            console.sendMessage(message);
        }

        loadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin wyłączony
        //saveConfig();
        //String message = ChatColor.translateAlternateColorCodes('&', "&7[&5Mystick&fPickaxe&7] &aConfig saved.");
        //getServer().getConsoleSender().sendMessage(message);

    }
    // Metoda do wczytywania pliku konfiguracyjnego
    private void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            // Tworzenie pliku konfiguracyjnego, jeśli nie istnieje
            saveResource("config.yml", false);
        }
        // Wczytywanie pliku konfiguracyjnego
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig() {
        try {
            config.save(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            getLogger().warning("Nie udało się zapisać pliku konfiguracyjnego!");
        }
    }
}
