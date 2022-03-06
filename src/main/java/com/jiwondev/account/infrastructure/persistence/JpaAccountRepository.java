package com.jiwondev.account.infrastructure.persistence;

import com.jiwondev.account.domain.Account;
import com.jiwondev.account.domain.AccountReader;
import com.jiwondev.account.domain.AccountStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository
    extends JpaRepository<Account, Long>, AccountReader, AccountStore {

}
