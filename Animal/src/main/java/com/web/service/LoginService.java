package com.web.service;

import com.web.dao.ILoginDAO;
import com.web.entity.backend.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by duyle on 01/03/2017.
 */

@Service
public class LoginService implements ILoginService {

    @Autowired
    ILoginDAO loginDAO;

    public Login getAccountByUser(String username) {
        return loginDAO.getAccountByUser(username);
    }

    public Login getAccountById(int id) {
        return loginDAO.getAccountById(id);
    }

    public Login getAccountByEmail(String email) {
        return loginDAO.getAccountByEmail(email);
    }

    public Login getAccountByUsername(String username) {
        return loginDAO.getAccountByUsername(username);
    }
}
