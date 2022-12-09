package net.royalguardians.RPGSystem.jobs.jobsEnums;
import net.minecraft.world.item.*;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum  ActivityEnum {

    /* Fishing Enum */
    FISHING("Erfolgreiches Fischen", 1,1, JobEnum.ANGLER),

    /* Wizard Enums */
    ENCHANTING("Verzaubern",1,1, JobEnum.WIZARD),
    FUSING_ENCHANTMENT("Verschmelzen (Verzauberung)", 1,1, JobEnum.WIZARD),


    /* Cartograph Enums */
    CRAFT_MAP("Kartenherstellung", 1,1, JobEnum.CARTOGRAPH),
    COPY_MAP("Duplizierung von Karten", 1,1, JobEnum.CARTOGRAPH),

    /* Blacksmith Enums */
    FUSING_WEAPON("Verschmelzung (Waffen)",1,1, JobEnum.BLACKSMITH),
    FUSING_TOOL("Verschmelzung (Werkzeuge)", 1,1, JobEnum.BLACKSMITH),
    FUSING_ARMOR("Verschmelzung (Rüstung)",1,1, JobEnum.BLACKSMITH),
    CRAFT_WEAPON("Herstellung (Waffen)",1,1, JobEnum.BLACKSMITH),
    CRAFT_TOOL("Herstellung (Werkzeuge)", 1,1, JobEnum.BLACKSMITH),
    CRAFT_ARMOR("Herstellung (Rüstung)", 1, 1, JobEnum.BLACKSMITH),
    REPAIR_WEAPON("Reparierung (Waffen)", 0.05,1, JobEnum.BLACKSMITH),
    REPAIR_TOOL("Reparierung (Werkzeuge)", 0.05,1, JobEnum.BLACKSMITH),
    REPAIR_ARMOR("Reparierung (Rüstung)", 0.05,1, JobEnum.BLACKSMITH);

    String name;
    double gold;
    int exp;
    JobEnum jobEnum;

    private ActivityEnum(String name, double gold, int exp, JobEnum jobEnum) {
        this.name = name;
        this.gold = gold;
        this.exp = exp;
        this.jobEnum = jobEnum;
    }

    public static List<ActivityEnum> getActivityList(JobEnum jobEnum) {
        List<ActivityEnum> activityEnums = new ArrayList<>();
     for(ActivityEnum activityEnum : values()) {
         if(activityEnum.getJobEnum() == jobEnum) {
             activityEnums.add(activityEnum);
         }
     }
     return activityEnums;
    }


    public String getName() {
        return name;
    }

    public int getExp() {
        return exp;
    }

    public JobEnum getJobEnum() {
        return jobEnum;
    }

    public double getGold() {
        return gold;
    }


    public static boolean isWeapon(Material material) {
        return EnchantmentTarget.WEAPON.includes(material);
    }
    public static boolean isTool(Material material) {
        return EnchantmentTarget.TOOL.includes(material);
    }
    public static boolean isArmor(Material material) {
        return EnchantmentTarget.ARMOR.includes(material);
    }

    public static boolean isRepairItem(ItemStack itemStack) {
        return (itemStack.getType() == Material.DIAMOND) ||
                (itemStack.getType() == Material.IRON_INGOT) ||
                (itemStack.getType() == Material.GOLD_INGOT) ||
                (itemStack.getType() == Material.NETHERITE_INGOT);

    }
}
