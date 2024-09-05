package io.github.jakeslye.jakePlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import io.github.jakeslye.jakePlugin.commands.AFKCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

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

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(
                this,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.CHAT) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                String message = packet.getStrings().read(0);

                if (message.contains("shit") || message.contains("damn")) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("Bad word!");
                }
            }
        });

        BukkitScheduler scheduler = Bukkit.getScheduler();

        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if(!getServer().getOnlinePlayers().isEmpty()){
                    for(Player player : getServer().getOnlinePlayers()){
                        setHeaderAndFooter(title, "PING: " + player.getPing(), player);
                    }
                }
            }
        }, 10, 40);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
