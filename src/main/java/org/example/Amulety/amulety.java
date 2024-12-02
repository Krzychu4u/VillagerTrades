package org.example.Amulety;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.example.Utils.Color;
import org.example.item.CrystalManager;

public class amulety implements Listener {


    public void speed(Player player) {
        // Sprawdzenie, czy gracz posiada diamentowy miecz w ekwipunku
        //1

        ItemStack amulet = new ItemStack(Material.SUGAR);
        ItemMeta meta = amulet.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Color.fix("&9&lAmulet Szybkosci III"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        amulet.setItemMeta(meta);
        if (player.getInventory().contains(amulet)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
        }else {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
    }
    public void trade(Player player) {
        // Sprawdzenie, czy gracz posiada diamentowy miecz w ekwipunku
        ItemStack wymiana = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta meta = wymiana.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Color.fix("&9&lAutoKrysztal"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        wymiana.setItemMeta(meta);
        if (player.getInventory().contains(wymiana)) {
            if(player.getLevel() >= 20){
                CrystalManager crystalManager = new CrystalManager(1);
                crystalManager.addCrystalsToInventory(player.getInventory());
                int a = player.getLevel();
                player.setLevel(a - 20);
            }
        }
    }
    public static void sellall(Player player) {
        ItemStack sell = new ItemStack(Material.END_CRYSTAL);

        ItemMeta meta = sell.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Color.fix("&9&lAutoSell"));

        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sell.setItemMeta(meta);
        if (player.getInventory().contains(sell)) {
            player.performCommand("sa autosell");
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (event.getClickedInventory() != null && event.getClickedInventory().equals(player.getInventory())) {
                speed(player);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        speed(player);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType() == Material.DIAMOND_PICKAXE) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                player.performCommand("enchanter");
            }
        }

    }
    public int licznik = 0;
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        speed(player);
        trade(player);
        licznik++;
        if(licznik >= 5)
            sellall(player);

    }
}
