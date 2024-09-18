package io.github.jakeslye.jakePlugin.events;

import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements org.bukkit.event.Listener{
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        ServerPlayer SP = ServerPlayer.getPlayer(event.getPlayer().getUniqueId());
        SP.updateMove();

        if(SP.isAFK()) {
            event.setCancelled(true);
        }
    }
}
