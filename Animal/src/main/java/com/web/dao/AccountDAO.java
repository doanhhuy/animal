package com.web.dao;

import com.web.entity.backend.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by duyle on 14/02/2017.
 */
@Transactional
@Repository
public class AccountDAO implements IAccountDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Account> getAllAccounts() {
        String hql = "FROM Account as p ORDER BY p.id";

        hibernateTemplate.setMaxResults(0);
        return (List<Account>) hibernateTemplate.find(hql);
    }

    public Account getAccountByUserPassword(String username, String password) {
        String hql = "FROM Account as p WHERE p.username ='" + username + "' and p.password='" + password + "'";

        hibernateTemplate.setMaxResults(0);
        List<Account> accountList = (List<Account>) hibernateTemplate.find(hql);
        if (accountList.size() == 0) {
            return null;
        }
        return accountList.get(0);
    }

    public Account getAccountById(int id) {
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Account.class, id);
    }

    public Account getAccountByEmail(String email) {
        String hql = "FROM Account as p WHERE p.email ='" + email + "'";

        hibernateTemplate.setMaxResults(0);
        List<Account> accountList = (List<Account>) hibernateTemplate.find(hql);
        if (accountList.size() == 0) {
            return null;
        }
        return accountList.get(0);
    }

    public int addAccount(Account account) {
        return (Integer) hibernateTemplate.save(account);
    }

    public void updateAccount(Account account) {
        Account a = getAccountById(account.getId());
        a.setActivationCode(account.getActivationCode());
        a.setUpdateDate(account.getUpdateDate());
        a.setActivationDate(account.getActivationDate());
        a.setDetail(account.getDetail());
        a.setEmail(account.getEmail());
        hibernateTemplate.update(a);
    }

    public void changeAccount(Account account) {
        Account a = getAccountById(account.getId());
        a.setIdRole(account.getIdRole());
        hibernateTemplate.merge(a);
    }

    public void deleteAccount(int id) {
        hibernateTemplate.delete(getAccountById(id));
    }


    public void resetPassword(Account account) {
        Account a = getAccountById(account.getId());
        a.setActivationCode(account.getActivationCode());
        a.setStatus(0);
        hibernateTemplate.update(a);
    }

    public void updatePassword(Account account) {
        Account a = getAccountById(account.getId());
        a.setStatus(account.getStatus());
        a.setActivationCode(account.getActivationCode());
        a.setUpdateDate(account.getUpdateDate());
        a.setPassword(account.getPassword());
        hibernateTemplate.update(a);
    }

    public boolean isAccountByUsername(Account account) {
        String hql = "FROM Account Where username = '" + account.getUsername() + "'";

        hibernateTemplate.setMaxResults(0);
        List<Account> accountList = (List<Account>) hibernateTemplate.find(hql);
        if (accountList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isAccountByEmail(Account account) {
        String hql = "FROM Account Where email = '" + account.getEmail() + "'";

        hibernateTemplate.setMaxResults(0);
        List<Account> accountList = (List<Account>) hibernateTemplate.find(hql);
        if (accountList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isAccountExistByAccount(Account account) {
        String hql = "FROM Account Where username = '" + account.getUsername() + "' email = '" + account.getEmail() + "'";

        hibernateTemplate.setMaxResults(0);
        List<Account> accountList = (List<Account>) hibernateTemplate.find(hql);
        if (accountList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Account getAccountByUsername(String username) {
        String hql = "FROM Account as p WHERE p.username ='" + username + "'";

        hibernateTemplate.setMaxResults(0);
        List<Account> accountList = (List<Account>) hibernateTemplate.find(hql);
        if (accountList.size() == 0) {
            return null;
        } else {
            return accountList.get(0);
        }
    }

    public boolean checkPasswordById(int id, String oldPassword) {
        String hql = "FROM Account Where password = '" + oldPassword + "' AND id = " + id;

        hibernateTemplate.setMaxResults(0);
        List<Account> accountList = (List<Account>) hibernateTemplate.find(hql);
        if (accountList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void changePassword(Account account) {
        Account a = getAccountById(account.getId());
        a.setPassword(account.getPassword());
        hibernateTemplate.merge(a);
    }

    public boolean checkEmail(int id, String email) {
        String hql = "FROM Account Where email = '" + email + "' AND id <> " + id;
        hibernateTemplate.setMaxResults(0);
        List<Account> accountList = (List<Account>) hibernateTemplate.find(hql);
        if (accountList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Account registerAccount(Account account) {
        int id = (Integer) hibernateTemplate.save(account);
        return getAccountById(id);
    }

    public void lockAccount(Account account) {
        Account a = getAccountById(account.getId());
        a.setStatus(account.getStatus());
        a.setDetail(account.getDetail());
        hibernateTemplate.update(a);
    }
}
