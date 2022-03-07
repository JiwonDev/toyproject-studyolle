package com.jiwondev.account.domain;

public interface AccountReader {

    Account findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    long count();
}
