package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.PhylumAPI;

import java.util.List;

/**
 * Created by duyle on 20/02/2017.
 */
public class PhylumJSON {

    private PhylumAPI phylum;
    private List<PhylumAPI> phylums;

    public PhylumAPI getPhylum() {
        return phylum;
    }

    public void setPhylum(PhylumAPI phylum) {
        this.phylum = phylum;
    }

    public List<PhylumAPI> getPhylums() {
        return phylums;
    }

    public void setPhylums(List<PhylumAPI> phylums) {
        this.phylums = phylums;
    }
}
