package io.github.jakeslye.jakePlugin;

import io.github.jakeslye.jakePlugin.commands.AFKCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("[Server Updates] You can now do /afk to become unable to take damage or move");
        if(event.getPlayer().isOp()){
            event.getPlayer().setPlayerListName("[ADMIN] " + event.getPlayer().getDisplayName());
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(AFKCommand.afkPlayers.contains(player.getName())) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player player) {
            if (AFKCommand.afkPlayers.contains(player.getName())) {
                event.setCancelled(true);
            }
        }

        if (event instanceof EntityDamageByEntityEvent entityEvent) {
            Entity damager = entityEvent.getDamager();
            if(damager instanceof Player player) {
                if (AFKCommand.afkPlayers.contains(player.getName())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (AFKCommand.afkPlayers.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (AFKCommand.afkPlayers.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(event.getPlayer().isOp()) {
            event.setFormat("[ADMIN] " + event.getFormat());
        }
    }
}
