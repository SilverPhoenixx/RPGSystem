package net.royalguardians.RPGSystem.listener;

import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.jobs.jobsEnums.ActivityEnum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if(event.getCurrentItem() == null || event.getClickedInventory() == null) return;
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        if(event.getClick() == ClickType.MIDDLE) return;
        if((event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.LEFT) && p.getItemOnCursor().getType() != Material.AIR) return;
        if(event.getSlot() == 0) {
            RPGPlayer rpgPlayer = RPGSystem.players.get(p);
                ItemStack item = event.getClickedInventory().getItem(0);
                        if (ActivityEnum.isArmor(item.getType())) {
                            ActivityEnum m = ActivityEnum.CRAFT_ARMOR;
                            rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), p);
                        } else if(ActivityEnum.isTool(item.getType())) {
                            ActivityEnum m = ActivityEnum.CRAFT_TOOL;
                            rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), p);
                        } else if (ActivityEnum.isWeapon(item.getType())) {
                            ActivityEnum m = ActivityEnum.CRAFT_WEAPON;
                            rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), p);
                        } else if(item.getType() == Material.MAP) {
                            ActivityEnum m = ActivityEnum.CRAFT_MAP;
                            rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp()*item.getAmount(), m.getGold()*item.getAmount(), p);
                        } else if(item.getType() == Material.FILLED_MAP) {
                            ActivityEnum m = ActivityEnum.COPY_MAP;
                            rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp()*item.getAmount(), m.getGold()*item.getAmount(), p);
                        }
                    }
                }
}
