package io.github.jakeslye.jakePlugin.events;

import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements org.bukkit.event.Listener {
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
}
