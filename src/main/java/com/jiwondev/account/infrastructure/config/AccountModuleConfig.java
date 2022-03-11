package com.jiwondev.account.infrastructure.config;

import com.jiwondev.account.domain.RegisterAccountProcessor;
import com.jiwondev.account.domain.RegisterEmailConfirmProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Import({
    RegisterAccountProcessor.class,
    RegisterEmailConfirmProcessor.class
})
@Configuration
public class AccountModuleConfig {

    /**
     * 스프링이 제공하는 PasswordEncoder Delegate 객체를 사용합니다. (기본값 Bcrypt)
     *
     * @return new BCryptPasswordEncoder;
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
