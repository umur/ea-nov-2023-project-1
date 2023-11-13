package edu.miu.ea.ap.helper;

public class Enumerations {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    public enum RoleType {
        NOTSET,
        ADMIN,
        USER,
    }

    public enum EventType {
        REUNION,
        PROFESSIONAL_NETWORKING,
        WORKSHOP,
        SEMINAR
    }

    public enum NewsType {
        NEWS,
        UPDATE,
        ANNOUNCEMENT
    }

}
