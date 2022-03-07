package com.jiwondev.account.interfaces;

import com.jiwondev.account.application.AccountFacade;
import com.jiwondev.account.infrastructure.validator.SignUpFormValidator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountFacade accountFacade;
    private final SignUpFormValidator signUpFormValidator;

    @GetMapping("/sign-up")
    public String register(Model model) {
        model.addAttribute(new SignUpForm()); // sigunUpForm 으로 key 가 등록됨.
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid @ModelAttribute SignUpForm signUpForm, Errors errors) {
        signUpFormValidator.validate(signUpForm, errors);
        if (errors.hasErrors()) {
            return "account/sign-up";
        }

        accountFacade.register(signUpForm);

        return "redirect:/";
    }

}
