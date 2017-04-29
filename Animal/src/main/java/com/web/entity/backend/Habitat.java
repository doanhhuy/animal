package com.web.entity.backend;

import javax.persistence.*;
import java.util.*;

/**
 * Created by duyle on 22/02/2017.
 */

@Entity
@Table(name = "habitat", schema = "public")
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "locationname")
    private String locationName;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @ManyToOne
    @JoinColumn(name = "idcreator", referencedColumnName = "id")
    private Account idCreator;
    @ManyToOne
    @JoinColumn(name = "idchecker", referencedColumnName = "id")
    private Account idChecker;
    @ManyToMany(mappedBy = "habitatByIdSpecies", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    private Collection<Species> speciesByIdHabitat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Collection<Species> getSpeciesByIdHabitat() {
        return speciesByIdHabitat;
    }

    public void setSpeciesByIdHabitat(Collection<Species> speciesByIdHabitat) {
        this.speciesByIdHabitat = speciesByIdHabitat;
    }
}
