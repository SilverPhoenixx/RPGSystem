package net.royalguardians.RPGSystem;

import net.royalguardians.RPGSystem.commands.NobleTitleCommand;
import net.royalguardians.RPGSystem.commands.SpecieCommand;
import net.royalguardians.RPGSystem.commands.WeaponCommand;
import net.royalguardians.RPGSystem.playerclass.PlayerClassEnum;
import net.royalguardians.RPGSystem.specie.SpecieEnum;
import net.royalguardians.RPGSystem.listener.*;
import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.specie.*;
import net.royalguardians.RPGSystem.sql.Database;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class RPGSystem extends JavaPlugin {
    public static RPGSystem instance;

    public static Map<SpecieEnum, AbstractSpecie> species = new HashMap<>();

    public static Map<Player, RPGPlayer> players = new HashMap<>();
    public static Database db;
    public static DecimalFormat df = new DecimalFormat("#.##");

    public static int regenerationCount;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

        instance = this;

        World world = Bukkit.createWorld(null);

        getCommand("Specie").setExecutor(new SpecieCommand());
        getCommand("NobleTitle").setExecutor(new NobleTitleCommand());
        getCommand("Weapon").setExecutor(new WeaponCommand());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new FishingListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractAtBlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new CraftListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityRegainHealthListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnchantmentListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);

        db = new Database("host", 3306, "database", "user", "password");

        species.put(SpecieEnum.NONE, new NoneSpecie());
        species.put(SpecieEnum.WOLF, new WolfSpecie());
        species.put(SpecieEnum.HUMAN, new HumanSpecie());
        species.put(SpecieEnum.DRAGON, new DragonSpecie());
        species.put(SpecieEnum.ELF, new ElfSpecie());
        species.put(SpecieEnum.DWARF, new DwarfSpecie());
        species.put(SpecieEnum.FAIRY, new FairySpecie());
        species.put(SpecieEnum.PHOENIX, new PhoenixSpecie());
        species.put(SpecieEnum.OGER, new OgerSpecie());
        PlayerClassEnum.loadPlayerClass();
    }

    @Override
    public void onDisable() {
    }

    public static RPGSystem getInstance() {
        return instance;
    }

    public static Database getDatabase() {
        return db;
    }

}
