package net.royalguardians.RPGSystem.enums;

public enum  Gender {
    NONE("?", "", "§f", 0),
    MALE("Männlich", "", "§b", 1),
    FEMALE("Weiblich", "", "§c", 2),
    DIVERSE("Divers", "", "§7", 3);

    String name;
    String icon;
    String colorcode;
    int ordinal;

    private Gender(String name, String icon, String colorcode, int ordinal) {
        this.name = name;
        this.icon = icon;
        this.colorcode = colorcode;
        this.ordinal = ordinal;

    }

    public static Gender getGenderByOrdinal(int i) {
        switch (i) {
            case 1: return MALE;
            case 2: return FEMALE;
            case 3: return DIVERSE;
            default: return NONE;
        }
    }

    public int getOrdinal() {
        return ordinal;
    }
}
