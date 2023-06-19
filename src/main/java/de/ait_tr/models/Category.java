package de.ait_tr.models;

public enum Category {

        ELECTRONICS("ELC", "electronics"),
        WATCHES("WCS", "watches"),
        HEALTH("HLT", "health"),
        ACCESSORIES("ACS", "accessories");

        private final String abbreviation;
        private final String description;

        Category(String abbreviation, String description ) {
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
