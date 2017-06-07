package com.web.entity.api;


/**
 * Created by duyle on 23/02/2017.
 */
public class HabitatAPI {

    private long id;
    private String locationName;
    private double latitude;
    private double longitude;
    private int idCreator;
    private String nameCreator;
    private int idChecker;
    private String nameChecker;
//    private Point geom;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public String getNameChecker() {
        return nameChecker;
    }

    public void setNameChecker(String nameChecker) {
        this.nameChecker = nameChecker;
    }

    public int getIdChecker() {
        return idChecker;
    }

    public void setIdChecker(int idChecker) {
        this.idChecker = idChecker;
    }

//    public Point getGeom() {
//        return geom;
//    }
//
//    public void setGeom(Point geom) {
//        this.geom = geom;
//    }
}
