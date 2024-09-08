package io.github.jakeslye.jakePlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import io.github.jakeslye.jakePlugin.commands.AFKCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class JakePlugin extends JavaPlugin {

    public static String title = "Boy Kisser Server";

    public void setHeaderAndFooter(String header, String footer, Player player)
    {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(header));
        packet.getChatComponents().write(1, WrappedChatComponent.fromText(footer));

        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);

    }

    @Override
    public void onEnable() {
        this.getCommand("afk").setExecutor(new AFKCommand());

        getServer().getPluginManager().registerEvents(new Listener(), this);

        BukkitScheduler scheduler = Bukkit.getScheduler();

        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if(!getServer().getOnlinePlayers().isEmpty()){
                    for(Player player : getServer().getOnlinePlayers()){
                        setHeaderAndFooter(ChatColor.LIGHT_PURPLE + title, ChatColor.BLUE +  "PING: " + player.getPing() + " TPS: " + Math.round(TPS.getTPS() * 100.0D) / 100.0D, player);
                    }
                }
            }
        }, 10, 40);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
