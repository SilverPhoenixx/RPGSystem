package net.royalguardians.RPGSystem.jobs.jobsEnums;

public enum  JobEnum {
    // Miner is for mining Stone and ores FINISHED
    MINER("Minenarbeiter"),
    // Woodcutter is for cutting trees FINISHED
    WOODCUTTER("Holzfäller"),
    // Digger is a job for Sand, Clay and Gravel FINISHED
    DIGGER("Schaufler"),
    // Hunter is a job for hunting "Monster!" IN PROGRESS
    HUNTER("Jäger"),
    // Farmer is a job for seeding wheat, carrot and co FINISHED
    FARMER("Farmer"),
    // Angler is a job for catch fish with angel FINISHED
    ANGLER("Angler"),
    // Blacksmith is a job for smithing armor, tool, weapon FINISHED
    BLACKSMITH("Schmied"),
    // Cartograph is a job for create maps FINISHED
    CARTOGRAPH("Kartograph"),
    // Wizard is a job for enchanting with enchantingmenttable FINISHED
    WIZARD("Zauberer");


    String name;

    private JobEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static JobEnum getJobEnumByName(String name) {
        switch (name) {
            case "MINER": return MINER;
            case "WOODCUTTER": return WOODCUTTER;
            case "DIGGER": return DIGGER;
            case "HUNTER": return HUNTER;
            case "FARMER": return  FARMER;
            case "ANGLER": return ANGLER;
            case "BLACKSMITH": return BLACKSMITH;
            case "CARTOGRAPH": return CARTOGRAPH;
            case "WIZARD": return WIZARD;
            default: return null;
        }
    }
}
