package me.kubister11.bytecraftingdisable.listeners;

import me.kubister11.bytecraftingdisable.storage.files.Config;
import me.kubister11.bytecraftingdisable.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.plugin.Plugin;

public class CraftingListener implements Listener {
    public CraftingListener(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onCrafting(CraftItemEvent e) {
        Material type = e.getRecipe().getResult().getType();
        for (String crafting : Config.BLOCKED$MATERIALS) {
            if (type.toString().equals(crafting) || (crafting.equals("ALL") || crafting.equals("*"))) {
                TextUtil.sendMessage(e.getWhoClicked(), Config.MESSAGES_CANNOT$CRAFT);
                e.getWhoClicked().closeInventory();
                e.setCancelled(true);
                return;
            }
        }
    }
}
