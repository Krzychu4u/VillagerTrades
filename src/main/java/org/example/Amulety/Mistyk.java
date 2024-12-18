package org.example.Amulety;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Objects;

import org.bukkit.inventory.meta.ItemMeta;
import org.example.Main;
import org.example.Utils.Color;

public class Mistyk implements Listener {



    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)){
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                Material toolType = event.getItem() != null ? event.getItem().getType() : null;
                if (isInventoryFull(event.getPlayer())) {
                    event.setCancelled(true); // Anuluj akcję kopania
                    //amulety.sellall((Player) event);
                    sendTitle(event.getPlayer(), Color.fix("&6&lMasz pełne eq!"), Color.fix("&eZainwestuj w autosell'a biedaku"));
                }
            }
        }
    }
    private boolean isInventoryFull(org.bukkit.entity.Player player) {
        return player.getInventory().firstEmpty() == -1;
    }
    private void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle, 10, 30, 10); // (title, subtitle, fadeIn, stay, fadeOut)
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.getType() == Material.DIAMOND_PICKAXE && Objects.requireNonNull(heldItem.getItemMeta()).hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)) {


            WorldGuardPlugin worldGuard = Main.worldGuard;

            if(player.getWorld().getName().equals("world")) {
                if (heldItem.getType() == Material.DIAMOND_PICKAXE && Objects.requireNonNull(heldItem.getItemMeta()).hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)) {
                    Block brokenBlock = event.getBlock();
                    World world = brokenBlock.getWorld();


                    // Sprawdź czy blok jest chroniony przez WorldGuard
                    if (worldGuard != null) {
                        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));

                        if (regionManager != null) {
                            RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
                            //boolean canBreak = query.testState(BukkitAdapter.adapt(brokenBlock.getLocation()), worldGuard.wrapPlayer(player), Flags.BLOCK_BREAK);




                            for (int x = -1; x <= 1; x++) {
                                for (int y = -1; y <= 1; y++) {
                                    for (int z = -1; z <= 1; z++) {
                                        Block blockToBreak = brokenBlock.getWorld().getBlockAt(
                                                brokenBlock.getLocation().getBlockX() + x,
                                                brokenBlock.getLocation().getBlockY() + y,
                                                brokenBlock.getLocation().getBlockZ() + z);
                                        if (blockToBreak.getType() != Material.BEDROCK) {
                                            boolean canBreak = query.testState(BukkitAdapter.adapt(blockToBreak.getLocation()), worldGuard.wrapPlayer(player), Flags.BLOCK_BREAK);
                                            //player.sendMessage(String.valueOf(canBreak));

                                            if (canBreak) {
                                                if (blockToBreak.getType() != Material.AIR) {
                                                    player.incrementStatistic(Statistic.MINE_BLOCK, blockToBreak.getType());


                                                    ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                                                    assert meta != null;
                                                    if (meta.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                                        float level = meta.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);

                                                        for (int i = 0; i < level; i++) {
                                                            player.getInventory().addItem(new ItemStack(blockToBreak.getType()));
                                                        }
                                                    } else {
                                                        player.getInventory().addItem(new ItemStack(blockToBreak.getType()));
                                                    }

                                                    if (blockToBreak.getType() == Material.REINFORCED_DEEPSLATE) {
                                                        player.getInventory().addItem(new ItemStack(blockToBreak.getType()));
                                                    }
                                                    //player.sendMessage(String.valueOf(blockToBreak.getType()));
                                                    blockToBreak.setType(Material.AIR);

                                                    event.getPlayer().giveExp(10);

                                                }
                                            } else {
                                                event.setCancelled(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
