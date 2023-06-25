package me.kubister11.bytecraftingdisable;

import fr.minuskube.inv.InventoryManager;
import lombok.Getter;
import me.kubister11.bytecraftingdisable.commands.MainCommand;
import me.kubister11.bytecraftingdisable.listeners.CraftingListener;
import me.kubister11.bytecraftingdisable.storage.files.Config;
import me.kubister11.bytecraftingdisable.utils.TextUtil;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class ByteCraftingDisable extends JavaPlugin {

    @Getter private static InventoryManager invManager;

    @Override
    public void onEnable() {
        long startLoad = System.currentTimeMillis();

        new Config(this, Config.class, "config.yml");
        new CraftingListener(this);
        new MainCommand();

        invManager = new InventoryManager(this);
        invManager.init();

        new Metrics(this, 18841);

        TextUtil.sendToConsole("&ebyteCraftingDisable loaded! &7(took: " + (System.currentTimeMillis() - startLoad) + "ms)");
    }
}
