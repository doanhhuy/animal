package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.PhylumAPI;

import java.util.List;

/**
 * Created by duyle on 20/02/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhylumJSON {

    private PhylumAPI phylum;
    private List<PhylumAPI> list;

    public PhylumAPI getPhylum() {
        return phylum;
    }

    public void setPhylum(PhylumAPI phylum) {
        this.phylum = phylum;
    }

    public List<PhylumAPI> getList() {
        return list;
    }

    public void setList(List<PhylumAPI> list) {
        this.list = list;
    }
}
