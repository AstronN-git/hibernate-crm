package com.max.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RestAuthenticationServiceImpl implements RestAuthenticationService {
    private final static String API_KEY = "superSecretApiKey";
    private final PasswordEncoder passwordEncoder;

    public RestAuthenticationServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isApiKeyValid(long timestamp, String encoded) {
        return passwordEncoder
                .matches(timestamp + "." + API_KEY, encoded);
    }
}
