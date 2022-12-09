package net.royalguardians.RPGSystem.player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.enums.Gender;
import net.royalguardians.RPGSystem.enums.NobleTitle;
import net.royalguardians.RPGSystem.playerclass.PlayerClass;
import net.royalguardians.RPGSystem.specie.SpecieEnum;
import net.royalguardians.RPGSystem.jobs.jobsEnums.JobEnum;
import net.royalguardians.RPGSystem.jobs.PlayerJobs;
import net.royalguardians.RPGSystem.specie.AbstractSpecie;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RPGPlayer {

    private Player player;
    private int level, exp, id;
    private NobleTitle title;
    private AbstractSpecie specie;
    private PlayerClass playerClass;
    private Gender gender;
    private RPGStats rpgStats;
    private double gold;
    private HashMap<JobEnum, PlayerJobs> jobs;

    private RPGScoreboard board;


    public RPGPlayer(Player player, int level, int exp, NobleTitle title, AbstractSpecie specie, Gender gender, double gold, int id, HashMap<JobEnum, PlayerJobs> jobs, RPGStats rpgStats, PlayerClass playerClass) {
        this.player = player;
        this.title = title;
        this.specie = specie;
        this.playerClass = playerClass;
        this.gender = gender;
        this.gold = gold;
        this.level = level;
        this.exp = exp;
        this.id = id;
        this.jobs = jobs;
        this.rpgStats = rpgStats;
        this.board = new RPGScoreboard(this);
    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public double getGold() {
        return gold;
    }

    public NobleTitle getTitle() {
        return title;
    }

    public AbstractSpecie getSpecie() {
        return specie;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void addGold(double add, Player p) {
        double old = this.gold;
        this.gold += add;
        getScoreboard().updateGold(this, old);
    }
    public void setGold(int gold, Player p) {
        double old = this.gold;
        this.gold = gold;
        getScoreboard().updateGold(this, old);
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(PlayerClass playerClass, Player p) {
       // PlayerClass old = this.getPlayerClass();
        this.playerClass = playerClass;
        RPGSystem.getDatabase().setPlayerClass(id, playerClass.getPlayerClass().getOrdinal());
    }

    public void setSpecie(AbstractSpecie specie, Player p) {
        SpecieEnum old = this.getSpecie().getSpecieEnum();
        this.specie.getSpecieEnum().removePlayer(p.getName());
        this.specie = specie;
        this.specie.getSpecieEnum().addPlayer(p.getName());
        p.setPlayerListName(specie.getSpecieEnum().getColorcode() + specie.getSpecieEnum().getName() + " §7| " + specie.getSpecieEnum().getColorcode() + p.getName());
        getScoreboard().updateSpecie(this, p, old);
        RPGSystem.getDatabase().setSpecie(id, specie.getSpecieEnum().getOrdinal());
        specie.getSpecieEnum().setPlayerAttribute(this, p);
    }

    public void setTitle(NobleTitle title, Player p) {
        String old = this.getTitle().getColorcode() + this.getTitle().getName();
        this.title = title;
        getScoreboard().updateTitle(this, old);
        RPGSystem.getDatabase().setTitle(id, title.getOrdinal());
    }

    public Player getPlayer() {
        return player;
    }

    public int getExp() {
        return exp;
    }


    public HashMap<JobEnum, PlayerJobs> getJobs() {
        return jobs;
    }

    public PlayerJobs getJob(JobEnum jobEnum) {
        return jobs.get(jobEnum);
    }

    public RPGStats getStats() {
        return rpgStats;
    }

    public int getLevel() {
        return level;
    }

    public RPGScoreboard getScoreboard() {
        return board;
    }

    public void setJobs(HashMap<JobEnum, PlayerJobs> jobs) {
        this.jobs = jobs;
    }

    public void save() {
        RPGSystem.getDatabase().savePlayer(this);
    }

    public void sendActionBar(String s, Player p) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));
    }

    public void onDamage(double damage, Player p) {
        getStats().currentHP -= damage;
        if(getStats().currentHP <= 0) {
         onDied(p);
         onRespawn();
        } else {
            double playerHealth = getStats().getCurrentHP()/((double) getStats().getMaximalHP(getSpecie().getSpecieEnum()) / 20);
            if(playerHealth < 1) playerHealth = 1;
            if(playerHealth > 20) playerHealth = 20;
            p.setHealth(playerHealth);
            updateStatus(p);
        }
    }
    public void onDied(Player p) {

    }
    public void onHealth(int health, Player p) {
        getStats().currentHP += health;
        if(getStats().currentHP > getStats().getMaximalHP(specie.getSpecieEnum())) getStats().currentHP = getStats().getMaximalHP(specie.getSpecieEnum());
        double playerHealth = getStats().getCurrentHP()/((double) getStats().getMaximalHP(getSpecie().getSpecieEnum()) / 20);
        p.setHealth(playerHealth);
        updateStatus(p);
    }

    public void onManaRegen(int mana, Player p) {
        getStats().currentMana += mana;
        if(getStats().currentMana > getStats().getMaximalMana(specie.getSpecieEnum())) getStats().currentMana = getStats().getMaximalMana(specie.getSpecieEnum());
        updateStatus(p);
    }

    public void onNormalHealthManaRegen(Player p) {
        double addMana = getStats().getMaximalHP(specie.getSpecieEnum())*0.05;
        addMana = addMana < 1 ? 1 : addMana;
        getStats().currentMana += addMana;
        if(getStats().currentMana > getStats().getMaximalMana(specie.getSpecieEnum())) getStats().currentMana = getStats().getMaximalMana(specie.getSpecieEnum());

        double addHP = getStats().getMaximalHP(specie.getSpecieEnum())*0.05;
        addHP = addHP < 1 ? 1 : addHP;
        getStats().currentHP += addHP+getSpecie().getSpecieEnum().getRegenerationAmount();
        if(getStats().currentHP > getStats().getMaximalHP(specie.getSpecieEnum())) getStats().currentHP = getStats().getMaximalHP(specie.getSpecieEnum());

        double playerHealth = getStats().getCurrentHP()/((double) getStats().getMaximalHP(getSpecie().getSpecieEnum()) / 20);
       if(playerHealth < 1) playerHealth = 1;
       if(playerHealth > 20) playerHealth = 20;
       p.setHealth(playerHealth);
        updateStatus(p);
    }

    public void onRespawn() {
        getStats().setCurrent(getStats().getMaximalMana(getSpecie().getSpecieEnum()), getStats().getMaximalHP(getSpecie().getSpecieEnum()));
    }
    public void updateStatus(Player p) {
    sendActionBar("§c"  + RPGSystem.df.format(getStats().getCurrentHP()) + "§7/§c" + getStats().getMaximalHP(getSpecie().getSpecieEnum()) + " HP §7| | §b" +  RPGSystem.df.format(getStats().getCurrentMana()) + "§7/§b" + getStats().getMaximalMana(getSpecie().getSpecieEnum()) + " Mana", p);
    }
}