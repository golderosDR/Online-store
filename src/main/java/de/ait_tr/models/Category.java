package de.ait_tr.models;

public enum Category {
    ACCESSORIES("ACS", "accessories"),
    BAGS("BAG", "bags"),
    BELTS("BLT", "belts"),
    GLASSES("GLS", "glasses"),
    HEALTH("HLT", "health"),
    NOTEBOOKS("NTB", "notebooks"),
    SMARTPHONES("SMP", "smartphones"),
    TABLETS("TAB", "tablets"),
    TVS("TVS", "tvs"),
    WATCHES("WCS", "watches");

    private final String abbreviation;
    private final String description;

    Category(String abbreviation, String description) {
        this.abbreviation = abbreviation;
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getDescription() {
        return description;
    }


}
