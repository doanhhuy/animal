package com.web.service;

import com.web.entity.backend.Account;

import java.util.List;

/**
 * Created by duyle on 14/02/2017.
 */
public interface IAccountService {

    List<Account> getAllAccounts();

    Account getAccountByUserPassword(String username, String password);

    Account getAccountByEmail(String email);

    Account getAccountById(int id);

    int addAccount(Account account);

    void updateAccount(Account account);

    void resetPassword(Account account);

    void changeRole(Account account);

    void deleteAccount(int id);

    boolean isAccountByUsername(Account account);

    boolean isAccountByEmail(Account account);

    boolean isAccountExistByAccount(Account account);

    Account getAccountByUser(String username);

    boolean checkPasswordById(int id, String oldPassword);

    boolean checkEmail(int id, String email);

    void changePassword(Account account);

    Account registerAccount(Account account);

    void updatePassword(Account account);

    void lockAccount(Account account);
}
