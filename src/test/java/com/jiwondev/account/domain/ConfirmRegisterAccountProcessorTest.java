package com.jiwondev.account.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import com.jiwondev.account.domain.exception.InvalidEmailException;
import com.jiwondev.account.domain.exception.InvalidEmailTokenException;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

final class ConfirmRegisterAccountProcessorTest {

    @TestFactory
    @DisplayName("회원가입 이메일 검증 도메인 서비스 테스트")
    Collection<DynamicTest> register() {
        var repository = new FakeAccountRepository();
        var processor = new ConfirmRegisterAccountProcessor(repository);

        var savedAccount = repository.save(
            Account.of("email3383@naver.com", "nickname2", "password!"));
        var testAccount = repository.save(
            Account.of("jiwone123@naver.com", "nick3344", "!password"));

        return List.of(
            dynamicTest("올바른 토큰과 이메일을 입력하고 검증에 성공한다.", () -> {
                // Act
                processor.confirmRegister(
                    savedAccount.getEmail(), savedAccount.getEmailCheckToken());
                // Assert
                var account = repository.findByEmail(savedAccount.getEmail());
                assertThat(account.isEmailVerified()).isTrue();
            }),

            dynamicTest("존재하지 않는 이메일을 입력하면 도메인 예외를 반환한다.", () -> {
                // Act
                var exception = catchThrowable(() -> processor.confirmRegister(
                    "dqjwodqd@naver.com", testAccount.getEmailCheckToken()));
                // Assert
                assertThat(exception).isInstanceOf(InvalidEmailException.class);
            }),

            dynamicTest("이메일은 유효하나 토큰이 다르다면 도메인 예외를 반환한다.", () -> {
                // Act
                var exception = catchThrowable(() -> processor.confirmRegister(
                    testAccount.getEmail(), "I_am_invalid_token"));
                // Assert
                assertThat(exception).isInstanceOf(InvalidEmailTokenException.class);
            })
        );
    }
}
