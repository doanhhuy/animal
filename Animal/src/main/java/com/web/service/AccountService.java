package com.web.service;

import com.web.dao.IAccountDAO;
import com.web.entity.backend.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duyle on 14/02/2017.
 */
@Service
public class AccountService implements IAccountService{

    @Autowired
    private IAccountDAO accountDAO;

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public Account getAccountByUserPassword(String username, String password) {
        Account account = accountDAO.getAccountByUserPassword(username, password);
        if(account == null){
            return null;
        }
        return account;
    }

    public Account getAccountByEmail(String email) {
        Account account = accountDAO.getAccountByEmail(email);
        if(account == null){
            return null;
        }
        return account;
    }

    public Account getAccountById(int id) {
        return accountDAO.getAccountById(id);
    }

    public Account getAccountByUser(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    public int addAccount(Account account) {
        return accountDAO.addAccount(account);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    public void resetPassword(Account account) {
        accountDAO.resetPassword(account);
    }

    public void changeRole(Account account) {
        accountDAO.changeAccount(account);
    }

    public void deleteAccount(int id) {
        accountDAO.deleteAccount(id);
    }

    public boolean isAccountByUsername(Account account) {
        return accountDAO.isAccountByUsername(account);
    }

    public boolean isAccountByEmail(Account account) {
        return accountDAO.isAccountByEmail(account);
    }

    public boolean isAccountExistByAccount(Account account) { return  accountDAO.isAccountExistByAccount(account);}

    public boolean checkPasswordById(int id, String oldPassword) {
        return accountDAO.checkPasswordById(id, oldPassword);
    }

    public boolean checkEmail(int id, String email) {
        return accountDAO.checkEmail(id, email);
    }

    public Account registerAccount(Account account) {
        return accountDAO.registerAccount(account);
    }

    public void changePassword(Account account) {
        accountDAO.changePassword(account);
    }

    public void updatePassword(Account account) {
        accountDAO.updatePassword(account);
    }

    public void lockAccount(Account account) {
        accountDAO.lockAccount(account);
    }
}
