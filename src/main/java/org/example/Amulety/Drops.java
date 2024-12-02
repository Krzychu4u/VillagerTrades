package org.example.Amulety;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.example.Utils.Color;
import org.example.item.CrystalManager;

import java.util.Random;

import static org.example.Main.worldGuard;

public class Drops implements Listener {



    private String[] wiadomosci = {
            //przerobic do configu i wczytywac z pętli
        "&aGratulacje wydropiles &d&lKrysztal!",
        "&aI cyk kolejny &ado kolekcji!",
        "&aGratulacje! &7Wydropiles talon na &d&lKrysztal &7i balon!"
    };

    private Random random = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block brokenBlock = event.getBlock();
        Material blockType = brokenBlock.getType();
        World world = brokenBlock.getWorld();
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.SURVIVAL) {

            if (worldGuard != null) {
                RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));

                if (regionManager != null) {
                    RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
                    boolean canBreak = query.testState(BukkitAdapter.adapt(brokenBlock.getLocation()), worldGuard.wrapPlayer(player), Flags.BLOCK_BREAK);
                    if (!canBreak) {
                        event.setCancelled(true);
                    } else if (player.hasPermission("mistyk.legenda")) {
                        if (legenda()) {
                            wiadomosc(player);
                            CrystalManager crystalManager = new CrystalManager(1);
                            crystalManager.addCrystalsToInventory(player.getInventory());
                        }
                    } else if (player.hasPermission("mistyk.wladca")) {
                        if (wladca()) {
                            wiadomosc(player);
                            CrystalManager crystalManager = new CrystalManager(1);
                            crystalManager.addCrystalsToInventory(player.getInventory());
                        }
                    }
                }
            }
        }
    }





    private void wiadomosc( Player p) {
        String random = randommsg();
        p.sendMessage(Color.fix(random));
    }

    private String randommsg() {
        return wiadomosci[random.nextInt(wiadomosci.length)];
    }

    private boolean legenda(){
        return random.nextDouble() <= 0.002;
    }
    private boolean wladca(){
        return random.nextDouble() <= 0.005;
    }
}

