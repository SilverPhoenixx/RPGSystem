package net.royalguardians.RPGSystem.jobs.jobsEnums;


import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;

import java.util.ArrayList;
import java.util.List;

public enum MaterialEnum {

    /* Sämtliche Enums für Miner */
    OBSIDIAN("Obsidian", Material.OBSIDIAN,  2.5,1, JobEnum.MINER),
    EMERALD_ORE("Smaragderz", Material.EMERALD_ORE,  5,1, JobEnum.MINER),
    DIAMOND_ORE("Diamanterz", Material.DIAMOND_ORE,  4.5, 1, JobEnum.MINER),
    LAPIS_ORE("Lapis Lazulierz", Material.LAPIS_ORE,  4, 1, JobEnum.MINER),
    REDSTONE_ORE("Redstoneerz", Material.REDSTONE_ORE,  3.5, 1, JobEnum.MINER),
    GOLD_ORE("Golderz", Material.GOLD_ORE,  2.5,1, JobEnum.MINER),
    IRON_ORE("Eisenerz", Material.IRON_ORE,  2,1, JobEnum.MINER),
    QUARZ_ORE("Quartzerz", Material.NETHER_QUARTZ_ORE,1, 2, JobEnum.MINER),
    NETHERRACK("Netherrack", Material.NETHERRACK, 0.5,1, JobEnum.MINER),
    NETHERGOLDORE("Nethergolderz", Material.NETHER_GOLD_ORE, 3,1, JobEnum.MINER),
    GILDED_BLACKSTONE("Basaltgolderz", Material.GILDED_BLACKSTONE, 3.5, 1,JobEnum.MINER),
    ANCIENT_DEBRIS("Antiker Schrott", Material.ANCIENT_DEBRIS, 7.5,1, JobEnum.MINER),
    COAL_ORE("Kohleerz", Material.COAL_ORE,  1.5,1, JobEnum.MINER),
    MOSSY_COBBLESTONE("bemooster Bruchstein", Material.MOSSY_COBBLESTONE, 1, 1,JobEnum.MINER),
    COBBLESTONE("Bruchstein", Material.COBBLESTONE,  1, 1,JobEnum.MINER),
    STONE("Stein", Material.STONE,  1,1, JobEnum.MINER),
    GRANIT("Granit", Material.GRANITE,  1,1, JobEnum.MINER),
    DIORIT("Diorit", Material.DIORITE,  1,1, JobEnum.MINER),
    ANDESITE("Andesit", Material.ANDESITE,  1,1, JobEnum.MINER),

    /* Sämtliche Farmer Enum */
    RED_MUSHROOM("Roter Pilz", Material.RED_MUSHROOM,1, 1, JobEnum.FARMER),
    BROWN_MUSHROOM("Brauner Pilz", Material.BROWN_MUSHROOM,1, 1, JobEnum.FARMER),
    CRIMSON_FUNGUS("Karmesin Pilz", Material.CRIMSON_FUNGUS, 1,1, JobEnum.FARMER),
    WARPED_FUNGUS("Wirrpilz", Material.WARPED_FUNGUS, 1,1, JobEnum.FARMER),
    WHEAT("Weizen", Material.WHEAT_SEEDS, 1,1, JobEnum.FARMER),
    CARROT("Karotte", Material.CARROTS, 1,1, JobEnum.FARMER),
    POTATO("Kartoffel", Material.POTATOES, 1,1, JobEnum.FARMER),
    NETHERWART("Netherwarze", Material.NETHER_WART, 1,1, JobEnum.FARMER),
    SUGAR_CANE("Zuckerrohr", Material.SUGAR_CANE, 1,1, JobEnum.FARMER),
    BEETROOT("Rote Beete", Material.BEETROOTS, 1,1, JobEnum.FARMER),
    SWEET_BERRY_BUSH("Süßbeeren", Material.SWEET_BERRY_BUSH, 1,1, JobEnum.FARMER),
    COCOA("Kakaobohnen", Material.COCOA, 1,1, JobEnum.FARMER),
    PUMPKIN("Kürbis", Material.PUMPKIN, 1,1, JobEnum.FARMER),
    MELON("Melone", Material.MELON, 1,1, JobEnum.FARMER),
    HONEY_NEST("Honig", Material.BEE_NEST,1,1, JobEnum.FARMER),
    HONEY_HIVE("Honig", Material.BEEHIVE, 1,1, JobEnum.FARMER),
    CACTUS("Kaktus", Material.CACTUS, 1,1, JobEnum.FARMER),

