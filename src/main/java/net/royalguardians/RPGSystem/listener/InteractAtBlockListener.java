package net.royalguardians.RPGSystem.listener;

import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.jobs.jobsEnums.MaterialEnum;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractAtBlockListener implements Listener {

    @EventHandler
    public void onInteractAtBlock(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(RPGSystem.players.containsKey(p)) {
            RPGPlayer rpgPlayer = RPGSystem.players.get(p);
            MaterialEnum m = MaterialEnum.getInteractBlock(e.getClickedBlock());
            if (m == null) return;
            if(e.getClickedBlock().getBlockData() instanceof Ageable) {
                Ageable ageable = (Ageable) e.getClickedBlock().getBlockData();
                if (ageable.getAge() == ageable.getMaximumAge()) {
                    rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), p);
                }
            }
        }
    }
}
