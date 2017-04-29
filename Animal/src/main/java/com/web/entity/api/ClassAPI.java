package com.web.entity.api;

import java.sql.Date;

/**
 * Created by duyle on 17/02/2017.
 */
public class ClassAPI {
    private int id;
    private String notation;
    private String scienceName;
    private String vietnameseName;
    private String discovererName;
    private Date yearDiscover;
    private int idCreator;
    private String nameCreator;
    private int idPhylum;
    private String vietnamesePhylum;
    private String sciencePhylum;

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

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }

    public int getIdPhylum() {
        return idPhylum;
    }

    public void setIdPhylum(int idPhylum) {
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
