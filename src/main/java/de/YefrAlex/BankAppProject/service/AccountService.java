package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.entity.Account;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    List<Account> getAccountList();

    Optional<Account> getAccountById(UUID accountId);

    Account updateAccount(Account account, UUID accountId);

    void deleteAccountById(UUID accountId);

    Account saveAccount(Account account);
}
