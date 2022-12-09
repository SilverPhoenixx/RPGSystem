package net.royalguardians.RPGSystem.player;

import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.specie.SpecieEnum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class RPGScoreboard {

    private Scoreboard scoreboard;
    private Objective objective;
    private RPGPlayer rpgPlayer;

    public RPGScoreboard(RPGPlayer rpgPlayer) {
        this.rpgPlayer = rpgPlayer;

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("display",  "dummy", "§3PhoenixRPG");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void setDisplayName(String displayname) {
        objective.setDisplayName(displayname);
    }

    public void setScore(String content, int score) {
        objective.getScore(content).setScore(score);
    }

    public void removeScore(String content) {
        scoreboard.resetScores(content);
    }

    public void updateScore(String oldContent, String newContent, int score) {
        removeScore(oldContent);
        setScore(newContent, score);
    }

    public void loadTeams() {
        for(SpecieEnum s : SpecieEnum.values()) {
            if(s.getOrdinal() == 0) {
                int i = s.getOrdinal() * SpecieEnum.values().length;
                scoreboard.registerNewTeam(i + s.getName());
                scoreboard.getTeam(i + s.getName()).setPrefix("§7[" + s.getColorcode() + s.getIcon() + "§7] " + s.getColorcode());
            } else {
                scoreboard.registerNewTeam(s.getOrdinal() + s.getName());
                scoreboard.getTeam(s.getOrdinal() + s.getName()).setPrefix("§7[" + s.getColorcode() + s.getIcon() + "§7] " + s.getColorcode());
            }
        }
    }

    public void addTeam(Player p, SpecieEnum s) {
        if(s.getOrdinal() == 0) {
            int i = s.getOrdinal() * SpecieEnum.values().length;
             scoreboard.getTeam(i + s.getName()).addEntry(p.getName());
        } else {
             scoreboard.getTeam(s.getOrdinal() + s.getName()).addEntry(p.getName());
        }
        p.setScoreboard(scoreboard);
    }
    public void removeTeam(Player p, SpecieEnum s) {
        if(s.getOrdinal() == 0) {
            int i = s.getOrdinal() * SpecieEnum.values().length;
            scoreboard.getTeam(i + s.getName()).removeEntry(p.getName());
        } else {
            scoreboard.getTeam(s.getOrdinal() + s.getName()).removeEntry(p.getName());
        }
        p.setScoreboard(scoreboard);
    }

    public void sendScoreboard(RPGPlayer rpgPlayer, Player p) {
        setScore("§7» §3Level", 11);
        setScore("§f§b" + rpgPlayer.getLevel(), 10);
        setScore("§a ", 9);
        setScore("§7» §3Titel", 8);
        setScore(rpgPlayer.getTitle().getColorcode() + rpgPlayer.getTitle().getName(), 7);
        setScore( "§8 ", 6);
        setScore("§7» §3Rasse", 5);
        setScore(rpgPlayer.getSpecie().getSpecieEnum().getColorcode() + rpgPlayer.getSpecie().getSpecieEnum().getName() + " §f", 4);
        setScore("§4 ", 3);
        setScore("§7» §3Gold", 2);
        setScore("§b" + RPGSystem.df.format(rpgPlayer.getGold()), 1);

        loadTeams();
        addTeam(rpgPlayer.getPlayer(), rpgPlayer.getSpecie().getSpecieEnum());

        sendTabMessage(p);

        Bukkit.getScheduler().runTaskLater(RPGSystem.getInstance(), () ->

            p.setPlayerListName(rpgPlayer.getSpecie().getSpecieEnum().getColorcode() + rpgPlayer.getSpecie().getSpecieEnum().getName() + " §7| " + rpgPlayer.getSpecie().getSpecieEnum().getColorcode() + rpgPlayer.getSpecie().getSpecieEnum().getColorcode() + p.getName())

            , 5L);
    }

    public void updateTitle(RPGPlayer rpgPlayer, String old) {
        updateScore(old, rpgPlayer.getTitle().getColorcode() + rpgPlayer.getTitle().getName(),7);
    }

    public void updateSpecie(RPGPlayer rpgPlayer, Player p, SpecieEnum old) {
        updateScore(old.getColorcode() + old.getName() + " §f", rpgPlayer.getSpecie().getSpecieEnum().getColorcode() + rpgPlayer.getSpecie().getSpecieEnum().getName() + " §f", 4);
        removeTeam(p, old);
        addTeam(p, rpgPlayer.getSpecie().getSpecieEnum());
    }

    public void updateGold(RPGPlayer rpgPlayer, double old) {
       updateScore("§b" + RPGSystem.df.format(old), "§b" + RPGSystem.df.format(rpgPlayer.getGold()), 1);
    }


    public void sendTabMessage(Player p){
        String header = "§7» §3Phoenix§bRPG §7«" + "\n" + "§a";
        String footer = "\n" + "§bWähle deine Rasse und erringe den Sieg!";
        header = header.replace("&", "§").replace("%n", "\n");
        footer = footer.replace("&", "§").replace("%n", "\n");
        p.setPlayerListHeaderFooter(header, footer);
    }
}
