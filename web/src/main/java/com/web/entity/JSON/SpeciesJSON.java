package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.SpeciesAPI;

import java.util.List;

/**
 * Created by duyle on 20/02/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpeciesJSON {

    private SpeciesAPI species;
    private List<SpeciesAPI> specieses;

    public SpeciesAPI getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesAPI species) {
        this.species = species;
    }

    public List<SpeciesAPI> getSpecieses() {
        return specieses;
    }

    public void setSpecieses(List<SpeciesAPI> specieses) {
        this.specieses = specieses;
    }
}
