package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository=accountRepository;
    }

    @Override
    public Account saveAccount(Account account)
    {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountList()
    {
        List<Account> accountList =  accountRepository.findAll();
        return accountList;

    }

    @Override
    public Optional<Account> getAccountById(UUID accountId)
    {
        return accountRepository.findById(accountId);
    }

    @Override
    public Account updateAccount(Account account, UUID accountId) {
        Account accDB
                = accountRepository.findById(accountId)
                .get();
        return accountRepository.save(accDB);
    }

    @Override
    public void deleteAccountById(UUID accountId)
    {
        accountRepository.deleteById(accountId);
    }
}
