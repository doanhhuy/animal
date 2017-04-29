package com.web.entity.api;


import com.web.bean.FileInfo;
import com.web.bean.Location;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by duyle on 17/02/2017.
 */
public class SpeciesAPI {

    private int id;
    private String notation;
    private String scienceName;
    private String vietnameseName;
    private String otherName;
    private String individualQuantity;
    private String reproductionTraits;
    private String sexualTraits;
    private String ortherTraits;
    private String alertlevel;
    private String discovererName;
    private String biologicalBehavior;
    private String mediumSize;
    private String food;
    private String origin;
    private String image;
    private Date yearDiscover;
    private int status;
    private String dateUpdate;
    private Date dateCreate;
    private int idGenus;
    private String scienceNameGenus;
    private String vietnameseNameGenus;
    private int idFamily;
    private String vietnameseNameFamily;
    private int idOrder;
    private int idClass;
    private int idPhylum;
    private double longitude;
    private double latitude;
    private int idCreator;
    private String nameCreator;
    private int idChecker;
    private String nameChecker;
    private FileInfo imageFile;
    private String locationName;

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

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getIndividualQuantity() {
        return individualQuantity;
    }

    public void setIndividualQuantity(String individualQuantity) {
        this.individualQuantity = individualQuantity;
    }

    public String getReproductionTraits() {
        return reproductionTraits;
    }

    public void setReproductionTraits(String reproductionTraits) {
        this.reproductionTraits = reproductionTraits;
    }

    public String getSexualTraits() {
        return sexualTraits;
    }

    public void setSexualTraits(String sexualTraits) {
        this.sexualTraits = sexualTraits;
    }

    public String getOrtherTraits() {
        return ortherTraits;
    }

    public void setOrtherTraits(String ortherTraits) {
        this.ortherTraits = ortherTraits;
    }

    public String getAlertlevel() {
        return alertlevel;
    }

    public void setAlertlevel(String alertlevel) {
        this.alertlevel = alertlevel;
    }

    public String getDiscovererName() {
        return discovererName;
    }

    public void setDiscovererName(String discovererName) {
        this.discovererName = discovererName;
    }

    public String getBiologicalBehavior() {
        return biologicalBehavior;
    }

    public void setBiologicalBehavior(String biologicalBehavior) {
        this.biologicalBehavior = biologicalBehavior;
    }

    public String getMediumSize() {
        return mediumSize;
    }

    public void setMediumSize(String mediumSize) {
        this.mediumSize = mediumSize;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getIdGenus() {
        return idGenus;
    }

    public void setIdGenus(int idGenus) {
        this.idGenus = idGenus;
    }

    public String getScienceNameGenus() {
        return scienceNameGenus;
    }

    public void setScienceNameGenus(String scienceNameGenus) {
        this.scienceNameGenus = scienceNameGenus;
    }

    public String getVietnameseNameGenus() {
        return vietnameseNameGenus;
    }

    public void setVietnameseNameGenus(String vietnameseNameGenus) {
        this.vietnameseNameGenus = vietnameseNameGenus;
    }

    public void setVietnameseNameFamily(String vietnameseNameFamily) {
        this.vietnameseNameFamily = vietnameseNameFamily;
    }

    public String getVietnameseNameFamily() {
        return vietnameseNameFamily;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public FileInfo getImageFile() {
        return imageFile;
    }

    public void setImageFile(FileInfo imageFile) {
        this.imageFile = imageFile;
    }

    public int getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(int idFamily) {
        this.idFamily = idFamily;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdPhylum() {
        return idPhylum;
    }

    public void setIdPhylum(int idPhylum) {
        this.idPhylum = idPhylum;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
