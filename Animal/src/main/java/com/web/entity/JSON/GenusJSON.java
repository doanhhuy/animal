package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.GenusAPI;

import java.util.List;

/**
 * Created by duyle on 3/20/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenusJSON {
    private GenusAPI genus;
    private List<GenusAPI> list;

    public GenusAPI getGenus() {
        return genus;
    }

    public void setGenus(GenusAPI genus) {
        this.genus = genus;
    }

    public List<GenusAPI> getList() {
        return list;
    }

    public void setList(List<GenusAPI> list) {
        this.list = list;
    }
}
