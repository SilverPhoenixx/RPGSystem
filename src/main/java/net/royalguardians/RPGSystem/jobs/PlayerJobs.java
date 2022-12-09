package net.royalguardians.RPGSystem.jobs;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.jobs.jobsEnums.ActivityEnum;
import net.royalguardians.RPGSystem.jobs.jobsEnums.EntityEnum;
import net.royalguardians.RPGSystem.jobs.jobsEnums.JobEnum;
import net.royalguardians.RPGSystem.jobs.jobsEnums.MaterialEnum;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class PlayerJobs {

    static int startExp = 500;

    int level;
    int exp;
    HashMap<String, Integer> list;
    Player player;

    public PlayerJobs(int level, int exp, HashMap<String, Integer> list, Player p) {
        this.player = p;

        this.level = level;
        this.exp = exp;
        this.list = list;
    }

    public abstract double getGold();


    public int getLevel() {
        return level;
    }

    public void addExp(int exp) {
        this.exp += exp;
        if(this.exp >= (Math.pow(500, this.level))) {
            this.exp -= Math.pow(500, this.level);
            this.level++;
            player.spigot().sendMessage(ChatMessageType.CHAT, new TextComponent("§6LEVEL UP!"));
        }
    }

    public int getExp() {
        return exp;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLists(HashMap<String, Integer> list) {
        this.list = list;
    }

    public static PlayerJobs getPlayerJob(JobEnum jobEnum, int level, int exp, HashMap<String, Integer> list, Player p) {
        if(list == null) list = new HashMap<>();
        switch (jobEnum) {
            case MINER: {
                if(list.isEmpty()) {
                    for(MaterialEnum m : MaterialEnum.getJobMaterialList(JobEnum.MINER)) {
                        list.put(m.getName(), 0);
                    }
                }
                return new MinerJob(level, exp, list, p);
            }
            case DIGGER: {
                if(list.isEmpty()) {
                    for(MaterialEnum m : MaterialEnum.getJobMaterialList(JobEnum.DIGGER)) {
                        list.put(m.getName(), 0);
                    }
                }
                return new DiggerJob(level, exp, list, p);
            }
            case FARMER: {
                if (list.isEmpty()) {
                    for (MaterialEnum m : MaterialEnum.getJobMaterialList(JobEnum.FARMER)) {
                        list.put(m.getName(), 0);
                    }
                }
                return new FarmerJob(level, exp, list, p);
            }
            case HUNTER: {
                if(list.isEmpty()) {
                    for(EntityEnum m : EntityEnum.values()) {
                        list.put(m.getName(), 0);
                    }
                }
                return new HunterJob(level, exp, list, p);
            }
            case WOODCUTTER: {
                if(list.isEmpty()) {

                    for(MaterialEnum m : MaterialEnum.getJobMaterialList(JobEnum.WOODCUTTER)) {
                        list.put(m.getName(), 0);
                    }
                }
                return new WoodcutterJob(level, exp, list, p);
            }
            case ANGLER: {
                    if(list.isEmpty()) {
                        for(ActivityEnum m : ActivityEnum.getActivityList(JobEnum.ANGLER)) {
                            list.put(m.getName(), 0);
                        }
                    }
                    return new AnglerJob(level, exp, list, p);
            }
            case WIZARD: {
                if(list.isEmpty()) {
                    for(ActivityEnum m : ActivityEnum.getActivityList(JobEnum.WIZARD)) {
                        list.put(m.getName(), 0);
                    }
                }
                return new WizardJob(level, exp, list, p);
            }
            case BLACKSMITH: {
                if(list.isEmpty()) {
                    for(ActivityEnum m : ActivityEnum.getActivityList(JobEnum.BLACKSMITH)) {
                        list.put(m.getName(), 0);
                    }
                }
                return new BlacksmithJob(level, exp, list, p);
            }
            case CARTOGRAPH: {
                if(list.isEmpty()) {
                    for(ActivityEnum m : ActivityEnum.getActivityList(JobEnum.CARTOGRAPH)) {
                        list.put(m.getName(), 0);
                    }
                }
                return new CartographJob(level, exp, list, p);
            }
        }
        return null;
    }

    public HashMap<String, Integer> getList() {
        return list;
    }


    public void addObjectCount(String object, int i) {
        if(!list.containsKey(object)) return;
        int count = list.get(object)+i;
        list.replace(object, count);
    }

    public void doJobAction(RPGPlayer rpgPlayer, String object, JobEnum jobEnum, int count, int exp, double gold, Player p) {
        addExp(exp);
        addObjectCount(object, count);
        rpgPlayer.addGold(gold, player);
        rpgPlayer.sendActionBar("§b" + jobEnum.getName() + " §7[§e+" + exp + " EXP§7] §7» §3" + object + " §7[§e+" + RPGSystem.df.format(gold) + " Gold§7]", p);

    }
}
