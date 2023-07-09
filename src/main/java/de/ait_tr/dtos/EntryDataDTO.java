package de.ait_tr.dtos;

public record EntryDataDTO(boolean isValid, String entryData) {
    @Override
    public String toString() {
        return "ManualEntryRecordDTO{" +
                "isValid=" + isValid +
                ", entryData='" + entryData + '\'' +
                '}';
    }
}
