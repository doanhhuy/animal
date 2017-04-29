package com.web.entity.api;

import com.web.bean.FileInfo;
import com.web.bean.Location;

/**
 * Created by duyle on 4/2/17.
 */
public class SpeciesShareAPI {

    private String vietnameseName;
    private String scienceName;
    private int idGenus;
    private String reproductionTraits;
    private String sexualTraits;
    private String ortherTraits;
    private String biologicalBehavior;
    private String mediumSize;
    private String food;
    private String locationName;
    private double longitude;
    private double latitude;
    private String fileName;
    private String encodeString;

    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public String getScienceName() {
        return scienceName;
    }

    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    public int getIdGenus() {
        return idGenus;
    }

    public void setIdGenus(int idGenus) {
        this.idGenus = idGenus;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}
