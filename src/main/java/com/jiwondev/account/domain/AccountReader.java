package com.jiwondev.account.domain;

public interface AccountReader {

    boolean existByEmail(String email);

    boolean existByNickname(String nickname);
}
