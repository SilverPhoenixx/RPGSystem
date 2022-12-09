package net.royalguardians.RPGSystem.player;

import net.royalguardians.RPGSystem.specie.SpecieEnum;

public class RPGStats {


    int damage;
    int movementSpeed;
    int defense;
    int critical;

    int pointsHP;
    int pointsMana;

    double currentHP;
    double currentMana;

    int points;
    int collected;


    public RPGStats(int pointsHP, double currentHP, int damage, int movementSpeed, int defense, int critical, int pointsMana, double currentMana, int points, int collected) {
        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.defense = defense;
        this.critical = critical;

        this.pointsHP = pointsHP;
        this.pointsMana = pointsMana;

        this.currentHP = currentHP;
        this.currentMana = currentMana;

        this.points = points;
        this.collected = collected;
    }


    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getPoints() {
        return points;
    }

    public int getCollected() {
        return collected;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }


    public int getCritical() {
        return critical;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }


    public void setCollected(int collected) {
        this.collected = collected;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public void setStats(int pointsHP, int currentHP, int damage, int movementSpeed, int defense, int critical, int pointsMana, int currentMana, int points, int collected) {
        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.defense = defense;
        this.critical = critical;

        this.currentHP = currentHP;
        this.currentMana = currentMana;

        this.pointsHP = pointsHP;
        this.pointsMana = pointsMana;

        this.points = points;
        this.collected = collected;
    }

    public void setCurrent(int currentMana, int currentHP) {
        this.currentMana = currentMana;
        this.currentHP = currentHP;
    }

    public double getCurrentHP() {
        return currentHP;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public int getPointsHP() {
        return pointsHP;
    }

    public int getPointsMana() {
        return pointsMana;
    }

    public int getMaximalHP(SpecieEnum specieEnum) {
        return specieEnum.getHealth()+(pointsHP*3);
    }

    public int getMaximalMana(SpecieEnum specieEnum) {
        return specieEnum.getMana()+(pointsMana*2);
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }
}
