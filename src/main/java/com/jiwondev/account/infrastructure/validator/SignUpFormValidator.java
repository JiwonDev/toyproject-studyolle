package com.jiwondev.account.infrastructure.validator;

import com.jiwondev.account.domain.AccountReader;
import com.jiwondev.account.interfaces.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountReader accountReader;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var signUpForm = (SignUpForm) target;

        if (accountReader.existByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email", "invalid.email",
                new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일입니다.");
        }

        if (accountReader.existByNickname(signUpForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname",
                new Object[]{signUpForm.getNickname()}, "이미 사용중인 닉네임입니다.");
        }
    }
}
