package com.jiwondev.interfaces;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.jiwondev.account.application.AccountService;
import com.jiwondev.account.domain.AccountReader;
import com.jiwondev.account.infrastructure.validator.SignUpFormValidator;
import com.jiwondev.account.interfaces.AccountController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/*
통합테스트는 SpringBootTest 를 사용한다.
만약 MockMvc 가 필요하다면 @AutoConfigureMockMvc 를 추가하면 된다.
 */
@WebMvcTest(AccountController.class)
final class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;
    @MockBean
    private SignUpFormValidator validator;

//    @Test
//    @DisplayName("회원가입 sign-up 화면이 반환되는지 테스트한다.")
//    void sign_up_test() throws Exception {
//        var actions = mockMvc.perform(get("/sign-up"));
//
//        actions.andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(model().attributeExists("signUpForm"))
//            .andExpect(view().name("account/sign-up"));
//    }

}
