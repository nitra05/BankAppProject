package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.ServerException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService=accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAll(){
        List<Account> allAccounts = accountService.getAccountList();
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Optional<Account>> getAccountById(@PathVariable("accountId") UUID accountId) {

        Optional<Account> account = accountService.getAccountById(accountId);
        if (Objects.nonNull(account)) return ResponseEntity.ok(account);
        else throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PostMapping("/createNewAccount")
    public ResponseEntity<Account> createNewAccount(@RequestBody Account newAccount) throws ServerException {
        Account account = accountService.saveAccount(newAccount);
        if (account == null) {
            throw new ServerException("CreatedAccount_Errror");
        } else {
            return new ResponseEntity<Account>(account, HttpStatus.CREATED);
        }
    }

    @PutMapping("/updateAccount/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID accountId, @RequestBody Account newAccount) throws ServerException {

          Optional<Account> accountDB = accountService.getAccountById(accountId);
        if (accountDB == null) {
            throw new ServerException("Account_not_found");
        } else {
            Account accDB = accountDB.get();

            accDB.setAccountNumber(newAccount.getAccountNumber());
            accDB.setType(newAccount.getType());
            accDB.setUpdatedAt(newAccount.getUpdatedAt());
            accDB.setBalance(newAccount.getBalance());
            accDB.setAssistantManagerId(newAccount.getAssistantManagerId());
            accDB.setClientId(newAccount.getClientId());
            accDB.setCurrencyCode(newAccount.getCurrencyCode());
            accDB.setMainManagerId(newAccount.getMainManagerId());
            accDB.setBlocked(newAccount.isBlocked());

            Account account = accountService.saveAccount(accDB);
        }
        return null;
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccountById(@PathVariable("accountId") UUID accountId){
        accountService.deleteAccountById(accountId);
    }
}
