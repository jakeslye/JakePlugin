package io.github.jakeslye.jakePlugin;

import io.github.jakeslye.jakePlugin.commands.AFKCommand;
import org.bukkit.ChatColor;
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
import org.bukkit.event.player.PlayerQuitEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(ChatColor.AQUA + "[Server Updates]" + ChatColor.WHITE +  " Ciaran is a bitch");
        new ServerPlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        ServerPlayer.removePlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        ServerPlayer SP = ServerPlayer.getPlayer(event.getPlayer().getUniqueId());
        SP.updateMove();

        if(SP.isAFK()) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player player) {
            if(ServerPlayer.getPlayer(player.getUniqueId()).isAFK()){
                event.setCancelled(true);
            }
        }

        if (event instanceof EntityDamageByEntityEvent entityEvent) {
            Entity damager = entityEvent.getDamager();
            if(damager instanceof Player player) {
                if (ServerPlayer.getPlayer(player.getUniqueId()).isAFK()) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (ServerPlayer.getPlayer(event.getPlayer().getUniqueId()).isAFK()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (ServerPlayer.getPlayer(event.getPlayer().getUniqueId()).isAFK()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String tag = ServerPlayer.getPlayer(event.getPlayer().getUniqueId()).getTag();
        if(tag != null) {
            event.setFormat(ChatColor.RED + "[" + tag + "]" + ChatColor.WHITE + " " + event.getFormat());
        }
    }
}
