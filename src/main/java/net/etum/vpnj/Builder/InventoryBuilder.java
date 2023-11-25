package net.etum.vpnj.Builder;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder {

    private final Inventory inventory;


    // Constructeur pour la création d'un inventaire avec un titre et un type
    public InventoryBuilder(String title, InventoryType type) {
        this.inventory = Bukkit.createInventory(null, type, Component.text(title));
    }

    // Constructeur pour la création d'un inventaire avec un titre et une taille spécifiée
    public InventoryBuilder(String title, int row, String doorName) {
        if(title == null){
            title = "titleNotFound";
        }
        if(row == 0) row = 1;
        this.inventory = Bukkit.createInventory(null, row * 9, Component.text(title));
        template(doorName);
    }

    private void template(String doorName){

        int row = getInventory().getSize() / 9;
        // Remplir les bords de l'inventaire avec des vitres grises
        for (int i = 0; i < row * 9; i++) {
            if (i < 9 || i >= (row - 1) * 9 || i % 9 == 0 || i % 9 == 8) {
                ItemStack glassPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                getInventory().setItem(i, glassPane);
            }
        }

        // Placer une porte au milieu de la dernière ligne
        int middleSlot = (row - 1) * 9 + 4;
        ItemBuilder doorItem = new ItemBuilder(Material.SPRUCE_DOOR, doorName);
        getInventory().setItem(middleSlot, doorItem.getStack());
    }



    public Inventory getInventory() {
        return inventory;
    }
}
