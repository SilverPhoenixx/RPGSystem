package net.royalguardians.RPGSystem.equipment;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.royalguardians.RPGSystem.RPGSystem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.UUID;

public enum Weapons {
    CHERRYBLOODSWORD(Material.DIAMOND_SWORD, "Kirschblutschwert", "Zahn des Alten Graf Dracula",
            true,  12, 0, 0.3,0, 6, Rarity.GODNESS, EquipmentSlot.HAND),

    BLOODWEDDING(Material.DIAMOND_SWORD, "Blutige Hochzeit","Am Tag der Hochzeit\nerschlug die Göttin der Schönheit\ndamit ihren Ehemann",
            false, 10, 1, 0, 0.015,3, Rarity.CELEASTIAL, EquipmentSlot.HAND),

    GOLDEYE(Material.DIAMOND_SWORD, "Goldenes Auge", "Dieses Schwert wurde einst\naus Ampheris Auge geschmiedet",
            true, 8, 1, 0.3,0.015, 2,  Rarity.LEGENDARY, EquipmentSlot.HAND),

    DESTROYED_WOODEN_SWORD(Material.WOODEN_SWORD, "Zerstörtes Holzschwert", "Dieses Schwert diente\neinst als Zahnstocher\nfür die Götter",
            false, 2, 0, 0,0, 0,  Rarity.COMMON, EquipmentSlot.HAND);


    private ItemStack item;

    private String description;
    private String name;
    private boolean shiny;

    private double damage;
    private double maxHealth;
    private double movementSpeed;
    private double knockbackResistance;
    private double armor;

    private Rarity rarity;

    private EquipmentSlot equipmentSlot;

    private Weapons(Material item, String name, String description, boolean shiny, double damage, double maxHealth, double movementSpeed, double knockbackResistance, double armor, Rarity rarity, EquipmentSlot equipmentSlot) {
        this.item = new ItemStack(item);
        this.name = name;
        this.description = description;
        this.shiny = shiny;

        this.damage = damage;
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        this.knockbackResistance = knockbackResistance;
        this.armor = armor;

        this.rarity = rarity;

        this.equipmentSlot = equipmentSlot;
        createItem();
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }


    public ItemStack getItem() {
        return item;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }

    public void createItem() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(rarity.getColorcode() + name);
        ArrayList<String> list = new ArrayList<>();

        list.add("§l§6Seltenheitsgrad:§r " + rarity.getColorcode() + rarity.getName());
        if(damage != 0) {
            double ndamage = damage+(damage*rarity.getDamageMultiply());
            list.add("§l§6Angriffsschaden:§e " +  RPGSystem.df.format(ndamage) + " §c⚔");
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", ndamage, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        }
        if(maxHealth != 0) {
            double nmaxHealth = maxHealth + (maxHealth * rarity.getHealthMultiply());
            list.add("§l§6Maximales Leben:§e +" +   RPGSystem.df.format(nmaxHealth) + " §4❤");
            itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", nmaxHealth, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        }
        if(movementSpeed != 0) {
            double nmovementSpeed = movementSpeed;
            list.add("§l§6Bewegungsgeschwindigkeit:§e +" +   RPGSystem.df.format(nmovementSpeed) + " §b≈");
            itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", nmovementSpeed, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        }
        if(knockbackResistance != 0) {
            double nknockbackResistance = knockbackResistance;
            list.add("§l§6Rückstoßrestistanz:§e +" +   RPGSystem.df.format(nknockbackResistance) + " §9⬅");
            itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", nknockbackResistance, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        }
        if(armor != 0) {
            double narmor = armor + (armor * rarity.getDefenseMultiply());
            list.add("§l§6Rüstung:§e +" +   RPGSystem.df.format(narmor) + " §3♚");
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", narmor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        }
        if(shiny) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        list.add(" ");

        for(String descriptionLine : description.split("\n")) {
            list.add(descriptionLine);
        }
        itemMeta.setUnbreakable(true);
        itemMeta.setLore(list);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(itemMeta);
    }


    public static Weapons getWeapon(String name) {
        for(Weapons weapons : values()) if(name.equals(weapons.name())) return weapons;
        return null;
    }
}
