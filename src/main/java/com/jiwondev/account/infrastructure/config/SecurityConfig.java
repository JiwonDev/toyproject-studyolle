package com.jiwondev.account.infrastructure.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 입력받은 비밀번호를 {algorithm} 방식으로 암호화해서 마치 DB에 보관되는 것과 유사 (현재는 no-op이므로 암호화를 하지 않음)
        auth.inMemoryAuthentication()
            .withUser("jiwon").password("{noop}123").roles("USER")
            .and()
            .withUser("admin").password("{noop}!@#").roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers(POST, "/login", "/sign-up", "/check-email", "/email-login",
                "/check-email-login", "/login-link").permitAll()
            .mvcMatchers(GET, "/", "/check-email", "/sign-up", "/check-email-token", "/email-login",
                "/check-email-login", "/login-link").permitAll()
            .mvcMatchers(GET, "/profile/*").permitAll()
            .anyRequest().authenticated();
    }
}
