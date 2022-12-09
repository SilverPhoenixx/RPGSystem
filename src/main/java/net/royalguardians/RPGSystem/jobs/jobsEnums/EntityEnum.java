package net.royalguardians.RPGSystem.jobs.jobsEnums;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public enum EntityEnum {

    BAT("Fledermaus", 1, 1, EntityType.BAT),
    BLAZE("Lohe", 1, 1, EntityType.BLAZE),
    CAT("Katze", 1, 1, EntityType.CAT),
    CHICKEN("Huhn", 1, 1, EntityType.CHICKEN),
    CAVE_SPIDER("Höhlenspinne", 1, 1, EntityType.CAVE_SPIDER),
    COD("Kabeljau", 1, 1, EntityType.COD),
    COW("Kuh", 1, 1, EntityType.COW),
    CREEPER("Creeper", 1, 1, EntityType.CREEPER),
    DOLPHIN("Delfin", 1, 1, EntityType.DOLPHIN),
    DONKEY("Esel", 1, 1, EntityType.DONKEY),
    DROWNED("Ertrunkener", 1, 1, EntityType.DROWNED),
    ELDER_GUARDIAN("Großer Wächter", 1, 1, EntityType.ELDER_GUARDIAN),
    ENDER_DRAGON("Enderdrache", 1, 1, EntityType.ENDER_DRAGON),
    ENDERMAN("Enderman", 1, 1, EntityType.ENDERMAN),
    ENDERMITE("Endermite", 1, 1, EntityType.ENDERMITE),
    EVOKER("Magier", 1, 1, EntityType.EVOKER),
    FOX("Fuchs", 1, 1, EntityType.FOX),
    GHAST("Ghast", 1, 1, EntityType.GHAST),
    GIANT("Riese", 1, 1, EntityType.GIANT),
    GUARDIAN("Wächter", 1, 1, EntityType.GUARDIAN),
    HOGLIN("Hoglin", 1, 1, EntityType.HOGLIN),
    HORSE("Pferd", 1, 1, EntityType.HORSE),
    HUSK("Wüstenzombie", 1, 1, EntityType.HUSK),
    ILLUSIONER("Illusionist", 1, 1, EntityType.ILLUSIONER),
    IRON_GOLEM("Eisengolem", 1, 1, EntityType.IRON_GOLEM),
    LLAMA("Lama", 1, 1, EntityType.LLAMA),
    MAGMA_CUBE("Magmawürfel", 1, 1, EntityType.MAGMA_CUBE),
    MULE("Maultier", 1, 1, EntityType.MULE),
    MUSHROOM_COW("Pilzkuh", 1, 1, EntityType.MUSHROOM_COW),
    OCELOT("Ozelot", 1, 1, EntityType.OCELOT),
    PIGLIN_BRUTE("Barbarischer Piglin", 1, 1, EntityType.PIGLIN_BRUTE),
    POLAR_BEAR("Eisbär", 1, 1, EntityType.POLAR_BEAR),
    PIG("Schwein", 1, 1, EntityType.PIG),
    PIGLIN("Piglin", 1, 1, EntityType.PIGLIN),
    PANDA("Panda", 1, 1, EntityType.PANDA),
    PARROT("Papagei", 1, 1, EntityType.PARROT),
    PHANTOM("Phantom", 1, 1, EntityType.PHANTOM),
    PILLAGER("Plünderer", 1, 1, EntityType.PILLAGER),
    PLAYER("Spieler", 1, 1, EntityType.PLAYER),
    RABBIT("Hase", 1, 1, EntityType.RABBIT),
    RAVAGER("Verwüster", 1, 1, EntityType.RAVAGER),
    SALMON("Lachs", 1, 1, EntityType.SALMON),
    SHEEP("Schaf", 1, 1, EntityType.SHEEP),
    SHULKER("Shulker", 1, 1, EntityType.SHULKER),
    SKELETON_HORSE("Skelettpferd", 1, 1, EntityType.SKELETON_HORSE),
    SKELETON("Skelett",1,1, EntityType.SKELETON),
    SILVERFISH("Silberfisch", 1, 1, EntityType.SILVERFISH),
    SLIME("Schleim", 1, 1, EntityType.SLIME),
    SNOWMAN("Schneemann", 1, 1, EntityType.SNOWMAN),
    SPIDER("Spinne", 1, 1, EntityType.SPIDER),
    SQUID("Tintenfisch", 1, 1, EntityType.SQUID),
    STRAY("Eiswanderer", 1, 1, EntityType.STRAY),
    STRIDER("Schreiter", 1, 1, EntityType.STRIDER),
    TRADER_LLAMA("Händlerlama", 1, 1, EntityType.TRADER_LLAMA),
    TROPICAL_FISH("Tropischer Fisch", 1, 1, EntityType.TROPICAL_FISH),
    TURTLE("Schildkröte", 1, 1, EntityType.TURTLE),
    UNKNOWN("Unbekannt", 1, 1, EntityType.UNKNOWN),
    VEX("Plagegeister", 1, 1, EntityType.VEX),
    VILLAGER("Dorfbewohner", 1, 1, EntityType.VILLAGER),
    VINDICATOR("Diener", 1, 1, EntityType.VINDICATOR),
    WITHER_SKELETON("Witherskelett", 1, 1, EntityType.WITHER_SKELETON),
    WITHER("Wither", 1, 1, EntityType.WITHER),
    WOLF("Wolf", 1, 1, EntityType.WOLF),
    WANDERING_TRADER("Fahrender Händler", 1, 1, EntityType.WANDERING_TRADER),
    WITCH("Hexe", 1, 1, EntityType.WITCH),
    ZOGLIN("Zoglin", 1, 1, EntityType.ZOGLIN),
    ZOMBIE("Zombie", 1, 1, EntityType.ZOMBIE),
    ZOMBIE_HORSE("Zombiepferd", 1, 1, EntityType.ZOMBIE_HORSE),
    ZOMBIE_VILLAGER("Zombiedorfbewohner", 1, 1, EntityType.ZOMBIE_VILLAGER),
    ZOMBIFIED_PIGLIN("Zombifizierter Piglin", 1, 1, EntityType.ZOMBIFIED_PIGLIN);


    String name;
    double gold;
    int exp;
    EntityType type;

    private EntityEnum(String name, double gold, int exp, EntityType type) {
        this.name = name;
        this.gold = gold;
        this.exp = exp;
        this.type = type;
    }

    public static EntityEnum getEntityEnum(EntityType type) {
        for (EntityEnum e : values()) {
            if (type == e.getType()) {
                return e;
            }
        }
        return null;
    }

    public int getExp() {
        return exp;
    }

    public double getGold() {
        return gold;
    }

    public String getName() {
        return name;
    }

    public EntityType getType() {
        return type;
    }
}