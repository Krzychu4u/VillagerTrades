package org.example.Utils;

import org.bukkit.ChatColor;
public class Color {

    public static String fix(String text) {
        return ChatColor.translateAlternateColorCodes('&', text.replace("<<", "«").replace(">>","»"));
    }
}
