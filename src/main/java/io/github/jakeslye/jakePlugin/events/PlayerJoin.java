package io.github.jakeslye.jakePlugin.events;

import io.github.jakeslye.jakePlugin.JakePlugin;
import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements org.bukkit.event.Listener{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new ServerPlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName());
        Bukkit.getScheduler().runTaskLater(JakePlugin.instance,  () -> {
            event.getPlayer().sendMessage("Server map: " + ChatColor.AQUA + "" + ChatColor.ITALIC + "" + ChatColor.UNDERLINE + "http://freaky.proserver.io:8123/");
        },  40);
    }
}
