package com.jiwondev.account.infrastructure.config;

import com.jiwondev.account.domain.ConfirmRegisterAccountProcessor;
import com.jiwondev.account.domain.RegisterAccountProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Import({
    RegisterAccountProcessor.class,
    ConfirmRegisterAccountProcessor.class
})
@Configuration
public class AccountModuleConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
