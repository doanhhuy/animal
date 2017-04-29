package com.web.dao;

import com.web.entity.backend.Login;

/**
 * Created by duyle on 01/03/2017.
 */
public interface ILoginDAO {

    Login getAccountByUser(String username);

    Login getAccountById(int id);

    Login getAccountByEmail(String email);

    Login getAccountByUsername(String username);

}
