package com.bryan.springbootwas.service.impl;

import com.bryan.springbootwas.service.CustomAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationServiceImpl implements CustomAuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationServiceImpl.class);

    @Override
    public Authentication authenticationV1(Authentication authentication) {
        return new UsernamePasswordAuthenticationToken(null,null);
    }

    @Override
    public Authentication authenticationV2(Authentication authentication) {
        return new UsernamePasswordAuthenticationToken(null,null);
    }
}
