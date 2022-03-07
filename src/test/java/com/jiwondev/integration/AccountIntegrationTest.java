package com.jiwondev.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("계정 서비스 통합 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class AccountIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JavaMailSender mockMailSender;

    @Test
    @DisplayName("회원가입에 성공하고 이메일이 발송된다.")
    void accountIntegrationTest() throws Exception {
        final String email = "jiwon3382@naver.com";
        final String password = "123123123";
        final String nickname = "asd2";

        var actions = mockMvc.perform(post("/sign-up")
            .param("email", email)
            .param("nickname", nickname)
            .param("password", password)
            .with(csrf()));

        // Assert
        actions
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));

        verify(mockMailSender).send(any(SimpleMailMessage.class));

    }
}
