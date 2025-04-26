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
import org.example.Main;
import org.example.Utils.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class guiCrystal implements CommandExecutor, Listener {

    private final Map<Integer, String> itemCommands = new HashMap<>();

    public guiCrystal() {
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
        itemCommands.put(10, "upeff");
        itemCommands.put(19, "upfor");
        itemCommands.put(16, "wymien");
        itemCommands.put(25, "rozmien");
    }

    private ItemStack createExampleItem(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            switch (material) {
                case DIAMOND_PICKAXE:
                    String name = ChatColor.translateAlternateColorCodes('&', (Main.config.getString("name", "&6&lEfficiency")));
                    meta.setDisplayName(name);
                    List<String> lore = new ArrayList<>();

                    int eff_max = Main.config.getInt("eff_max", 30);


                    for(int i=0; i<eff_max; i++)
                    {
                        String lore_msg = Main.config.getString("lore", ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc " + i + ChatColor.LIGHT_PURPLE + " 500 SuperKrysztalow");
                        String lvl = Integer.toString(eff_max-i);
                        lore_msg = lore_msg.replaceAll("&lvl", lvl);
                        String colored_lore = ChatColor.translateAlternateColorCodes('&',lore_msg);
                        lore.add(colored_lore);
                    }


                    /*
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XL: " + ChatColor.LIGHT_PURPLE + "   700 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XXXV: " + ChatColor.LIGHT_PURPLE + " 500 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XXX: " + ChatColor.LIGHT_PURPLE + "  350 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XV: " + ChatColor.LIGHT_PURPLE + "   250 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XX: " + ChatColor.LIGHT_PURPLE + "   200 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XIX: " + ChatColor.LIGHT_PURPLE + "  175 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XVIII: " + ChatColor.LIGHT_PURPLE + "160 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XVII: " + ChatColor.LIGHT_PURPLE + " 145 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XVI: " + ChatColor.LIGHT_PURPLE + "  130 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XV: " + ChatColor.LIGHT_PURPLE + "   115 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XIV: " + ChatColor.LIGHT_PURPLE + "  100 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XIII: " + ChatColor.LIGHT_PURPLE + " 85 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XII: " + ChatColor.LIGHT_PURPLE + "  75 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc XI: " + ChatColor.LIGHT_PURPLE + "   55 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc X: " + ChatColor.LIGHT_PURPLE + "    40 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc IX: " + ChatColor.LIGHT_PURPLE + "   20 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc VIII: " + ChatColor.LIGHT_PURPLE + " 10 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc VII: " + ChatColor.LIGHT_PURPLE + "  5 SuperKrysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc VI: " + ChatColor.LIGHT_PURPLE + "   2 SuperKrysztaly");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc V: " + ChatColor.LIGHT_PURPLE + "    80 Krysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc IV: " + ChatColor.LIGHT_PURPLE + "   25 Krysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc III: " + ChatColor.LIGHT_PURPLE + "  8 Krysztalow");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc II: " + ChatColor.LIGHT_PURPLE + "   3 Krysztaly");
                    lore.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Wydajnosc I: " + ChatColor.LIGHT_PURPLE + "    1 Krysztal");
                     */
                    meta.setLore(lore);
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    break;
                case EXPERIENCE_BOTTLE:
                    meta.setDisplayName(ChatColor.GREEN + "Fortuna");
                    List<String> lore2 = new ArrayList<>();
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XX: " + ChatColor.LIGHT_PURPLE + "   200 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XIX: " + ChatColor.LIGHT_PURPLE + "  175 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XVIII: " + ChatColor.LIGHT_PURPLE + "160 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XVII: " + ChatColor.LIGHT_PURPLE + " 145 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XVI: " + ChatColor.LIGHT_PURPLE + "  130 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XV: " + ChatColor.LIGHT_PURPLE + "   130 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XIV: " + ChatColor.LIGHT_PURPLE + "  115 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XIII: " + ChatColor.LIGHT_PURPLE + " 100 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XII: " + ChatColor.LIGHT_PURPLE + "  85 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune XI: " + ChatColor.LIGHT_PURPLE + "   70 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune X: " + ChatColor.LIGHT_PURPLE + "    40 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune IX: " + ChatColor.LIGHT_PURPLE + "   20 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune VIII: " + ChatColor.LIGHT_PURPLE + " 10 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune VII: " + ChatColor.LIGHT_PURPLE + "  5 SuperKrysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune VI: " + ChatColor.LIGHT_PURPLE + "   2 SuperKrysztaly");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune V: " + ChatColor.LIGHT_PURPLE + "    80 Krysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune IV: " + ChatColor.LIGHT_PURPLE + "   25 Krysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune III: " + ChatColor.LIGHT_PURPLE + "  8 Krysztalow");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune II: " + ChatColor.LIGHT_PURPLE + "   3 Krysztaly");
                    lore2.add(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY+ "Fortune I: " + ChatColor.LIGHT_PURPLE + "    1 Krysztal");
                    meta.setLore(lore2);
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    break;
                case AMETHYST_SHARD:
                    meta.setDisplayName(Color.fix("&7Wymien &d&lkrysztaly &7na &5&lSuperKrysztaly"));
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    meta.setCustomModelData(10000);
                    break;
                case ECHO_SHARD:
                    meta.setDisplayName(Color.fix("&7Rozmien &5&lSuperKrysztaly &7na &d&lkrysztaly"));
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    meta.setCustomModelData(10000);
                    break;
                case PURPLE_STAINED_GLASS_PANE:
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
        Inventory gui = Bukkit.createInventory(null, 36, "Krysztaly GUI");

        // Dodano wywołanie configureItems()
        configureItems();

        for (int i = 0; i < 36; i++) {
            Material material = i % 9 == 0 ? Material.PURPLE_STAINED_GLASS_PANE : getMaterialForSlot(i);

            ItemStack item = createExampleItem(material);

            if (item != null) {
                gui.setItem(i, item);
            }
        }

        player.openInventory(gui);
    }

    private Material getMaterialForSlot(int slot) {
        return switch (slot) {
            case 10 -> Material.DIAMOND_PICKAXE;
            case 19 -> Material.EXPERIENCE_BOTTLE;
            case 16 -> Material.AMETHYST_SHARD;
            case 25 -> Material.ECHO_SHARD;
            default -> Material.PURPLE_STAINED_GLASS_PANE;
        };
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Krysztaly GUI")) {
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
