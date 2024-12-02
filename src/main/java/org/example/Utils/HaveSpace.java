package org.example.Utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HaveSpace {
    public void haveSpaceFinal(Player player, Material material, int ilosc) {
        Inventory inventory = player.getInventory();

        // Sprawdź, czy gracz ma wystarczającą ilość miejsca w ekwipunku
        if (haveSpace(player, material, ilosc)) {
            ItemStack item = new ItemStack(material, ilosc);

            // Dodaj item do ekwipunku gracza
            inventory.addItem(item);

        } else {
            player.sendMessage("Nie masz wystarczająco dużo miejsca w ekwipunku!");
            // Anuluj akcję, jeśli gracz nie ma miejsca
        }
    }
    private boolean haveSpace(Player player, Material material, int ilosc) {
        Inventory inventory = player.getInventory();
        int wolneSloty = 0;

        // Zlicz wolne sloty w ekwipunku
        for (ItemStack item : inventory.getContents()) {
            if (item == null) {
                wolneSloty++;
            } else if (item.getType() == material) {
                // Znaleziono istniejący stak itemu, sprawdź czy można go rozszerzyć
                int miejsceNaStosie = material.getMaxStackSize() - item.getAmount();
                wolneSloty += miejsceNaStosie;
            }
        }

        // Sprawdź, czy jest wystarczająco dużo wolnych slotów
        return wolneSloty >= ilosc;
    }

    // Przykład użycia:
    public void jakasMetoda(Player player) {
        Material materialKamien = Material.STONE;
        int oczekiwanaIlosc = 100;

        haveSpaceFinal(player, materialKamien, oczekiwanaIlosc);
    }
}
