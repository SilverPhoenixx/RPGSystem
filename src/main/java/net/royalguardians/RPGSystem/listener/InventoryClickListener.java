package net.royalguardians.RPGSystem.listener;

import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.jobs.jobsEnums.ActivityEnum;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClickListener(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        if(event.getCurrentItem() == null || event.getClickedInventory() == null) return;
        if(event.getClick() == ClickType.MIDDLE) return;
        if((event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.LEFT) && p.getItemOnCursor().getType() != Material.AIR) return;
        RPGPlayer rpgPlayer = RPGSystem.players.get(p);
          if(event.getClickedInventory().getType() == InventoryType.ANVIL){
                  AnvilInventory anvil = (AnvilInventory) event.getClickedInventory();
                  if(anvil.getRepairCost() > p.getLevel()) return;
                 if(event.getSlot() == 2) {

              ItemStack item = event.getClickedInventory().getItem(1);
               if(ActivityEnum.isRepairItem(item)) {
                   ItemStack repaired = event.getClickedInventory().getItem(0);
                   int repair = repaired.getItemMeta() instanceof Damageable ? ((Damageable) repaired.getItemMeta()).getDamage() : 0;
                   ItemStack itemStack = event.getClickedInventory().getItem(0);
                   if (ActivityEnum.isArmor(itemStack.getType())) {
                       ActivityEnum m = ActivityEnum.REPAIR_ARMOR;
                       rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold()*repair, p);
                   } else if(ActivityEnum.isTool(itemStack.getType())) {
                       ActivityEnum m = ActivityEnum.REPAIR_TOOL;
                       rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold()*repair, p);
                   } else if (ActivityEnum.isWeapon(itemStack.getType())) {
                       ActivityEnum m = ActivityEnum.REPAIR_WEAPON;
                       rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold()*repair, p);
                   }
               } else {
                   ItemStack itemStack = event.getClickedInventory().getItem(0);
                   if (ActivityEnum.isArmor(itemStack.getType())) {
                       ActivityEnum m = ActivityEnum.FUSING_ARMOR;
                       rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), p);
                   } else if(ActivityEnum.isTool(itemStack.getType())) {
                       ActivityEnum m = ActivityEnum.FUSING_TOOL;
                       rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), p);
                   } else if (ActivityEnum.isWeapon(itemStack.getType())) {
                       ActivityEnum m = ActivityEnum.FUSING_WEAPON;
                       rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), 1, m.getExp(), m.getGold(), p);
                   }
               }
           }
      } else if(event.getClickedInventory().getType() == InventoryType.CARTOGRAPHY) {
              if(event.getSlot() == 2) {
                  ItemStack item = event.getClickedInventory().getItem(0);
                  ActivityEnum m = ActivityEnum.COPY_MAP;
                  rpgPlayer.getJob(m.getJobEnum()).doJobAction(rpgPlayer, m.getName(), m.getJobEnum(), item.getAmount(), m.getExp() * item.getAmount(), m.getGold() * item.getAmount(), p);
              }
          }
    }
}
