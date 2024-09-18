package io.github.jakeslye.jakePlugin.events;

import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements org.bukkit.event.Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ServerPlayer sp = ServerPlayer.getPlayer(event.getPlayer().getUniqueId());
        String tag = sp.getTag();
        if(tag != null) {
            event.setFormat(sp.getTagColor() + "[" + tag + "]" + ChatColor.WHITE + " " + event.getFormat());
        }
    }
}