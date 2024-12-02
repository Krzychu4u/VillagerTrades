package org.example.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HasFreeSlots {
    public static boolean hasFreeSlots(Player player, int requiredSlots) {
        Inventory playerInventory = player.getInventory();
        int freeSlots = 0;

        for (int i = 0; i < playerInventory.getSize(); i++) {
            if (isSlotFree(playerInventory, i)) {
                freeSlots++;
            }
        }

        return freeSlots >= requiredSlots;
    }

    private static boolean isSlotFree(Inventory inventory, int slot) {
        ItemStack itemStack = inventory.getItem(slot);
        return itemStack == null && isNotArmorOrOffhand(itemStack);
    }

    private static boolean isNotArmorOrOffhand(ItemStack itemStack) {
        // Tutaj możesz dostosować warunki w zależności od tego, co chcesz traktować jako zbroję
        return !itemStack.getType().name().contains("HELMET")
                && !itemStack.getType().name().contains("CHESTPLATE")
                && !itemStack.getType().name().contains("LEGGINGS")
                && !itemStack.getType().name().contains("BOOTS")
                && !itemStack.getType().name().contains("SHIELD");
    }
}
