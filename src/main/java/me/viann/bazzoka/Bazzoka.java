package me.viann.bazzoka;

import me.viann.bazzoka.commands.BazzokaCommand;
import me.viann.bazzoka.listeners.ArrowListener;
import me.viann.bazzoka.listeners.BowFireRateListener;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bazzoka extends JavaPlugin {

    private static final String BOW_NAME = "§cBazzoka";

    @Override
    public void onEnable() {
        this.getLogger().info("@    Bazzoka has been enabled!");

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        this.getLogger().info("@    Bazzoka has been enabled!");
    }

    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new BowFireRateListener(this), this);
        getServer().getPluginManager().registerEvents(new ArrowListener(), this);
    }

    public void registerCommands(){
        this.getCommand("bazzoka").setExecutor(new BazzokaCommand());
    }

    public static String getBowName() {
        return BOW_NAME;
    }

    public static ItemStack createExplosiveBow() {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(BOW_NAME);
            bow.setItemMeta(meta);
        }
        return bow;
    }

}

