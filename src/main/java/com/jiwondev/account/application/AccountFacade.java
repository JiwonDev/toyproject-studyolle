package com.jiwondev.account.application;

import com.jiwondev.account.domain.Account;
import com.jiwondev.account.domain.AccountMailManager;
import com.jiwondev.account.domain.AccountReader;
import com.jiwondev.account.domain.ConfirmRegisterAccountProcessor;
import com.jiwondev.account.domain.RegisterAccountProcessor;
import com.jiwondev.account.domain.dto.AccountRegisterCommand;
import com.jiwondev.account.interfaces.RegisterConfirmResponse;
import com.jiwondev.account.interfaces.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountFacade {

    private final RegisterAccountProcessor registerAccountProcessor;
    private final ConfirmRegisterAccountProcessor confirmRegisterAccountProcessor;
    private final AccountMailManager accountMailManager;
    private final AccountReader accountReader;
    private final AccountMapper mapper;

    @Transactional
    public void register(SignUpForm form) {
        AccountRegisterCommand command = mapper.toCommand(form);

        registerAccountProcessor.register(command);

        // TODO Mail 트랜잭션 분리
        Account account = accountReader.findByEmail(command.getEmail());
        accountMailManager.sendConfirm(account);
    }

    @Transactional
    public RegisterConfirmResponse registerConfirm(String email, String token) {
        confirmRegisterAccountProcessor.confirmRegister(email, token);

        var account = accountReader.findByEmail(email);
        var count = accountReader.count();
        return mapper.toResponse(account.getNickname(), count);
    }
}
