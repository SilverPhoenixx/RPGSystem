package net.royalguardians.RPGSystem.jobs;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class WizardJob extends PlayerJobs {
    public WizardJob(int level, int exp, HashMap<String, Integer> list, Player p) {
        super(level, exp, list, p);
    }

    @Override
    public double getGold() {
        return 0;
    }

}
