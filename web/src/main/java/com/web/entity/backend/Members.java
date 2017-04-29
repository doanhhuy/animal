package com.web.entity.backend;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by duyle on 15/02/2017.
 */

@Entity
@Table(name = "member", schema = "public")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Members implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "detail")
    private String detail;
    @Column(name = "address")
    private String address;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @OneToMany(mappedBy = "idMembers")
    private Collection<Account> accountByIdMember;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<Account> getAccountByIdMember() {
        return accountByIdMember;
    }

    public void setAccountByIdMember(Collection<Account> accountById) {
        this.accountByIdMember = accountById;
    }
}
