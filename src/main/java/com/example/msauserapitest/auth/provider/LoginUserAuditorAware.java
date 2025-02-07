package com.example.msauserapitest.auth.provider;

import com.example.msauserapitest.user.RequestUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof RequestUser user)) {
            return Optional.empty();
        }

        return Optional.of(user.getUserId());
    }
}
