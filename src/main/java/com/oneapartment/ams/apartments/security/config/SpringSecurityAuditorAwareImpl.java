package com.oneapartment.ams.apartments.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class SpringSecurityAuditorAwareImpl implements AuditorAware {
    @Override
    public Optional<String> getCurrentAuditor() {

        return getUsernameFromAuthentication();
    }

    public static Optional<String> getUsernameFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return Optional.ofNullable(currentUserName);
        }
        else{
            return null;
        }
    }
}
