package net.royalguardians.RPGSystem.playerclass;

import net.royalguardians.RPGSystem.player.RPGPlayer;

public abstract class PlayerClass {

    PlayerClassEnum playerClass;

    public PlayerClass() {

    }

    public PlayerClassEnum getPlayerClass() {
        return playerClass;
    }

    public abstract void doSkill1();
    public abstract void doSkill2();
    public abstract void doSkill3();

}
