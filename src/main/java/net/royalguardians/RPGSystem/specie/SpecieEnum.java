package net.royalguardians.RPGSystem.specie;

import net.royalguardians.RPGSystem.player.RPGPlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum SpecieEnum {
    NONE("?", "?", "§f", "", 10, 1, 20, 0, 0.1, 0, 1, false, false, false, false, 99),
    PHOENIX("Phoenix", "✞", "§c", "", 50,4, 17, 1, 2, 0,1, true, true, true, false, 1),
    DRAGON("Drache", "☣", "§5", "", 40,6, 18, 2, 0.11, 0, 1, false, true, true, false, 2),
    OGER("Oger", "⚉", "§2", "", 20,7, 16, 0, 0.1,0.1, 1, false, false, false, true, 3),

    ELF("Elf", "✼", "§a", "", 50,5, 15, 0, 0.11, 0,3,  true, false, false, false, 4),
    DWARF("Zwerg", "⚙", "§6", "", 20,1, 30, 5, 0.09, 0.1,2,  false, true, true, false, 5),
    WOLF("Wolf", "➙", "§7", "", 30,2, 20, 3, 0.125, 0, 3, true, false, false, false, 6),
    FAIRY("Fee", "⚛", "§d", "", 60,2, 19, 7, 0.115, 0, 1, true, false, false,  true, 7),

    HUMAN("Mensch", "⚔", "§b", "", 25, 3, 20, 0, 0.1, 0,2,  false, false, false, false, 8);

    String name;
    String icon;
    String colorcode;
    String headBase64;

    int mana;
    int damage;
    int health;
    int defense;
    double walkspeed;
    double knockbackResistance;

    boolean fallResistance;
    boolean fireResistance;
    boolean explosionResistance;
    boolean projectileResistance;

    int regenerationAmount;
    int ordinal;

    List<String> stringList = new ArrayList<>();

    private SpecieEnum(String name, String icon, String colorcode, String headBase64, int mana, int damage, int health, int defense, double walkspeed, double knockbackResistance, int regenerationAmount, boolean fallResistance, boolean fireResistance, boolean explosionResistance, boolean projectileResistance, int ordinal) {
        this.name = name;
        this.icon = icon;
        this.colorcode = colorcode;
        this.headBase64 = headBase64;

        this.mana = mana;
        this.damage = damage;
        this.health = health;
        this.defense = defense;
        this.walkspeed = walkspeed;
        this.knockbackResistance = knockbackResistance;
        this.regenerationAmount = regenerationAmount;

        this.fallResistance = fallResistance;
        this.fireResistance = fireResistance;
        this.explosionResistance = explosionResistance;
        this.projectileResistance = projectileResistance;

        this.ordinal = ordinal;
    }

    public int getMana() {
        return mana;
    }

    public int getHealth() {
        return health;
    }

    public boolean isExplosionResistance() {
        return explosionResistance;
    }

    public boolean isFallResistance() {
        return fallResistance;
    }

    public boolean isProjectileResistance() {
        return projectileResistance;
    }

    public int getRegenerationAmount() {
        return regenerationAmount;
    }

    public double getKnockbackResistance() {
        return knockbackResistance;
    }

    public boolean isFireResistance() {
        return fireResistance;
    }

    public static SpecieEnum getSpecieByOrdinal(int ordinal) {
        switch (ordinal) {
            case 1: return PHOENIX;
            case 2: return DRAGON;
            case 3: return OGER;
            case 4: return ELF;
            case 5: return DWARF;
            case 6: return WOLF;
            case 7: return FAIRY;
            case 8: return HUMAN;

            default: return NONE;
        }

    }
public static SpecieEnum getSpecieByName(String specie) {
        switch (specie) {
            case "PHOENIX": return PHOENIX;
            case "DRAGON": return DRAGON;
            case "OGER": return OGER;
            case "ELF": return ELF;
            case "DWARF": return DWARF;
            case "WOLF": return WOLF;
            case "FAIRY": return FAIRY;
            case "HUMAN": return HUMAN;
            default: return NONE;
        }
}

    public void addPlayer(String s) {
        stringList.add(s);
    }
    public void removePlayer(String s) {
        stringList.remove(s);
    }
    public List<String> getPlayer() {
        return stringList;
    }
    public String getColorcode() {
        return colorcode;
    }

    public String getName() {
        return name;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public String getIcon() {
        return icon;
    }

    public void setPlayerAttribute(RPGPlayer rpgPlayer, Player p) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        p.setHealth(20);
        // MOVEMENT SPEED
        p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(walkspeed + (rpgPlayer.getStats().getMovementSpeed()/10000) * 5);
        // KNOCKBACK RESISTANCE
        p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);

       rpgPlayer.getStats().setCurrent(rpgPlayer.getStats().getMaximalMana(this), rpgPlayer.getStats().getMaximalHP(this));
    }
}
