package com.web.dao;

import com.web.entity.backend.Classes;

import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
public interface IClassDAO {

    List<Classes> getAllClasss();

    Classes getClassById(int id);

    int addClass(Classes classes);

    void updateClass(Classes classes);

    boolean isClassExistByClass(Classes classes);

    List<Classes> getClassByPhylumId(int id);
}
