package com.web.entity.api;

import java.sql.Date;

/**
 * Created by duyle on 17/02/2017.
 */
public class GenusAPI {

    private long id;
    private String notation;
    private String scienceName;
    private String vietnameseName;
    private String discovererName;
    private Date yearDiscover;
    private long status;
    private Date dateUpdate;
    private Date dateCreate;
    private long idFamily;
    private String scienceNameFamily;
    private String vietnameseNameFamily;
    private long idCreator;
    private String nameCreator;
    private long idChecker;
    private String nameChecker;


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

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
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

    public long getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(long idFamily) {
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

    public long getIdChecker() {
        return idChecker;
    }

    public void setIdChecker(long idChecker) {
        this.idChecker = idChecker;
    }

    public String getNameChecker() {
        return nameChecker;
    }

    public void setNameChecker(String nameChecker) {
        this.nameChecker = nameChecker;
    }
}
