package com.web.dao;

import com.web.entity.backend.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by duyle on 01/03/2017.
 */

@Transactional
@Repository
public class LoginDAO implements ILoginDAO {

    @Autowired
    HibernateTemplate hibernateTemplate;

    public Login getAccountByUser(String username) {
        String hql = "FROM Login as p WHERE p.username ='" + username + "' and p.status = 1";
        
        hibernateTemplate.setMaxResults(0);
        List<Login> loginList = (List<Login>) hibernateTemplate.find(hql);
        if (loginList.size() == 0) {
            return null;
        }
        return loginList.get(0);
    }

    public Login getAccountById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Login.class, id);
    }

    public Login getAccountByEmail(String email) {
        String hql = "FROM Login as p WHERE p.email ='" + email + "'";
        
        hibernateTemplate.setMaxResults(0);
        List<Login> loginList = (List<Login>) hibernateTemplate.find(hql);
        if (loginList.size() == 0) {
            return null;
        }
        return loginList.get(0);
    }

    public Login getAccountByUsername(String username) {
        String hql = "FROM Login as p WHERE p.username ='" + username + "'";
        
        hibernateTemplate.setMaxResults(0);
        List<Login> loginList = (List<Login>) hibernateTemplate.find(hql);
        if (loginList.size() == 0) {
            return null;
        }
        return loginList.get(0);
    }
}
