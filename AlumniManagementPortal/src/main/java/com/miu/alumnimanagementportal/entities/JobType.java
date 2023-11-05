package com.miu.alumnimanagementportal.entities;

public enum JobType {
    FULL_TIME("FULL_TIME"),
    PART_TIME("PART_TIME"),
    CONTRACT("CONTRACT"),
    REMOTE("REMOTE");

    private final String label;
    JobType(String label) {
        this.label = label;
    }
}
