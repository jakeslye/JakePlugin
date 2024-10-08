package io.github.jakeslye.jakePlugin;

import org.bukkit.ChatColor;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

public class ServerPlayer {
    private Instant lastMove = null;
    private UUID uid = null;
    private String name = null;
    private Boolean AFK = false;
    private String tag = null;
    private ChatColor tagColor = ChatColor.WHITE;

    private static final HashMap<String, ServerPlayer> Players = new HashMap<String, ServerPlayer>();

    public ServerPlayer(UUID uid, String name) {
        this.uid = uid;
        this.name = name;
        this.lastMove = Instant.now();

        Players.put(uid.toString(), this);
    }

    public void setAFK(Boolean AFK) {
        this.AFK = AFK;
    }

    public Boolean isAFK() {
        return AFK;
    }

    public void updateMove(){
        lastMove = Instant.now();
    }

    public long getLastMove() {
        Duration timeElapsed = Duration.between(lastMove, Instant.now());
        return timeElapsed.getSeconds();
    }

    public void setTagColor(ChatColor color) {
        this.tagColor = color;
    }

    public ChatColor getTagColor() {
        return tagColor;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public UUID getUID(){
        return uid;
    }

    public String getName(){
        return name;
    }

    public static ServerPlayer getPlayer(UUID uuid){
        return Players.get(uuid.toString());
    }

    public static void removePlayer(UUID uuid){
        Players.remove(uuid.toString());
    }
}
