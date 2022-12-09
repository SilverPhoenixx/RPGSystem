package net.royalguardians.RPGSystem.enums;

public enum NobleTitle {
    NONE("?", "§f", 0),
    EMPEROR("Kaiser", "§c", 1),
    EMPRESS("Kaiserin", "§c", 2),

    KING("König", "§c", 3),
    QUEEN("Königin", "§c", 4),

    PRINCE("Prinz", "§6", 5),
    PRINCESS("Prinzessin", "§6", 6),

    DUKE("Herzog", "§6", 7),
    DUCHESS("Herzogin", "§6", 8),

    LANDGRAVE("Landgraf", "§e", 9),
    LANDGRAVINE("Landgräfin", "§e", 10),

    COUNT("Graf", "§e", 11),
    COUNTESS("Gräfin", "§e", 12),

    BARON("Baron", "§b", 13),
    BARONESSE("Baronesse", "§b", 14),

    MAYOR("Bürgermeister", "§b", 15),
    MAYORESS("Bürgermeisterin", "§b", 16),

    NOBLE("Adliger", "§9", 17),
    NOBLEWOMAN("Adlige", "§9", 18),

    KNIGHT("Ritter", "§7", 19),
    FEMKNIGHT("Ritterin", "§7", 20),

    PEASANT("Bauer", "§f", 21),
    PEASANTWOMAN("Bäuerin", "§f", 22),

    SLAVE("Sklave", "§f", 23),
    SLAVEWOMAN("Sklavin", "§f", 24);

    String name;
    String colorcode;
    int ordinal;

    private NobleTitle(String name, String colorcode, int ordinal) {
        this.name = name;
        this.colorcode = colorcode;
        this.ordinal = ordinal;
    }

    public static NobleTitle getNobleTitleByOrdinary(int ordinal) {
        switch (ordinal) {
            case 1: return EMPEROR;
            case 2: return EMPRESS;
            case 3: return KING;
            case 4: return QUEEN;
            case 5: return PRINCE;
            case 6: return PRINCESS;
            case 7: return DUKE;
            case 8: return DUCHESS;
            case 9: return LANDGRAVE;
            case 10: return LANDGRAVINE;
            case 11: return COUNT;
            case 12: return COUNTESS;
            case 13: return BARON;
            case 14: return BARONESSE;
            case 15: return MAYOR;
            case 16: return MAYORESS;
            case 17: return NOBLE;
            case 18: return NOBLEWOMAN;
            case 19: return KNIGHT;
            case 20: return FEMKNIGHT;
            case 21: return PEASANT;
            case 22: return PEASANTWOMAN;
            case 23: return SLAVE;
            case 24: return SLAVEWOMAN;
            default: return NONE;
        }
    }

    public static NobleTitle getNobleTitleByName(String name) {
        switch (name) {
            case "EMPEROR":
                return EMPEROR;
            case "EMPRESS":
                return EMPRESS;
            case "KING":
                return KING;
            case "QUEEN":
                return QUEEN;
            case "PRINCE":
                return PRINCE;
            case "PRINCESS":
                return PRINCESS;
            case "DUKE":
                return DUKE;
            case "DUCHESS":
                return DUCHESS;
            case "LANDGRAVE":
                return LANDGRAVE;
            case "LANDGRAVINE":
                return LANDGRAVINE;
            case "COUNT":
                return COUNT;
            case "COUNTESS":
                return COUNTESS;
            case "BARON":
                return BARON;
            case "BARONESSE":
                return BARONESSE;
            case "MAYOR":
                return MAYOR;
            case "MAYORESS":
                return MAYORESS;
            case "NOBLE":
                return NOBLE;
            case "NOBLEWOMAN":
                return NOBLEWOMAN;
            case "KNIGHT":
                return KNIGHT;
            case "FEMKNIGHT":
                return FEMKNIGHT;
            case "PEASANT":
                return PEASANT;
            case "PEASANTWOMAN":
                return PEASANTWOMAN;
            case "SLAVE":
                return SLAVE;
            case "SLAVEWOMAN":
                return SLAVEWOMAN;
            default:
                return NONE;
        }

    }

    public String getName() {
        return name;
    }

    public String getColorcode() {
        return colorcode;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
