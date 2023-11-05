package com.miu.alumnimanagementportal.entities;

public enum EventType {
    Reunion("REUNION"),
    ProfessionalNetwork("PROFESSIONAL_NETWORK"),
    Workshop("WORKSHOP"),
    Seminar("SEMINAR");

    private final String label;
    EventType(String label) {
        this.label = label;
    }
}
