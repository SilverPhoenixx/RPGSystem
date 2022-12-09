package net.royalguardians.RPGSystem.equipment;

public enum Rarity {
    GODNESS("GÖTTLICH", "§b", 0.5, 0.5, 0),
    CELEASTIAL("HIMMLISCH", "§3", 0.4, 0.4, 0),
    LEGENDARY("LEGENDÄR", "§6", 0.35, 0.35, 0),
    HEROIC("HELDENHAFT", "§e", 0.3, 0.25, 0),
    MYTHIC("MYSTISCH", "§d", 0.2, 0.2, 0),
    EPIC("EPISCH", "§5", 0.15, 0.15, 0),
    RARE("SELTEN", "§9", 0.05, 0.05, 0),
    UNCOMMON("UNGEWÖHNLICH", "§a", 0.025, 0.025, 0),
    COMMON("GEWÖHNLICH", "§7", 0, 0, 0);

    private String name;
    private String colorcode;
    private double damageMultiply;
    private double defenseMultiply;
    private double healthMultiply;

    private Rarity(String name, String colorcode, double damageMultiply, double defenseMultiply, double healthMultiply) {
        this.name = name;
        this.colorcode = colorcode;
        this.damageMultiply = damageMultiply;
        this.defenseMultiply = defenseMultiply;
        this.healthMultiply = healthMultiply;
    }

    public String getName() {
        return name;
    }

    public double getDamageMultiply() {
        return damageMultiply;
    }

    public double getDefenseMultiply() {
        return defenseMultiply;
    }

    public double getHealthMultiply() {
        return healthMultiply;
    }

    public String getColorcode() {
        return colorcode;
    }
}
