package me.kubister11.bytecraftingdisable.commands;

import fr.minuskube.inv.SmartInventory;
import me.kubister11.bytecraftingdisable.gui.AddGui;
import me.kubister11.bytecraftingdisable.storage.files.Config;
import me.kubister11.bytecraftingdisable.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    public MainCommand() {
        Bukkit.getPluginCommand("bytecrafting").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("bytecraftingdisable.add")) return true;
        if (args.length == 0) {
            if (!(sender instanceof Player p)) return true;
            SmartInventory.builder()
                    .provider(new AddGui())
                    .size(1, 9)
                    .title(TextUtil.fix(Config.GUI_TITLE))
                    .build().open(p);
        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            Config.getConfigFile().reload();
            TextUtil.sendMessage(sender, Config.MESSAGES_RELOADED);
        }
        return true;
    }

    private static final List<String> arguments = List.of("reload");
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            for (String arg : arguments) {
                if (arg.startsWith(args[0].toLowerCase())) {
                    results.add(arg);
                }
            }
            return results;
        }
        return results;
    }
}
