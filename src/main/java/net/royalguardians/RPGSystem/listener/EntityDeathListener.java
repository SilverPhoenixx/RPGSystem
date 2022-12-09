package net.royalguardians.RPGSystem.listener;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.jobs.jobsEnums.EntityEnum;
import net.royalguardians.RPGSystem.jobs.jobsEnums.JobEnum;
import net.royalguardians.RPGSystem.jobs.jobsEnums.MaterialEnum;
import net.royalguardians.RPGSystem.player.RPGPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void death(EntityDeathEvent e) {
        if(e.getEntity().getKiller() == null) return;
        Player p = e.getEntity().getKiller();
        if(RPGSystem.players.containsKey(p)) {
            RPGPlayer rpgPlayer = RPGSystem.players.get(p);
            EntityEnum entityEnum = EntityEnum.getEntityEnum(e.getEntityType());
            if (entityEnum == null) return;
                rpgPlayer.getJob(JobEnum.HUNTER).doJobAction(rpgPlayer, entityEnum.getName(), JobEnum.HUNTER, 1, entityEnum.getExp(), entityEnum.getGold(), p);
            }
    }
}
