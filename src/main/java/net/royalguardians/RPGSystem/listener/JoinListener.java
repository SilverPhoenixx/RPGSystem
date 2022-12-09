package net.royalguardians.RPGSystem.listener;


import net.royalguardians.PhoenixID.PhoenixID;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.specie.SpecieEnum;
import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void join(PlayerJoinEvent e) {
            Player p = e.getPlayer();
            e.setJoinMessage(null);
            RPGSystem.getDatabase().getPlayer(e.getPlayer(), rpgPlayer -> {
                SpecieEnum specieEnum = rpgPlayer.getSpecie().getSpecieEnum();
                Bukkit.broadcastMessage("§a⬆ " + specieEnum.getColorcode() + specieEnum.getName() + " §7» " + specieEnum.getColorcode() + p.getName());
                specieEnum.addPlayer(p.getName());
                rpgPlayer.getScoreboard().sendScoreboard(rpgPlayer, p);
                RPGSystem.getDatabase().setTime(rpgPlayer.getId());
            }, PhoenixID.ids.get(e.getPlayer().getUniqueId()).getOrdinal());
}
}
