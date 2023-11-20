package edu.ea.userservice.config;


import edu.ea.userservice.model.User;
import edu.ea.userservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credential = userRepo.findByEmail(username);
            return credential.map(CustomUserDetails ::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}
