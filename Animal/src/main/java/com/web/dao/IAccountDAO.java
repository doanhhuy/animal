package com.web.dao;

import com.web.entity.backend.Account;

import java.util.List;

/**
 * Created by duyle on 14/02/2017.
 */
public interface IAccountDAO {

    List<Account> getAllAccounts();

    Account getAccountByUserPassword(String username, String password);

    Account getAccountById(int id);

    Account getAccountByEmail(String email);

    int addAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(int id);

    boolean isAccountByUsername(Account account);

    boolean isAccountByEmail(Account account);

    boolean isAccountExistByAccount(Account account);

    void changeAccount(Account account);

    Account getAccountByUsername(String username);

    void resetPassword(Account account);

    boolean checkPasswordById(int id, String oldPassword);

    boolean checkEmail(int id, String email);

    Account registerAccount(Account account);

    void changePassword(Account account);

    void updatePassword(Account account);

    void lockAccount(Account account);
}
