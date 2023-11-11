package edu.miu.ea.ap.helper.security;

import edu.miu.ea.ap.model.domain.APUser;
import edu.miu.ea.ap.service.APUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
@Profile({"stg", "prod"})
public class RefreshTokenUserService implements UserDetailsService {

    @Autowired
    APUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        APUser currentUser = userService.getUserByUsername(username);
        if (currentUser != null) {
            Collection<SimpleGrantedAuthority> privileges = new ArrayList();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
            privileges.add(simpleGrantedAuthority);
            APUser user = new APUser();
            user.setUsername(username);
            user.setAuthorities(privileges);
            return user;
        } else {
            throw new UsernameNotFoundException("Username not found in database: " + username);
        }
    }

}
