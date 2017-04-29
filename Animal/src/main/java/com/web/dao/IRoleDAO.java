package com.web.dao;

import com.web.entity.backend.Role;

import java.util.List;

/**
 * Created by duyle on 21/02/2017.
 */
public interface IRoleDAO {

    List<Role> getAllRoles();

    Role getRoleById(int id);

    int addRole(Role role);

    void updateRole(Role role);

    boolean isRoleExistByRole(Role role);

    Role getRoleByRoleName(String roleName);
}
