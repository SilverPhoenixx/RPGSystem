package net.royalguardians.RPGSystem.commands;

import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.specie.SpecieEnum;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpecieCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Specie")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("RPGSystem.Specie.set")) {
                    if (args.length == 0) {
                        p.sendMessage("§3/Specie set <Player> <Specie>");
                    } else if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("set")) {
                            Player t = Bukkit.getPlayer(args[1]);
                            if (t != null) {
                                SpecieEnum specieEnum = SpecieEnum.getSpecieByName(args[2]);
                                RPGSystem.players.get(t).setSpecie(RPGSystem.species.get(specieEnum), t);
                            } else p.sendMessage("§3/Specie set <Player> <Specie>");
                        } else p.sendMessage("§3/Specie set <Player> <Specie>");
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
            for(SpecieEnum specieEnum : SpecieEnum.values()) {
                list.add(specieEnum.name());
            }
            return list;
        }
        return null;
    }
}
