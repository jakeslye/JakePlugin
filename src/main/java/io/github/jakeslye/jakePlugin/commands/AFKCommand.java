package io.github.jakeslye.jakePlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AFKCommand implements CommandExecutor {
    public static ArrayList<String> afkPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            if(afkPlayers.contains(player.getName())) {
                afkPlayers.remove(player.getName());
                player.sendMessage("No longer afk!");
            }else{
                afkPlayers.add(player.getName());
                player.sendMessage("Now afk!");
            }
        }
        return false;
    }
}
