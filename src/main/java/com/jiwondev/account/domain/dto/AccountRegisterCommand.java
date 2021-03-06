package com.jiwondev.account.domain.dto;

public final class AccountRegisterCommand {

    private String nickname;
    private String email;
    private String password;

    public AccountRegisterCommand(String email, String nickname, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
