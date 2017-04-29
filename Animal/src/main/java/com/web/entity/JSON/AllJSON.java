package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.*;
import com.web.entity.backend.Phylum;

import java.util.List;

/**
 * Created by duyle on 3/22/17.
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AllJSON {

    private String maxDate;
    private List<PhylumAPI> phylumList;
    private List<ClassAPI> classList;
    private List<OrderAPI> orderList;
    private List<FamilyAPI> familyList;
    private List<GenusAPI> genusList;

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public List<PhylumAPI> getPhylumList() {
        return phylumList;
    }

    public void setPhylumList(List<PhylumAPI> phylumList) {
        this.phylumList = phylumList;
    }

    public List<ClassAPI> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassAPI> classList) {
        this.classList = classList;
    }

    public List<OrderAPI> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderAPI> orderList) {
        this.orderList = orderList;
    }

    public List<FamilyAPI> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<FamilyAPI> familyList) {
        this.familyList = familyList;
    }

    public List<GenusAPI> getGenusList() {
        return genusList;
    }

    public void setGenusList(List<GenusAPI> genusList) {
        this.genusList = genusList;
    }
}
