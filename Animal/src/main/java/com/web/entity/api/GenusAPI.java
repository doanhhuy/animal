package com.web.entity.api;

import java.sql.Date;

/**
 * Created by duyle on 17/02/2017.
 */
public class GenusAPI {

    private int id;
    private String notation;
    private String scienceName;
    private String vietnameseName;
    private String discovererName;
    private Date yearDiscover;
    private int status;
    private String dateUpdate;
    private Date dateCreate;
    private int idFamily;
    private String scienceNameFamily;
    private String vietnameseNameFamily;
    private int idCreator;
    private String nameCreator;
    private int idChecker;
    private String nameChecker;


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

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(int idFamily) {
        this.idFamily = idFamily;
    }

    public String getScienceNameFamily() {
        return scienceNameFamily;
    }

    public void setScienceNameFamily(String scienceNameFamily) {
        this.scienceNameFamily = scienceNameFamily;
    }

    public String getVietnameseNameFamily() {
        return vietnameseNameFamily;
    }

    public void setVietnameseNameFamily(String vietnameseNameFamily) {
        this.vietnameseNameFamily = vietnameseNameFamily;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }

    public String getNameCreator() {
        return nameCreator;
    }

    public void setNameCreator(String nameCreator) {
        this.nameCreator = nameCreator;
    }

    public int getIdChecker() {
        return idChecker;
    }

    public void setIdChecker(int idChecker) {
        this.idChecker = idChecker;
    }

    public String getNameChecker() {
        return nameChecker;
    }

    public void setNameChecker(String nameChecker) {
        this.nameChecker = nameChecker;
    }
}
