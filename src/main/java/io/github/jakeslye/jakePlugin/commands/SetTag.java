package io.github.jakeslye.jakePlugin.commands;

import io.github.jakeslye.jakePlugin.JakePlugin;
import io.github.jakeslye.jakePlugin.ServerPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SetTag implements CommandExecutor {


    private HashMap<String, ChatColor> colorHashMap = new HashMap<String, ChatColor>();

    public SetTag() {
        colorHashMap.put("red", ChatColor.RED);
        colorHashMap.put("green", ChatColor.GREEN);
        colorHashMap.put("pink", ChatColor.LIGHT_PURPLE);
        colorHashMap.put("blue", ChatColor.BLUE);
        colorHashMap.put("gold", ChatColor.GOLD);
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.isOp()) {
            Player p = JakePlugin.instance.getServer().getPlayer(strings[0]);

            if (p != null) {
                ServerPlayer sp = ServerPlayer.getPlayer(p.getUniqueId());

                if(strings.length == 1) {
                    commandSender.sendMessage(ChatColor.GOLD + "Name is reset :)");
                    sp.setTag(null);
                    JakePlugin.instance.getServer().getPlayer(sp.getName()).setPlayerListName(sp.getName());
                }else{
                    sp.setTag(strings[1]);

                    if(strings.length == 3){
                        ChatColor color = colorHashMap.get(strings[2]);

                        if(color != null){
                            sp.setTagColor(color);
                        }else{
                            commandSender.sendMessage(ChatColor.RED + "Invalid color :(");
                        }
                    }

                    JakePlugin.instance.getServer().getPlayer(sp.getName()).setPlayerListName(sp.getTagColor() +  "[" + sp.getTag() + "] " + ChatColor.WHITE + sp.getName());

                    commandSender.sendMessage(ChatColor.GOLD + "Name is updated :D");
                }
            }
        }
        return false;
    }
}
