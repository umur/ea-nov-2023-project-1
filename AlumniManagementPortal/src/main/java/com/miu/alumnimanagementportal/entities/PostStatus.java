package com.miu.alumnimanagementportal.entities;

public enum PostStatus {
    OPENED("OPENED"),
    CLOSED("CLOSED");

    private final String label;
    PostStatus(String label) {
        this.label = label;
    }
}
