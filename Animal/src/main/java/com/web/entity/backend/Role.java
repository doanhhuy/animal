package com.web.entity.backend;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by duyle on 14/02/2017.
 */
@Entity
@Table(name = "roles", schema = "public")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "rolename")
    private String roleName;
    @Column(name = "detail")
    private String detail;
    @OneToMany(mappedBy = "idRole")
    private Collection<Account> accountByIdRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Collection<Account> getAccountByIdRole() {
        return accountByIdRole;
    }

    public void setAccountByIdRole(Collection<Account> accountById) {
        this.accountByIdRole = accountById;
    }
}
