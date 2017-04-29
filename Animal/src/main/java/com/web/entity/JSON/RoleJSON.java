package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.RoleAPI;

import java.util.List;

/**
 * Created by duyle on 21/02/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleJSON {

    private RoleAPI role;
    private List<RoleAPI> roles;

    public RoleAPI getRole() {
        return role;
    }

    public void setRole(RoleAPI role) {
        this.role = role;
    }

    public List<RoleAPI> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleAPI> roles) {
        this.roles = roles;
    }
}
