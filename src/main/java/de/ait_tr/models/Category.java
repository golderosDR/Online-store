package de.ait_tr.models;

public enum Category {
    WATCHES("WCS", "watches"),
    HEALTH("HLT", "health"),
    ACCESSORIES("ACS", "accessories"),
    SMARTPHONES("SMP", "smartphones"),
    TVS("TVS", "tvs"),
    NOTEBOOKS("NTB", "notebooks"),
    TABLETS("TAB", "tablets"),
    BAGS("BAG", "bags"),
    GLASSES("GLS", "glasses"),
    BELTS("BLT", "belts");

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
