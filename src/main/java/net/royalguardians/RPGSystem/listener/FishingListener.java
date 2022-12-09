package net.royalguardians.RPGSystem.listener;

import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.jobs.jobsEnums.ActivityEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishingListener implements Listener {

    @EventHandler
    public void onAngle(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if (RPGSystem.players.containsKey(event.getPlayer())) {
                RPGPlayer rpgPlayer = RPGSystem.players.get(event.getPlayer());
                ActivityEnum m = ActivityEnum.FISHING;
                rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), event.getPlayer());
            }
        }
    }
}
