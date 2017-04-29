package com.web.entity.api;

import java.sql.Date;

/**
 * Created by duyle on 17/02/2017.
 */
public class ClassAPI {
    private long id;
    private String notation;
    private String scienceName;
    private String vietnameseName;
    private String discovererName;
    private Date yearDiscover;
    private long idCreator;
    private String nameCreator;
    private long idPhylum;
    private String vietnamesePhylum;
    private String sciencePhylum;

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

    public long getIdPhylum() {
        return idPhylum;
    }

    public void setIdPhylum(long idPhylum) {
        this.idPhylum = idPhylum;
    }

    public String getNameCreator() {
        return nameCreator;
    }

    public void setNameCreator(String nameCreator) {
        this.nameCreator = nameCreator;
    }

    public String getVietnamesePhylum() {
        return vietnamesePhylum;
    }

    public void setVietnamesePhylum(String vietnamesePhylum) {
        this.vietnamesePhylum = vietnamesePhylum;
    }

    public String getSciencePhylum() {
        return sciencePhylum;
    }

    public void setSciencePhylum(String sciencePhylum) {
        this.sciencePhylum = sciencePhylum;
    }
}
