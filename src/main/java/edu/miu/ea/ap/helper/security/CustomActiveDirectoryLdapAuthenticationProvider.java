package edu.miu.ea.ap.helper.security;

import edu.miu.ea.ap.service.APUserService;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.DefaultDirObjectFactory;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;
import org.springframework.security.ldap.authentication.AbstractLdapAuthenticationProvider;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomActiveDirectoryLdapAuthenticationProvider extends AbstractLdapAuthenticationProvider {

    private static final Pattern SUB_ERROR_CODE = Pattern.compile(".*data\\s([0-9a-f]{3,4}).*");
    private static final int USERNAME_NOT_FOUND = 1317;
    private static final int INVALID_PASSWORD = 1326;
    private static final int NOT_PERMITTED = 1328;
    private static final int PASSWORD_EXPIRED = 1330;
    private static final int ACCOUNT_DISABLED = 1331;
    private static final int ACCOUNT_EXPIRED = 1793;
    private static final int PASSWORD_NEEDS_RESET = 1907;
    private static final int ACCOUNT_LOCKED = 1909;
    private final String domain;
    private final String rootDn;
    private final String url;
    CustomActiveDirectoryLdapAuthenticationProvider.ContextFactory contextFactory = new CustomActiveDirectoryLdapAuthenticationProvider.ContextFactory();
    private boolean convertSubErrorCodesToExceptions;
    private String searchFilter = "(&(objectClass=user)(userPrincipalName={0}))";
    private Map<String, Object> contextEnvironmentProperties = new HashMap();
    private APUserService userService;

    public CustomActiveDirectoryLdapAuthenticationProvider(String domain, String url, String rootDn) {
        Assert.isTrue(StringUtils.hasText(url), "Url cannot be empty");
        this.domain = StringUtils.hasText(domain) ? domain.toLowerCase() : null;
        this.url = url;
        this.rootDn = StringUtils.hasText(rootDn) ? rootDn.toLowerCase() : null;
    }


    public CustomActiveDirectoryLdapAuthenticationProvider(String domain, String url) {
        Assert.isTrue(StringUtils.hasText(url), "Url cannot be empty");
        this.domain = StringUtils.hasText(domain) ? domain.toLowerCase() : null;
        this.url = url;
        this.rootDn = this.domain == null ? null : this.rootDnFromDomain(this.domain);
    }

    public void setUserDao(APUserService userService) {
        this.userService = userService;
    }

    protected DirContextOperations doAuthentication(UsernamePasswordAuthenticationToken auth) {
        String username = auth.getName();
        String password = (String) auth.getCredentials();
        DirContext ctx = this.bindAsUser(username, password);

        DirContextOperations var5;
        try {
            var5 = this.searchForUser(ctx, username);
        } catch (NamingException var9) {
            this.logger.error("Failed to locate directory entry for authenticated user: " + username, var9);
            throw this.badCredentials(var9);
        } finally {
            LdapUtils.closeContext(ctx);
        }

        return var5;
    }

    protected Collection<? extends GrantedAuthority> loadUserAuthorities(DirContextOperations userData, String username, String password) {
        Collection<SimpleGrantedAuthority> privileges = new ArrayList();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        privileges.add(simpleGrantedAuthority);
        return privileges;
    }

    private DirContext bindAsUser(String username, String password) {
        String bindUrl = this.url;
        Hashtable<String, Object> env = new Hashtable();
        env.put("java.naming.security.authentication", "simple");
        String bindPrincipal = this.createBindPrincipal(username);
        env.put("java.naming.security.principal", bindPrincipal);
        env.put("java.naming.provider.url", bindUrl);
        env.put("java.naming.security.credentials", password);
        env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        env.put("java.naming.factory.object", DefaultDirObjectFactory.class.getName());
        env.putAll(this.contextEnvironmentProperties);

        try {
            return this.contextFactory.createContext(env);
        } catch (NamingException var7) {
            if (!(var7 instanceof AuthenticationException) && !(var7 instanceof OperationNotSupportedException)) {
                throw LdapUtils.convertLdapException(var7);
            } else {
                this.handleBindException(bindPrincipal, var7);
                throw this.badCredentials(var7);
            }
        }
    }

    private void handleBindException(String bindPrincipal, NamingException exception) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Authentication for " + bindPrincipal + " failed:" + exception);
        }

        this.handleResolveObj(exception);
        int subErrorCode = this.parseSubErrorCode(exception.getMessage());
        if (subErrorCode <= 0) {
            this.logger.debug("Failed to locate AD-specific sub-error code in message");
        } else {
            this.logger.info("Active directory authentication failed: " + this.subCodeToLogMessage(subErrorCode));
            if (this.convertSubErrorCodesToExceptions) {
                this.raiseExceptionForErrorCode(subErrorCode, exception);
            }

        }
    }

    private void handleResolveObj(NamingException exception) {
        Object resolvedObj = exception.getResolvedObj();
        boolean serializable = resolvedObj instanceof Serializable;
        if (resolvedObj != null && !serializable) {
            exception.setResolvedObj((Object) null);
        }

    }

    private int parseSubErrorCode(String message) {
        Matcher m = SUB_ERROR_CODE.matcher(message);
        return m.matches() ? Integer.parseInt(m.group(1), 16) : -1;
    }

    private void raiseExceptionForErrorCode(int code, NamingException exception) {
        String hexString = Integer.toHexString(code);
        Throwable cause = new CustomActiveDirectoryAuthenticationException(hexString, exception.getMessage(), exception);
        switch (code) {
            case 1330:
                throw new CredentialsExpiredException(this.messages.getMessage("LdapAuthenticationProvider.credentialsExpired", "User credentials have expired"), cause);
            case 1331:
                throw new DisabledException(this.messages.getMessage("LdapAuthenticationProvider.disabled", "User is disabled"), cause);
            case 1793:
                throw new AccountExpiredException(this.messages.getMessage("LdapAuthenticationProvider.expired", "User account has expired"), cause);
            case 1909:
                throw new LockedException(this.messages.getMessage("LdapAuthenticationProvider.locked", "User account is locked"), cause);
            default:
                throw this.badCredentials(cause);
        }
    }

    private String subCodeToLogMessage(int code) {
        switch (code) {
            case 1317:
                return "User was not found in directory";
            case 1326:
                return "Supplied password was invalid";
            case 1328:
                return "User not permitted to logon at this time";
            case 1330:
                return "Password has expired";
            case 1331:
                return "Account is disabled";
            case 1793:
                return "Account expired";
            case 1907:
                return "User must reset password";
            case 1909:
                return "Account locked";
            default:
                return "Unknown (error code " + Integer.toHexString(code) + ")";
        }
    }

    private BadCredentialsException badCredentials() {
        return new BadCredentialsException(this.messages.getMessage("LdapAuthenticationProvider.badCredentials", "Bad credentials"));
    }

    private BadCredentialsException badCredentials(Throwable cause) {
        return (BadCredentialsException) this.badCredentials().initCause(cause);
    }

    private DirContextOperations searchForUser(DirContext context, String username) throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(2);
        String bindPrincipal = this.createBindPrincipal(username);
        String searchRoot = this.rootDn != null ? this.rootDn : this.searchRootFromPrincipal(bindPrincipal);

        try {
            return SpringSecurityLdapTemplate.searchForSingleEntryInternal(context, searchControls, searchRoot, this.searchFilter, new Object[]{bindPrincipal, username});
        } catch (IncorrectResultSizeDataAccessException var8) {
            if (var8.getActualSize() != 0) {
                throw var8;
            } else {
                UsernameNotFoundException userNameNotFoundException = new UsernameNotFoundException("User " + username + " not found in directory.", var8);
                throw this.badCredentials(userNameNotFoundException);
            }
        }
    }

    private String searchRootFromPrincipal(String bindPrincipal) {
        int atChar = bindPrincipal.lastIndexOf(64);
        if (atChar < 0) {
            this.logger.debug("User principal '" + bindPrincipal + "' does not contain the domain, and no domain has been configured");
            throw this.badCredentials();
        } else {
            return this.rootDnFromDomain(bindPrincipal.substring(atChar + 1, bindPrincipal.length()));
        }
    }

    private String rootDnFromDomain(String domain) {
        String[] tokens = StringUtils.tokenizeToStringArray(domain, ".");
        StringBuilder root = new StringBuilder();
        String[] var4 = tokens;
        int var5 = tokens.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String token = var4[var6];
            if (root.length() > 0) {
                root.append(',');
            }

            root.append("dc=").append(token);
        }

        return root.toString();
    }

    String createBindPrincipal(String username) {
        return this.domain != null && !username.toLowerCase().endsWith(this.domain) ? username + "@" + this.domain : username;
    }

    public void setConvertSubErrorCodesToExceptions(boolean convertSubErrorCodesToExceptions) {
        this.convertSubErrorCodesToExceptions = convertSubErrorCodesToExceptions;
    }

    public void setSearchFilter(String searchFilter) {
        Assert.hasText(searchFilter, "searchFilter must have text");
        this.searchFilter = searchFilter;
    }

    public void setContextEnvironmentProperties(Map<String, Object> environment) {
        Assert.notEmpty(environment, "environment must not be empty");
        this.contextEnvironmentProperties = new Hashtable(environment);
    }

    static class ContextFactory {
        ContextFactory() {
        }

        DirContext createContext(Hashtable<?, ?> env) throws NamingException {
            return new InitialLdapContext(env, (Control[]) null);
        }
    }

}
