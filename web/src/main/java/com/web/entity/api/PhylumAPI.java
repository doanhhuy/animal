package com.web.entity.api;

import java.sql.Date;

/**
 * Created by duyle on 16/02/2017.
 */
public class PhylumAPI {
    private long id;
    private String notation;
    private long idKingdom;
    private String scienceName;
    private String vietnameseName;
    private String discovererName;
    private Date yearDiscover;
    private long idCreator;
    private String nameCreator;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public long getIdKingdom() {
        return idKingdom;
    }

    public void setIdKingdom(long idKingdom) {
        this.idKingdom = idKingdom;
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

    public long getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(long idCreator) {
        this.idCreator = idCreator;
    }

    public String getNameCreator() {
        return nameCreator;
    }

    public void setNameCreator(String nameCreator) {
        this.nameCreator = nameCreator;
    }
}
