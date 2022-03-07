package com.jiwondev.account.infrastructure.config;

import com.jiwondev.account.domain.AccountRegisterProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    AccountRegisterProcessor.class
})
@Configuration
public class AccountModuleConfig {

}
