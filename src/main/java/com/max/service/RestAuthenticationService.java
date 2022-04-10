package com.max.service;

public interface RestAuthenticationService {
    boolean isApiKeyValid (long timestamp, String encoded);
}
