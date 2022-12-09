package net.royalguardians.RPGSystem.playerclass;

public enum PlayerClassEnum {

        NONE("?", "", "§f", null,0),
        ARCHER("Bogenschütze", "", "§b", null,1), // BOGEN/ARMBRUST
        SWORDSMAN("Schwertkämpfer", "", "§c", null,2), // SCHWERT
        MAGE("Divers", "", "§7", null,3), // SCHAUFEL
        BERSERKER("", "", "", null,4), // AXT
        PUGILIST("Faustkämpfer", "", "", null,5), // FAUST
        SHADOWRUNNER("Schattenläufer", "", "", null,6); // FELDHACKE

        String name;
        String icon;
        String colorcode;
        int ordinal;
        PlayerClass playerClass;

        private PlayerClassEnum(String name, String icon, String colorcode, PlayerClass playerClass, int ordinal) {
            this.name = name;
            this.icon = icon;
            this.colorcode = colorcode;
            this.ordinal = ordinal;
        }

    public void setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }


    public static void loadPlayerClass()  {
            NONE.setPlayerClass(new NoneClass());
            ARCHER.setPlayerClass(new ArcherClass());
            SWORDSMAN.setPlayerClass(new SwordmansClass());
            MAGE.setPlayerClass(new MageClass());
            BERSERKER.setPlayerClass(new BerserkerClass());
            PUGILIST.setPlayerClass(new PugilistClass());
            SHADOWRUNNER.setPlayerClass(new ShadowrunnerClass());
        }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public static PlayerClass getPlayerClass(int i) {
            for(PlayerClassEnum playerClassEnum : values()) if(playerClassEnum.getOrdinal() == i) return playerClassEnum.getPlayerClass();
            return NONE.getPlayerClass();
    }
}
