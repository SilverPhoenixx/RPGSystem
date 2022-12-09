package net.royalguardians.RPGSystem.jobs;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MinerJob extends PlayerJobs {


    public MinerJob(int level, int exp, HashMap<String, Integer> list, Player p) {
        super(level, exp, list, p);
    }

    public double getGold() {
        return 0;
    }


}
