package com.jiwondev.account.domain;

public final class AccountRegisterProcessor {

    private final AccountStore accountStore;

    public AccountRegisterProcessor(AccountStore accountStore) {
        this.accountStore = accountStore;
    }

    public void register(Account account) {
        var newAccount = accountStore.save(account);
        newAccount.generateEmailCheckToken();
    }

}
