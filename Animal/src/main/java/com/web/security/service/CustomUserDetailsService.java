package com.web.security.service;

import com.web.dao.IAccountDAO;
import com.web.entity.backend.Account;
import com.web.entity.backend.Login;
import com.web.entity.backend.Role;
import com.web.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 24/02/2017.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ILoginService loginService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Login login = loginService.getAccountByUser(username);
        return new User(login.getUsername(), login.getPassword(), true,
                true, true, true, getGrantedAuthorities(login));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Login login) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + login.getRoleName()));
        return grantedAuthorities;
    }
}
