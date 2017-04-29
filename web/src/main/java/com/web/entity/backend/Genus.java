package com.web.entity.backend;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by duyle on 17/02/2017.
 */

@Entity
@Table(name = "genus", schema = "public")
public class Genus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "notation")
    private String notation;
    @Column(name = "sciencename")
    private String scienceName;
    @Column(name = "vietnamesename")
    private String vietnameseName;
    @Column(name = "discoverername")
    private String discovererName;
    @Column(name = "yeardiscover")
    private Date yearDiscover;
    @Column(name = "status")
    private int status;
    @Column(name = "dateupdate")
    private Date dateUpdate;
    @Column(name = "datecreate")
    private Date dateCreate;
    @ManyToOne
    @JoinColumn(name = "idfamily", referencedColumnName = "id")
    private Families idFamily;
    @ManyToOne
    @JoinColumn(name = "idcreator", referencedColumnName = "id")
    private Account idCreator;
    @ManyToOne
    @JoinColumn(name = "idchecker", referencedColumnName = "id")
    private Account idChecker;
    @OneToMany(mappedBy = "idGenus", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Collection<Species> speciesByIdGenus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getScienceName() {
        return scienceName;
    }

    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public String getDiscovererName() {
        return discovererName;
    }

    public void setDiscovererName(String discovererName) {
        this.discovererName = discovererName;
    }

    public Date getYearDiscover() {
        return yearDiscover;
    }

    public void setYearDiscover(Date yearDiscover) {
        this.yearDiscover = yearDiscover;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Families getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(Families idFamily) {
        this.idFamily = idFamily;
    }

    public Account getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(Account idCreator) {
        this.idCreator = idCreator;
    }

    public Account getIdChecker() {
        return idChecker;
    }

    public void setIdChecker(Account idChecker) {
        this.idChecker = idChecker;
    }

    public Collection<Species> getSpeciesByIdGenus() {
        return speciesByIdGenus;
    }

    public void setSpeciesByIdGenus(Collection<Species> speciesByIdGenus) {
        this.speciesByIdGenus = speciesByIdGenus;
    }
}
