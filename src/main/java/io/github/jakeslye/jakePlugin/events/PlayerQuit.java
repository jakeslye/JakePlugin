package io.github.jakeslye.jakePlugin.events;

import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements org.bukkit.event.Listener{

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        ServerPlayer.removePlayer(event.getPlayer().getUniqueId());
    }
}
