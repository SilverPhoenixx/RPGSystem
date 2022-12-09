package net.royalguardians.RPGSystem.commands;

import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.equipment.Weapons;
import net.royalguardians.RPGSystem.specie.SpecieEnum;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WeaponCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("RPGSystem.Weapon.get")) {
                    if (args.length == 0) {
                        p.sendMessage("§3/Weapon <Player> <Weapon>");
                    } else if (args.length == 2) {
                            Player t = Bukkit.getPlayer(args[0]);
                            if (t != null) {
                                Weapons weapon = Weapons.getWeapon(args[1]);
                                t.getInventory().addItem(weapon.getItem());
                            } else p.sendMessage("§3/Weapon <Player> <Weapon>");
                    } else p.sendMessage("§3/Weapon <Player> <Weapon>");
                }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if(args.length == 2) {
            List<String> list = new ArrayList<>();
            for(Weapons weapon : Weapons.values()) {
                list.add(weapon.name());
            }
            return list;
        }
        return null;
    }
}
