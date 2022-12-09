package net.royalguardians.RPGSystem.listener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.jobs.jobsEnums.MaterialEnum;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;


public class BlockBreakListener implements Listener {
    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

       /** Map<Vector, Block> blocks = new HashMap<Vector, Block>();
        for(int y = 0; y < 16; y++) {
            Block block = e.getBlock().getLocation().subtract(0,16,0).add(0, y, 0).getBlock();
            blocks.put(block.getLocation().toVector(), block);
        }
        Test.sendMultiBlockChange(blocks, e.getPlayer()); */

        if (RPGSystem.players.containsKey(p)) {
            RPGPlayer rpgPlayer = RPGSystem.players.get(p);
            MaterialEnum m = MaterialEnum.getBreakMaterials(e.getBlock());
            if (m == null) return;
            if (e.getBlock().getType() == Material.SUGAR_CANE) {
                int i = 1;
                Location loc = e.getBlock().getLocation();
                for (; ; ) {
                    loc = loc.add(0, 1, 0);
                    if (loc.getBlock().getType() == Material.SUGAR_CANE) {
                        i++;
                    } else {
                        break;
                    }
                }
                    rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), i, i * m.getExp(), i * m.getGold(), p);
                } else{
                    rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), p);
                }
        }
    }


}
