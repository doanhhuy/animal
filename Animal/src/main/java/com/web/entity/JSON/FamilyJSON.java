package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.FamilyAPI;

import java.util.List;

/**
 * Created by duyle on 3/20/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilyJSON {

    private FamilyAPI family;
    private List<FamilyAPI> list;

    public FamilyAPI getFamily() {
        return family;
    }

    public void setFamily(FamilyAPI family) {
        this.family = family;
    }

    public List<FamilyAPI> getList() {
        return list;
    }

    public void setList(List<FamilyAPI> list) {
        this.list = list;
    }

}
