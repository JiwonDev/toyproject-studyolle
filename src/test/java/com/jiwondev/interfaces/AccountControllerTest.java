package com.jiwondev.interfaces;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.jiwondev.account.application.AccountFacade;
import com.jiwondev.account.infrastructure.validator.SignUpFormValidator;
import com.jiwondev.account.interfaces.AccountController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccountController.class)
final class AccountControllerTest {
// MockMvc 가 필요하다면 @AutoConfigureMockMvc 를 추가하면 된다.

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountFacade accountFacade;

    @MockBean
    private SignUpFormValidator validator; // Controller 에 사용된 Bean Validator 객체

    @Test
    @DisplayName("회원가입 뷰가 정상 반환된다.")
    void 요청_회원가입_입력폼() throws Exception {
        var actions = mockMvc.perform(get("/sign-up"));

        actions
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("signUpForm"))
            .andExpect(view().name("account/sign-up"));

        then(accountFacade).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("회원가입에 성공하고 홈페이지로 리다이렉트한다.")
    void 성공_회원가입() throws Exception {
        var actions = mockMvc.perform(post("/sign-up")
            .param("nickname", "qwe123")
            .param("email", "jiwon3382@naver.com")
            .param("password", "124124124")
            .with(csrf()));

        // Assert
        actions
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));

        then(accountFacade).should().register(any());
    }

    @Test
    @DisplayName("입력값에 오류가 있다면 회원가입에 실패하고 가입페이지로 리다이렉션 한다.")
    void 실패_회원가입_입력값_오류() throws Exception {
        var actions = mockMvc.perform(post("/sign-up")
            .param("nickname", "jiwon")
            .param("email", "email...")
            .param("password", "12345")
            .with(csrf()));

        actions
            .andExpect(status().isOk())
            .andExpect(view().name("account/sign-up"));
    }

}
