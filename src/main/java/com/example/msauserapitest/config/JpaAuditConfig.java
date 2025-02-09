package com.example.msauserapitest.config;
import com.example.msauserapitest.auth.provider.LoginUserAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "commonAuditorAware")
public class JpaAuditConfig {
    @Bean
    public AuditorAware<Long> commonAuditorAware() {
        return new LoginUserAuditorAware();
    }
}
