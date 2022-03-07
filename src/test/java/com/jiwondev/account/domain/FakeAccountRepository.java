package com.jiwondev.account.domain;

import com.jiwondev.common.exception.EntityNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeAccountRepository implements AccountReader, AccountStore {

    private final Map<Long, Account> data = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Account findByEmail(String email) {
        return data.values().stream()
            .filter(account -> account.getEmail().equals(email))
            .findAny()
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean existsByEmail(String email) {
        return data.values().stream()
            .anyMatch(account -> account.getEmail().equals(email));
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return data.values().stream()
            .anyMatch(account -> account.getNickname().equals(nickname));
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public Account save(Account account) {
        data.putIfAbsent(idGenerator.incrementAndGet(), account);
        return account;
    }
}
