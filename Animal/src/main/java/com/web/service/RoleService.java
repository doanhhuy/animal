package com.web.service;

import com.web.dao.IRoleDAO;
import com.web.entity.backend.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duyle on 21/02/2017.
 */

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleDAO roleDAO;

    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }

    public int addRole(Role role) {
        return roleDAO.addRole(role);
    }

    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    public boolean isRoleExistByRole(Role role) {
        return roleDAO.isRoleExistByRole(role);
    }

    public Role getRoleByRoleName(String roleName) {
        return roleDAO.getRoleByRoleName(roleName);
    }
}
