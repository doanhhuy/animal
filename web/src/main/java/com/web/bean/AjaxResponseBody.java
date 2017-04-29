package com.web.bean;

import com.web.entity.api.SpeciesAPI;

import java.util.List;

/**
 * Created by duyle on 3/16/17.
 */
public class AjaxResponseBody {

    private List<SpeciesAPI> speciesAPIList;

    public List<SpeciesAPI> getSpeciesAPIList() {
        return speciesAPIList;
    }

    public void setSpeciesAPIList(List<SpeciesAPI> speciesAPIList) {
        this.speciesAPIList = speciesAPIList;
    }
}
