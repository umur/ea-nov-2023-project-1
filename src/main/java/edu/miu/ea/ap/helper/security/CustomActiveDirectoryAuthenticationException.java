package edu.miu.ea.ap.helper.security;

import org.springframework.security.core.AuthenticationException;

public class CustomActiveDirectoryAuthenticationException extends AuthenticationException {

    private final String dataCode;

    CustomActiveDirectoryAuthenticationException(String dataCode, String message, Throwable cause) {
        super(message, cause);
        this.dataCode = dataCode;
    }

    public String getDataCode() {
        return this.dataCode;
    }

}
