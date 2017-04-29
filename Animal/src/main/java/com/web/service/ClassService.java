package com.web.service;

import com.web.dao.IClassDAO;
import com.web.entity.backend.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
@Service
public class ClassService implements IClassService {
    @Autowired
    private IClassDAO classDAO;

    public List<Classes> getAllClasses() {
        return classDAO.getAllClasss();
    }

    public Classes getClassById(int id) {
        return classDAO.getClassById(id);
    }

    public int addClass(Classes classes) {
        return classDAO.addClass(classes);
    }

    public void updateClass(Classes classes) {
        classDAO.updateClass(classes);
    }

    public boolean isClassExistByClass(Classes classes) {
        if (classDAO.isClassExistByClass(classes)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Classes> getClassByPhylumId(int id) {
        return classDAO.getClassByPhylumId(id);
    }
}