    /* Sämtliche Digger Enum */
    CRIMSON_NYLIUM("Karmesin Erde", Material.CRIMSON_NYLIUM, 1,1, JobEnum.DIGGER),
    WARPED_NYLIUM("Wirrerde", Material.WARPED_NYLIUM, 1,1, JobEnum.DIGGER),
    DIRT("Erde", Material.DIRT,1,1, JobEnum.DIGGER),
    COARSED_DIRT("Grobe Erde", Material.COARSE_DIRT, 1,1, JobEnum.DIGGER),
    PODZOL("Podsol", Material.PODZOL, 1,1, JobEnum.DIGGER),
    GRASS_BLOCK("Grass", Material.GRASS_BLOCK, 1,1, JobEnum.DIGGER),
    GRASS_PATH("Weg", Material.DIRT_PATH,1,1, JobEnum.DIGGER),
    FARMLAND("Ackerboden", Material.FARMLAND,1,1, JobEnum.DIGGER),
    SAND("Sand", Material.SAND, 1,1, JobEnum.DIGGER),
    RED_SAND("Roter Sand", Material.RED_SAND, 1,1, JobEnum.DIGGER),
    MYCEL("Myzel", Material.MYCELIUM,1,1, JobEnum.DIGGER),
    SOULSAND("Seelensand", Material.SOUL_SAND,1,1, JobEnum.DIGGER),
    SOULDIRT("Seelenerde", Material.SOUL_SOIL,1,1, JobEnum.DIGGER),
    GRAVEL("Kies", Material.GRAVEL,1,1, JobEnum.DIGGER),
    CLAY("Lehm", Material.CLAY, 1,1,JobEnum.DIGGER),
    SNOWBLOCK("Schneeblock", Material.SNOW_BLOCK, 1,1, JobEnum.DIGGER),
    SNOWLAYER("Schneeebene", Material.SNOW, 1,1, JobEnum.DIGGER),

    /* Sämtliche Woodcutter Enum */
    STRIPPED_OAK_LOG("Entrindeter Eichenstamm", Material.STRIPPED_OAK_LOG,1,1, JobEnum.WOODCUTTER),
    OAK_LOG("Eichenstamm", Material.OAK_LOG,1,1, JobEnum.WOODCUTTER),
    STRIPPED_SPRUCE_LOG("Entrindeter Fichtenstamm",Material.STRIPPED_SPRUCE_LOG,1,1, JobEnum.WOODCUTTER),
    SPRUCE_LOG("Fichtenstamm", Material.SPRUCE_LOG,1,1, JobEnum.WOODCUTTER),
    STRIPPED_BIRCH_LOG("Entrindeter Birkenstamm", Material.STRIPPED_BIRCH_LOG,1,1, JobEnum.WOODCUTTER),
    BIRCH_LOG("Birkenstamm",Material.BIRCH_LOG,1,1, JobEnum.WOODCUTTER),
    STRIPPED_JUNGLE_LOG("Entrindeter Junglestamm", Material.STRIPPED_JUNGLE_LOG,1,1, JobEnum.WOODCUTTER),
    JUNGLE_LOG("Junglestamm", Material.JUNGLE_LOG,1,1, JobEnum.WOODCUTTER),
    STRIPPED_ACACIA_LOG("Entrindeter Akazienstamm", Material.STRIPPED_ACACIA_LOG,1,1, JobEnum.WOODCUTTER),
    ACACIA_LOG("Akazienstamm", Material.ACACIA_LOG,1,1, JobEnum.WOODCUTTER),
    STRIPPED_DARK_OAK_LOG("Entrindeter Schwarzeichenstamm", Material.STRIPPED_DARK_OAK_LOG,1,1, JobEnum.WOODCUTTER),
    DARK_OAK_LOG("Schwarzeichenstamm", Material.DARK_OAK_LOG,1,1, JobEnum.WOODCUTTER),
    STRIPPED_CRIMSON_LOG("Geschälter Karmesinstiel", Material.STRIPPED_CRIMSON_STEM,1,1, JobEnum.WOODCUTTER),
    CRIMSON_LOG("Karmesinstiel", Material.CRIMSON_STEM,1,1, JobEnum.WOODCUTTER),
    STRIPPED_WARPED_LOG("Geschälter Wirrstiel", Material.STRIPPED_WARPED_STEM,1,1, JobEnum.WOODCUTTER),
    WARPED_LOG("Wirrstiel", Material.WARPED_STEM,1,1, JobEnum.WOODCUTTER);

