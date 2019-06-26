package com.bryan.springbootwas.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface CustomAuthenticationService {

    Authentication authenticationV1(Authentication authentication);

    Authentication authenticationV2(Authentication authentication);
}
