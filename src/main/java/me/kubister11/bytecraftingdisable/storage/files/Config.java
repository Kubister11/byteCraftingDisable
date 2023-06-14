package me.kubister11.bytecraftingdisable.storage.files;

import lombok.Getter;
import me.kubister11.bytecraftingdisable.storage.api.ConfigFile;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Config extends ConfigFile {
    @Getter private static ConfigFile configFile;
    public Config(Plugin plugin, Class configClass, String fileName) {
        super(plugin, configClass, fileName);
        configFile = this;
    }

    public static String MESSAGES_CANNOT$CRAFT = "&cNie możesz tego zrobić!";
    public static String MESSAGES_RELOADED = "&aPrzeładowano plugin!";
    public static String MESSAGES_SAVED = "&aZapisano item!";
    public static String MESSAGES_ADD$BUTTON = "&aDodaj";
    public static String MESSAGES_EXIT$BUTTON = "&cWyjdz";
    public static String GUI_TITLE = "&0Dodaj item:";
    public static List<String> BLOCKED$MATERIALS = new ArrayList<>();
}
