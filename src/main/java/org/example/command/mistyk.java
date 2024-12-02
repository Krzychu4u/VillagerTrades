package org.example.command;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.Utils.Color;

import java.util.*;

public class mistyk implements CommandExecutor, Listener {

    private final Map<Integer, String> itemCommands = new HashMap<>();

    public mistyk() {
        // Konfiguracja elementów i odpowiadających im komend
        configureItems();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            openMultiCommandGUI(player);
        } else {
            sender.sendMessage("Ta komenda jest dostępna tylko dla graczy!");
        }
        return true;
    }

    private void configureItems() {
        itemCommands.put(13, "getnie");
    }

    private ItemStack createExampleItem(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            switch (material) {
                case DIAMOND_PICKAXE:
                    List<String> lore = new ArrayList<>();
                    meta.setDisplayName(ChatColor.AQUA + "Niebianski kilof");
                    lore.add(Color.fix("&fNajlepszy kilof na serwerze"));
                    lore.add(Color.fix(" "));
                    lore.add(Color.fix(" &fKoszt 600 &5&lSuperKrysztalow"));
                    lore.add(Color.fix(" &aKliknij, aby kupic!"));
                    meta.setLore(lore);
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    break;
                default:
                    break;
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    private void openMultiCommandGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "Niebianski kilof");

        // Dodano wywołanie configureItems()
        configureItems();

        for (int i = 0; i < 27; i++) {
            Material material = i % 9 == 0 ? Material.BLACK_STAINED_GLASS_PANE : getMaterialForSlot(i);

            ItemStack item = createExampleItem(material);

            if (item != null) {
                gui.setItem(i, item);
            }
        }
        player.openInventory(gui);
    }

    private Material getMaterialForSlot(int slot) {
        return switch (slot) {
            case 13 -> Material.DIAMOND_PICKAXE;
            case 4 -> Material.YELLOW_STAINED_GLASS_PANE;
            case 12 -> Material.YELLOW_STAINED_GLASS_PANE;
            case 14 -> Material.YELLOW_STAINED_GLASS_PANE;
            case 22 -> Material.YELLOW_STAINED_GLASS_PANE;
            default -> Material.BLACK_STAINED_GLASS_PANE;
        };
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Niebianski kilof")) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                Player player = (Player) event.getWhoClicked();

                int slot = event.getSlot();
                if (itemCommands.containsKey(slot)) {
                    player.performCommand(itemCommands.get(slot));
                }
            }
        }
    }
}
