package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.ClassAPI;

import java.util.List;

/**
 * Created by duyle on 3/20/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassJSON {

    private ClassAPI classes;
    private List<ClassAPI> list;

    public ClassAPI getClasses() {
        return classes;
    }

    public void setClasses(ClassAPI classes) {
        this.classes = classes;
    }

    public List<ClassAPI> getList() {
        return list;
    }

    public void setList(List<ClassAPI> list) {
        this.list = list;
    }
}
