package com.web.entity.backend;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by duyle on 17/02/2017.
 */

@Entity
@Table(name = "species", schema = "public")
public class Species {

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
    @Column(name = "othername")
    private String otherName;
    @Column(name = "individualquantity")
    private String individualQuantity;
    @Column(name = "reproductiontraits")
    private String reproductionTraits;
    @Column(name = "sexualtraits")
    private String sexualTraits;
    @Column(name = "orthertraits")
    private String ortherTraits;
    @Column(name = "alertlevel")
    private String alertLevel;
    @Column(name = "discoverername")
    private String discovererName;
    @Column(name = "biologicalbehavior")
    private String biologicalBehavior;
    @Column(name = "mediumsize")
    private String mediumSize;
    @Column(name = "food")
    private String food;
    @Column(name = "origin")
    private String origin;
    @Column(name = "image")
    private String image;
    @Column(name = "yeardiscover")
    private Date yearDiscover;
    @Column(name = "status")
    private int status;
    @Column(name = "dateupdate")
    private Timestamp dateUpdate;
    @Column(name = "datecreate")
    private Date dateCreate;
    @Column(name = "type")
    private String type;
    @Column(name = "color")
    private String color;
    @ManyToOne
    @JoinColumn(name = "idgenus", referencedColumnName = "id")
    private Genus idGenus;
    @ManyToOne
    @JoinColumn(name = "idcreator", referencedColumnName = "id")
    private Account idCreator;
    @ManyToOne
    @JoinColumn(name = "idchecker", referencedColumnName = "id")
    private Account idChecker;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "distribution",
            joinColumns = @JoinColumn(name = "idspecies", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idhabitat", referencedColumnName = "id"))
    private Collection<Habitat> habitatByIdSpecies;


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
        return alertLevel;
    }

    public void setAlertlevel(String alertlevel) {
        this.alertLevel = alertlevel;
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

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Genus getIdGenus() {
        return idGenus;
    }

    public void setIdGenus(Genus idGenus) {
        this.idGenus = idGenus;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Collection<Habitat> getHabitatByIdSpecies() {
        return habitatByIdSpecies;
    }

    public void setHabitatByIdSpecies(Collection<Habitat> habitatByIdSpecies) {
        this.habitatByIdSpecies = habitatByIdSpecies;
    }
}
