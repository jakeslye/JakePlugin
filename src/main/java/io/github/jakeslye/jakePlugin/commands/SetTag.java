package io.github.jakeslye.jakePlugin.commands;

import io.github.jakeslye.jakePlugin.JakePlugin;
import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTag implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.isOp()) {
            Player p = JakePlugin.instance.getServer().getPlayer(strings[0]);

            if (p != null) {
                ServerPlayer sp = ServerPlayer.getPlayer(p.getUniqueId());

                sp.setTag(strings[1]);
            }
        }
        return false;
    }
}
