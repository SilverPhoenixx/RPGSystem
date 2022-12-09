package net.royalguardians.RPGSystem.listener;

import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.jobs.jobsEnums.ActivityEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantmentListener implements Listener {

@EventHandler
    public void onEnchant(EnchantItemEvent event) {
        if (RPGSystem.players.containsKey(event.getEnchanter())) {
            RPGPlayer rpgPlayer = RPGSystem.players.get(event.getEnchanter());
            ActivityEnum m = ActivityEnum.ENCHANTING;
            rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), event.getEnchanter());
        }
    }
}
