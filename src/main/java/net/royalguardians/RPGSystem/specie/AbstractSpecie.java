package net.royalguardians.RPGSystem.specie;

import net.royalguardians.RPGSystem.player.RPGPlayer;
import org.bukkit.entity.Player;

public abstract class AbstractSpecie {

    SpecieEnum specieEnum;

    public AbstractSpecie() {

    }

    public SpecieEnum getSpecieEnum() {
        return specieEnum;
    }
    public void regenerate(int count, RPGPlayer rpgPlayer, Player player) {
        rpgPlayer.onNormalHealthManaRegen(player);
    }
}
