package it.filyx0.zeuscommandfixer.Event;

import it.filyx0.zeuscommandfixer.ZCF;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;

public class onCommandEvent implements Listener {
    private Plugin plugin = ZCF.getPlugin(ZCF.class);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandEvent(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        //HashMap<Player, Integer> vls = new HashMap<>();

        FileConfiguration config = plugin.getConfig();

        String prefix = config.getString("prefix").replace("&", "§");

        if (config.getBoolean("commands.custom.block") == true) {
            for (int i = 0; i < config.getStringList("commands.custom.list").size(); i++) {
                if (e.getMessage().equalsIgnoreCase(config.getStringList("commands.custom.list").get(i))) {
                    if (p.hasPermission("zeus.bypass")) {
                        p.sendMessage(config.getString("messages.bypass").replace("&", "§").replace("%prefix%", prefix).replace("%command%", e.getMessage()));

                        for (Player staff : Bukkit.getOnlinePlayers()) {
                            if(staff.hasPermission("zeus.alerts")) {
                                staff.sendMessage(config.getString("bypass_format").replace("&", "§").replace("%prefix%", prefix).replace("%command%", e.getMessage()).replace("%player%", p.getName()));
                            }
                        }

                        System.out.println(config.getString("console_bypass"));
                    } else {
                        e.setCancelled(true);

                        //int vl = vls.get(p);
                        //vls.put(p, vl + 1);

                        p.sendMessage(config.getString("messages.block_cmd").replace("&", "§").replace("%prefix%", prefix));

                        for (Player staff : Bukkit.getOnlinePlayers()) {
                            if(staff.hasPermission("zeus.alerts")) {
                                staff.sendMessage(config.getString("flag_format").replace("&", "§").replace("%prefix%", prefix).replace("%command%", e.getMessage()).replace("%player%", p.getName())/*.replace("%vl%", String.valueOf(vl))*/);
                            }
                        }

                        System.out.println(config.getString("console_flag"));
                    }
                }
            }
        }
    }
}
