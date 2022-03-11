package com.jiwondev.account.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import com.jiwondev.account.domain.dto.AccountRegisterCommand;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

final class RegisterAccountProcessorTest {

    @TestFactory
    @DisplayName("회원가입 도메인 서비스 테스트")
    Collection<DynamicTest> 회원가입_도메인_서비스() {
        var repository = new FakeAccountRepository();
        var processor = new RegisterAccountProcessor(repository, repository,
            new StubPasswordEncoder());

        var savedAccount = repository.save(
            Account.of("email3383@naver.com", "nickname2", "password!"));

        return List.of(
            dynamicTest("회원가입에 성공하고, 이메일 토큰과 계정이 생성된다.", () -> {
                // Arrange
                var command = new AccountRegisterCommand(
                    "asndqwd@naver.com", "adqnwdi3", "password!");
                // Act
                processor.register(command);

                //Assert
                var account = repository.findByEmail(command.getEmail());
                assertThat(account.getEmailCheckToken()).isNotNull();
            }),

            dynamicTest("이미 존재하는 이메일이라면 예외를 반환한다.", () -> {
                // Arrange
                var command = new AccountRegisterCommand(
                    savedAccount.getEmail(), "abcdabcd", "password!");
                // Act & Assert
                assertThatThrownBy(() -> processor.register(command))
                    .isNotNull();
            }),

            dynamicTest("이미 존재하는 닉네임이라면 예외를 반환한다.", () -> {
                // Arrange
                var command = new AccountRegisterCommand(
                    "abcabcd@naver.com", savedAccount.getNickname(), "password!");
                // Act & Assert
                assertThatThrownBy(() -> processor.register(command))
                    .isNotNull();
            })
        );
    }

    private static class StubPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence rawPassword) {
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return rawPassword.toString().equals(encodedPassword);
        }
    }
}
