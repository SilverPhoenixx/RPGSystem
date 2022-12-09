package net.royalguardians.RPGSystem.listener;

import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.player.RPGPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EntityRegainHealthListener implements Listener {

    @EventHandler
    public void onRegainHealth(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (RPGSystem.players.containsKey(p)) {
                RPGPlayer rpgPlayer = RPGSystem.players.get(p);
                switch (event.getRegainReason()) {
                    case SATIATED:
                        event.setAmount(rpgPlayer.getSpecie().getSpecieEnum().getRegenerationAmount()); break;
                }
            }
        }
    }
}