    String name;
    Material m;
    double gold;
    int exp;
    JobEnum jobEnum;

    private MaterialEnum(String name, Material m, double gold, int exp, JobEnum jobEnum) {
        this.name = name;
        this.m = m;
        this.gold = gold;
        this.jobEnum = jobEnum;
        this.exp = exp;
    }

    public static MaterialEnum getInteractBlock(Block b) {
        if(b == null) return null;
        switch (b.getType()) {
            case BEE_NEST: return HONEY_NEST;
            case BEEHIVE: return HONEY_HIVE;
            case SWEET_BERRY_BUSH: return SWEET_BERRY_BUSH;
        }
        return null;
    }

    public static MaterialEnum getBreakMaterials(Block b) {
        if(b.getBlockData() instanceof Ageable) {
            Ageable state = (Ageable) b.getBlockData();
            switch (b.getType()) {
                case BEE_NEST:
                case BEEHIVE:
                    return null;
                case WHEAT: {
                    if (state.getAge() == state.getMaximumAge()) return MaterialEnum.WHEAT;
                    return null;
                }
                case CARROTS: {
                    if (state.getAge() == state.getMaximumAge()) return MaterialEnum.CARROT;
                    return null;
                }
                case POTATOES: {
                    if (state.getAge() == state.getMaximumAge()) return MaterialEnum.POTATO;
                    return null;
                }
                case NETHER_WART: {
                    if (state.getAge() == state.getMaximumAge()) return MaterialEnum.NETHERWART;
                    return null;
                }
                case BEETROOTS: {
                    if (state.getAge() == state.getMaximumAge()) return MaterialEnum.BEETROOT;
                    return null;
                }
                case SWEET_BERRY_BUSH: {
                    if (state.getAge() == state.getMaximumAge()) return MaterialEnum.SWEET_BERRY_BUSH;
                    return null;
                }
                case COCOA: {
                    if (state.getAge() == state.getMaximumAge()) return MaterialEnum.COCOA;
                    return null;
                }
            }
        }
        for (MaterialEnum m : values()) {
            if (m.getMaterial() == b.getType()) {
                return m;
            }
        }
        return null;
    }

    public static List<MaterialEnum> getJobMaterialList(JobEnum jobEnum) {
        List<MaterialEnum> materialList = new ArrayList<>();
        for(MaterialEnum m : values()) {
            if(m.getJobEnum() == jobEnum) {
                materialList.add(m);
            }
        }
        return materialList;
    }

    public Material getMaterial() {
        return m;
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

    public JobEnum getJobEnum() {
        return jobEnum;
    }
}
