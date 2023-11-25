package net.etum.vpnj.Builder;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private final ItemStack stack;

    public ItemBuilder(Material material, String displayName) {
        if(material == null){
            material = Material.STONE;
        }
        stack = new ItemStack(material, 1);
        ItemMeta meta = stack.getItemMeta();
        if(displayName == null){
            displayName = "NotNameFound";
        }
        meta.displayName(Component.text(displayName));
        stack.setItemMeta(meta);
    }

    public ItemBuilder(Material material, String displayName, int amount) {
        if(material == null){
            material = Material.STONE;
        }
        stack = new ItemStack(material, amount);
        ItemMeta meta = stack.getItemMeta();
        if(displayName == null){
            displayName = "NotNameFound";
        }
        meta.displayName(Component.text(displayName));
        stack.setItemMeta(meta);
    }

    public void displayname(String displayName) {
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(Component.text(displayName));
        stack.setItemMeta(meta);
    }

    public void addEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(enchantment, level, ignoreLevelRestriction);
        stack.setItemMeta(meta);
    }

    public void removeEnchant(Enchantment enchantment){
        ItemMeta meta = stack.getItemMeta();
        meta.removeEnchant(enchantment);
        stack.setItemMeta(meta);
    }

    public void addFlag(ItemFlag itemFlag){
        ItemMeta meta = stack.getItemMeta();
        meta.addItemFlags(itemFlag);
        stack.setItemMeta(meta);
    }

    public void removeFlag(ItemFlag itemFlag){
        ItemMeta meta = stack.getItemMeta();
        meta.removeItemFlags(itemFlag);
        stack.setItemMeta(meta);
    }

    public void addLore(String loreListToAdd) {
        ItemMeta meta = stack.getItemMeta();
        List<Component> currentLore = meta.hasLore() ? meta.lore() : new ArrayList<>();

        // Vérifie si le lore actuel existe
        if (currentLore != null) {
            // Filtrer les éléments de lore qui ne sont pas déjà présents,
            // sauf si l'élément est une ligne vide
            currentLore.add(Component.text(loreListToAdd));

            // Met à jour le lore de l'ItemStack
            meta.lore(currentLore);
            stack.setItemMeta(meta);
        }
    }



    public ItemStack getStack() {
        return stack;
    }
}
