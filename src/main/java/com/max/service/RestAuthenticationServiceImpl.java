package com.max.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RestAuthenticationServiceImpl implements RestAuthenticationService {
    private final static String API_KEY = "superSecretApiKey";
    private final PasswordEncoder passwordEncoder;

    public static final long ACCEPTED_TIME_INTERVAL = 100000;

    public RestAuthenticationServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isApiKeyValid(long timestamp, String encoded) {
        long time = new Date().getTime();

        if (Math.abs(time - timestamp) > ACCEPTED_TIME_INTERVAL) return false;

        return passwordEncoder
                .matches(timestamp + "." + API_KEY, encoded);
    }
}
