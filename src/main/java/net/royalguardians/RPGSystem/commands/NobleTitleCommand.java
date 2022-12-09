package net.royalguardians.RPGSystem.commands;

import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.enums.NobleTitle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class NobleTitleCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("NobleTitle")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("RPGSystem.NobleTitle.set")) {
                    if (args.length == 0) {
                        p.sendMessage("§3/NobleTitle set <Player> <Title>");
                    } else if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("set")) {
                            Player t = Bukkit.getPlayer(args[1]);
                            if (t != null) {
                                NobleTitle nobleTitle = NobleTitle.getNobleTitleByName(args[2]);
                                RPGSystem.players.get(t).setTitle(nobleTitle, t);
                            } else p.sendMessage("§3/NobleTitle set <Player> <Specie>");
                        } else p.sendMessage("§3/NobleTitle set <Player> <Specie>");
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if(args.length == 3) {
            List<String> list = new ArrayList<>();
            for(NobleTitle nobleTitle : NobleTitle.values()) {
                list.add(nobleTitle.name());
            }
            return list;
        }
        return null;
    }
}
