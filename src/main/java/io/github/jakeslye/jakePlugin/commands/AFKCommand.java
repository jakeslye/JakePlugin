package io.github.jakeslye.jakePlugin.commands;

import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AFKCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            ServerPlayer SP = ServerPlayer.getPlayer(player.getUniqueId());
            if(SP.isAFK()) {
                SP.setAFK(false);
                player.sendMessage("No longer afk!");
            }else{
                if(SP.getLastMove() < 10){
                    player.sendMessage("You need to not move for ten seconds to go afk.");
                }else {
                    SP.setAFK(true);
                    player.sendMessage("Now afk!");
                }
            }
        }
        return false;
    }
}
