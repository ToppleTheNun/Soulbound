package com.me.tft_02.soulbound;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
    Soulbound plugin;

    public ItemUtils(Soulbound instance) {
        plugin = instance;
    }

    public static ItemStack soulbindItem(Player player, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasLore() && isBindOnPickup(itemStack)) {
            itemMeta.getLore().remove(ChatColor.DARK_RED + "Bind on pickup");
        }

        List<String> itemLore = new ArrayList<String>();
        itemLore.add(ChatColor.GOLD + "Soulbound");
        itemLore.add(player.getName());
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack bopItem(Player player, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> itemLore = new ArrayList<String>();
        itemLore.add(ChatColor.DARK_RED + "Bind on pickup");
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack unbindItem(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasLore() && isSoulbound(itemStack)) {
            itemMeta.getLore().clear();
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static boolean isSoulbound(ItemStack itemStack) {
        if (!itemStack.hasItemMeta()) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasLore()) {
            List<String> itemLore = itemMeta.getLore();
            if (itemLore.contains(ChatColor.GOLD + "Soulbound")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBindOnPickup(ItemStack itemStack) {
        if (!itemStack.hasItemMeta()) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasLore()) {
            List<String> itemLore = itemMeta.getLore();
            if (itemLore.contains(ChatColor.DARK_RED + "Bind on pickup")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBindedPlayer(Player player, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> itemLore = itemMeta.getLore();
        if (itemLore.contains(player.getName())) {
            return true;
        }
        return false;
    }
}
