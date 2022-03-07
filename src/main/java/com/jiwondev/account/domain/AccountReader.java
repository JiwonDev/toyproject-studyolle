package com.jiwondev.account.domain;

public interface AccountReader {

    Account findByEmail(String email);

    boolean existByEmail(String email);

    boolean existByNickname(String nickname);
}
