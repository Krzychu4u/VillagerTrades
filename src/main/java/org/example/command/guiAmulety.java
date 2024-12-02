package org.example.command;



import org.bukkit.Bukkit;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class guiAmulety implements CommandExecutor, Listener {

    private final Map<Integer, String> itemCommands = new HashMap<>();

    public guiAmulety() {
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
        itemCommands.put(10, "amulet speed");
        itemCommands.put(13, "amulet autosell");
        itemCommands.put(16, "amulet autokrysztal");
    }

    private ItemStack createExampleItem(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            switch (material) {
                case SUGAR:
                    List<String> lore = new ArrayList<>();
                    meta.setDisplayName(Color.fix("&9&lAmulet Szybkosci III"));
                    lore.add(Color.fix(" "));
                    lore.add(Color.fix(" &fKoszt 10 &5&lSuperKrysztalow"));
                    lore.add(Color.fix(" &aKliknij, aby kupic!"));
                    meta.setLore(lore);
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    break;
                case END_CRYSTAL:
                    List<String> lore1 = new ArrayList<>();
                    meta.setDisplayName(Color.fix("&9&lAutoSell"));
                    lore1.add(Color.fix(" "));
                    lore1.add(Color.fix(" &fKoszt 400 &d&lKrysztalow"));
                    lore1.add(Color.fix(" &aKliknij, aby kupic!"));
                    meta.setLore(lore1);
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    break;
                case FIRE_CHARGE:
                    List<String> lore2 = new ArrayList<>();
                    meta.setDisplayName(Color.fix("&9&lAutoKrysztal"));
                    lore2.add(Color.fix(" "));
                    lore2.add(Color.fix(" &fKoszt 400 &d&lKrysztalow"));
                    lore2.add(Color.fix(" &aKliknij, aby kupic!"));
                    meta.setLore(lore2);
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    break;
                case BLACK_STAINED_GLASS_PANE:
                    meta.setDisplayName(" ");
                    break;
                default:
                    break;
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    private void openMultiCommandGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "Szemrany typ");

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
            case 10 -> Material.SUGAR;
            case 13 -> Material.END_CRYSTAL;
            case 16 -> Material.FIRE_CHARGE;
            default -> Material.BLACK_STAINED_GLASS_PANE;
        };
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Szemrany typ")) {
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
