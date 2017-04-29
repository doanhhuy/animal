package com.web.entity.backend;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by duyle on 14/02/2017.
 */

@Entity
@Table(name = "account", schema = "public")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "status")
    private int status;
    @Column(name = "activationcode")
    private String activationCode;
    @Column(name = "activationdate")
    private Date activationDate;
    @Column(name = "updatedate")
    private Date updateDate;
    @Column(name = "detail")
    private String detail;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "idrole", referencedColumnName = "id")
    private Role idRole;
    @ManyToOne
    @JoinColumn(name = "idmember", referencedColumnName = "id")
    private Members idMembers;
    @OneToMany(mappedBy = "idCreator")
    private Collection<Phylum> phylumByIdAccount;
    @OneToMany(mappedBy = "idCreator")
    private Collection<Classes> classesByIdAccount;
    @OneToMany(mappedBy = "idCreator")
    private Collection<Orders> odrerCreatorByIdAccount;
    @OneToMany(mappedBy = "idChecker")
    private Collection<Orders> odrerCheckerByIdAccount;
    @OneToMany(mappedBy = "idCreator")
    private Collection<Families> familyCreatorByIdAccount;
    @OneToMany(mappedBy = "idChecker")
    private Collection<Families> familyCheckerByIdAccount;
    @OneToMany(mappedBy = "idCreator")
    private Collection<Genus> genusCreatorByIdAccount;
    @OneToMany(mappedBy = "idChecker")
    private Collection<Genus> genusCheckerByIdAccount;
    @OneToMany(mappedBy = "idCreator")
    private Collection<Habitat> habitatCreatorByIdAccount;
    @OneToMany(mappedBy = "idChecker")
    private Collection<Habitat> habitatCheckerByIdAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public Members getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(Members idMembers) {
        this.idMembers = idMembers;
    }

    public Collection<Phylum> getPhylumByIdAccount() {
        return phylumByIdAccount;
    }

    public void setPhylumByIdAccount(Collection<Phylum> phylumByIdAccount) {
        this.phylumByIdAccount = phylumByIdAccount;
    }

    public Collection<Classes> getClassesByIdAccount() {
        return classesByIdAccount;
    }

    public void setClassesByIdAccount(Collection<Classes> classesByIdAccount) {
        this.classesByIdAccount = classesByIdAccount;
    }

    public Collection<Orders> getOdrerCreatorByIdAccount() {
        return odrerCreatorByIdAccount;
    }

    public void setOdrerCreatorByIdAccount(Collection<Orders> odrerCreatorByIdAccount) {
        this.odrerCreatorByIdAccount = odrerCreatorByIdAccount;
    }

    public Collection<Orders> getOdrerCheckerByIdAccount() {
        return odrerCheckerByIdAccount;
    }

    public void setOdrerCheckerByIdAccount(Collection<Orders> odrerCheckerByIdAccount) {
        this.odrerCheckerByIdAccount = odrerCheckerByIdAccount;
    }

    public Collection<Families> getFamilyCreatorByIdAccount() {
        return familyCreatorByIdAccount;
    }

    public void setFamilyCreatorByIdAccount(Collection<Families> familyCreatorByIdAccount) {
        this.familyCreatorByIdAccount = familyCreatorByIdAccount;
    }

    public Collection<Families> getFamilyCheckerByIdAccount() {
        return familyCheckerByIdAccount;
    }

    public void setFamilyCheckerByIdAccount(Collection<Families> familyCheckerByIdAccount) {
        this.familyCheckerByIdAccount = familyCheckerByIdAccount;
    }

    public Collection<Genus> getGenusCreatorByIdAccount() {
        return genusCreatorByIdAccount;
    }

    public void setGenusCreatorByIdAccount(Collection<Genus> genusCreatorByIdAccount) {
        this.genusCreatorByIdAccount = genusCreatorByIdAccount;
    }

    public Collection<Genus> getGenusCheckerByIdAccount() {
        return genusCheckerByIdAccount;
    }

    public void setGenusCheckerByIdAccount(Collection<Genus> genusCheckerByIdAccount) {
        this.genusCheckerByIdAccount = genusCheckerByIdAccount;
    }

    public Collection<Habitat> getHabitatCreatorByIdAccount() {
        return habitatCreatorByIdAccount;
    }

    public void setHabitatCreatorByIdAccount(Collection<Habitat> habitatCreatorByIdAccount) {
        this.habitatCreatorByIdAccount = habitatCreatorByIdAccount;
    }

    public Collection<Habitat> getHabitatCheckerByIdAccount() {
        return habitatCheckerByIdAccount;
    }

    public void setHabitatCheckerByIdAccount(Collection<Habitat> habitatCheckerByIdAccount) {
        this.habitatCheckerByIdAccount = habitatCheckerByIdAccount;
    }
}
