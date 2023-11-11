package edu.miu.ea.ap.helper.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@Profile({"dev"})
public class WebSecurityLocalConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.ldap.localUsers}")
    private String localUsers;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        String[] users = localUsers.split(";");
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        for (String username : users) {
            manager.createUser(User.withUsername(username).password("$2y$12$JNOXvXmc.DAozqN5hacoqOy24YvdzXCR5nRGxCVaMOJVgihPtHV0S").authorities("USER").build());
        }
        return manager;
    }

}
