package com.web.entity.backend;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by duyle on 16/02/2017.
 */
@Entity
@Table(name = "class", schema = "public")
public class Classes implements Serializable{
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
    @ManyToOne
    @JoinColumn(name = "idcreator", referencedColumnName = "id")
    private Account idCreator;
    @ManyToOne
    @JoinColumn(name = "idphylum", referencedColumnName = "id")
    private Phylum idPhylum;
    @OneToMany(mappedBy = "idClass")
    private Collection<Orders> orderByIdClass;

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

    public Account getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(Account idCreator) {
        this.idCreator = idCreator;
    }

    public Phylum getIdPhylum() {
        return idPhylum;
    }

    public void setIdPhylum(Phylum idPhylum) {
        this.idPhylum = idPhylum;
    }

    public Collection<Orders> getOrderByIdClass() {
        return orderByIdClass;
    }

    public void setOrderByIdClass(Collection<Orders> orderByIdClass) {
        this.orderByIdClass = orderByIdClass;
    }
}
