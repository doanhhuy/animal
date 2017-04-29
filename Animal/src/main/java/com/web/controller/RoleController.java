package com.web.controller;

import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.entity.JSON.RoleJSON;
import com.web.entity.api.RoleAPI;
import com.web.entity.backend.Role;
import com.web.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 21/02/2017.
 */
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/api/roles", method = RequestMethod.GET)
    public ResponseEntity<RoleJSON> getAllRoles() {
        List<Role> roleList = roleService.getAllRoles();

        if (roleList.isEmpty()) {
            return new ResponseEntity<RoleJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<RoleAPI> roleAPIS = new ArrayList<RoleAPI>();
            RoleAPI roleAPI;
            for (Role p : roleList) {
                roleAPI = new RoleAPI();
                roleAPI.setId(p.getId());
                roleAPI.setDetail(p.getDetail());
                roleAPI.setRoleName(p.getRoleName());
                roleAPIS.add(roleAPI);
            }

            RoleJSON roleJSON = new RoleJSON();
            roleJSON.setRoles(roleAPIS);
            return new ResponseEntity<RoleJSON>(roleJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/roles/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoleJSON> getRoleById(@PathVariable("id") int id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            return new ResponseEntity<RoleJSON>(HttpStatus.NO_CONTENT);
        } else {
            RoleAPI roleAPI = new RoleAPI();
            roleAPI.setId(role.getId());
            roleAPI.setDetail(role.getDetail());
            roleAPI.setRoleName(role.getRoleName());
            RoleJSON roleJSON = new RoleJSON();
            roleJSON.setRole(roleAPI);
            return new ResponseEntity<RoleJSON>(roleJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/roles", method = RequestMethod.POST)
    public ResponseEntity<RoleJSON> addRole(@RequestBody RoleAPI roleAPI) throws ErrorException {
        Role role = new Role();
        role.setRoleName(roleAPI.getRoleName());
        role.setDetail(roleAPI.getDetail());
        if (roleService.getRoleByRoleName(roleAPI.getRoleName()) != null) {
            throw new ErrorException("Role này đã tồn tại!");
        } else {
            int result = roleService.addRole(role);
            if (result == 0) {
                throw new ErrorException("Thêm Role không thành công!");
            } else {
                RoleAPI roleAPI1 = new RoleAPI();
                Role role1 = roleService.getRoleById(result);
                roleAPI1.setId(role1.getId());
                roleAPI1.setRoleName(role1.getRoleName());
                roleAPI1.setDetail(role1.getDetail());
                RoleJSON roleJSON = new RoleJSON();
                roleJSON.setRole(roleAPI1);
                return new ResponseEntity<RoleJSON>(roleJSON, HttpStatus.OK);
            }
        }

    }

    @RequestMapping(value = "/api/roles/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RoleJSON> updateRole(@PathVariable("id") int id, @RequestBody RoleAPI roleAPI) throws ErrorException {
        Role role = new Role();
        role.setId(id);
        role.setRoleName(roleAPI.getRoleName());
        role.setDetail(roleAPI.getDetail());
        if (roleService.isRoleExistByRole(role)) {
            throw new ErrorException("Tên Role này đã tồn tại!");
        } else {
            roleService.updateRole(role);

            RoleAPI roleAPI1 = new RoleAPI();
            Role role1 = roleService.getRoleById(id);
            roleAPI1.setId(role1.getId());
            roleAPI1.setRoleName(role1.getRoleName());
            roleAPI1.setDetail(role1.getDetail());
            RoleJSON roleJSON = new RoleJSON();
            roleJSON.setRole(roleAPI1);
            return new ResponseEntity<RoleJSON>(roleJSON, HttpStatus.OK);
        }
    }

    /*
   Function return error message by JSON
    */
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }

}
