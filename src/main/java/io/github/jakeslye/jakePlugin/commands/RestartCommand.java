package io.github.jakeslye.jakePlugin.commands;

import io.github.jakeslye.jakePlugin.JakePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;


public class RestartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player && commandSender.isOp()) {
            BukkitScheduler scheduler = Bukkit.getScheduler();

            scheduler.scheduleSyncRepeatingTask(JakePlugin.instance, new Runnable() {
                int time = 270;

                @Override
                public void run() {
                    time -= 30;
                    JakePlugin.instance.getServer().broadcastMessage(ChatColor.GOLD + "Server is restarting in " + time + " seconds.");
                    if (time == 0) {
                        JakePlugin.instance.getServer().spigot().restart();
                    }
                }
            }, 0, 600);
        }
        return false;
    }
}
