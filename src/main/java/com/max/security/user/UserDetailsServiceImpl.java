package com.max.security.user;

import com.max.entity.User;
import com.max.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);

        UserDetails userDetails =
                new UserDetailsImpl(
                        user.getLogin(),
                        user.getPassword(),
                        user.getAuthorities()
                );
        logger.info("loaded user: " + user);
        return userDetails;
    }
}
