package it.filyx0.zeuscommandfixer;

import it.filyx0.zeuscommandfixer.Event.onCommandEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZCF extends JavaPlugin implements Listener {
    //ZeusCommandFixer
    public static ZCF instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(this, this);

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new onCommandEvent(), this);

        System.out.println("[ZeusCommandFixer] Plugin enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("[ZeusCommandFixer] Plugin disabled!");
    }
}
