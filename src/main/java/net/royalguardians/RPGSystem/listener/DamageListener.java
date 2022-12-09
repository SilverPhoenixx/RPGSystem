package net.royalguardians.RPGSystem.listener;

import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.player.RPGPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DamageListener implements Listener {


    @EventHandler
    public void OnGetDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            double damage = e.getDamage();
            e.setDamage(0);
            if (RPGSystem.players.containsKey(p)) {
                RPGPlayer rpgPlayer = RPGSystem.players.get(p);
                damage -= rpgPlayer.getStats().getDefense();
                switch (e.getCause()) {
                    case PROJECTILE:
                        if(rpgPlayer.getSpecie().getSpecieEnum().isProjectileResistance()) damage /= 2; break;
                    case FIRE:
                    case LAVA:
                    case FIRE_TICK:
                    case HOT_FLOOR:
                        if(rpgPlayer.getSpecie().getSpecieEnum().isFireResistance()) damage /= 2; break;
                    case FALL:
                        if(rpgPlayer.getSpecie().getSpecieEnum().isFallResistance()) damage /= 2; break;
                    case BLOCK_EXPLOSION:
                    case ENTITY_EXPLOSION:
                        if(rpgPlayer.getSpecie().getSpecieEnum().isExplosionResistance()) damage /= 2; break;
                    default: break;
                }
                rpgPlayer.onDamage(damage, p);
            }
        }
    }
}
