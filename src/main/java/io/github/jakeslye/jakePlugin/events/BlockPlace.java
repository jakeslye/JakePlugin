package io.github.jakeslye.jakePlugin.events;

import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements org.bukkit.event.Listener{
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (ServerPlayer.getPlayer(event.getPlayer().getUniqueId()).isAFK()) {
            event.setCancelled(true);
        }
    }
}
