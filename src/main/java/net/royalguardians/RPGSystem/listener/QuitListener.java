package net.royalguardians.RPGSystem.listener;

import net.royalguardians.RPGSystem.RPGSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(RPGSystem.players.containsKey(p)) {
            e.setQuitMessage(null);
            RPGSystem.players.get(p).save();
        }
    }
}
