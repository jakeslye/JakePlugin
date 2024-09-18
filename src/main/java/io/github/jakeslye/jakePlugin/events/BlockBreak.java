package io.github.jakeslye.jakePlugin.events;

import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements org.bukkit.event.Listener{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (ServerPlayer.getPlayer(event.getPlayer().getUniqueId()).isAFK()) {
            event.setCancelled(true);
        }
    }
}
