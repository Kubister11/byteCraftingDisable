package me.kubister11.bytecraftingdisable.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.kubister11.bytecraftingdisable.storage.files.Config;
import me.kubister11.bytecraftingdisable.utils.ItemUtils;
import me.kubister11.bytecraftingdisable.utils.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddGui implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fill(ClickableItem.empty(ItemUtils.createFilter(Material.GRAY_STAINED_GLASS_PANE, " "), false));
        contents.set(0, 1, ClickableItem.empty(ItemUtils.createFilter(Material.ORANGE_STAINED_GLASS_PANE, " "), false));
        contents.set(0, 2, ClickableItem.empty(new ItemStack(Material.AIR), true));
        contents.set(0, 3, ClickableItem.empty(ItemUtils.createFilter(Material.ORANGE_STAINED_GLASS_PANE, " "), false));


        contents.set(0, 7, ClickableItem.of(ItemUtils.createIs(Material.LIME_DYE, Config.MESSAGES_ADD$BUTTON, true), false, e -> {
            ItemStack item = e.getInventory().getItem(2);
            if (item == null) return;
            Material type = item.getType();

            for (String crafting : Config.BLOCKED$MATERIALS) {
                if (type.toString().equals(crafting)) {
                    return;
                }
            }

            Config.BLOCKED$MATERIALS.add(type.toString());
            Config.getConfigFile().save();
            TextUtil.sendMessage(player, Config.MESSAGES_SAVED);
            player.closeInventory();
        }));
        contents.set(0, 8, ClickableItem.of(ItemUtils.createIs(Material.RED_DYE, Config.MESSAGES_EXIT$BUTTON, true), false, e -> {
            player.closeInventory();
        }));
    }


    @Override
    public void update(Player player, InventoryContents contents) {

    }

}
