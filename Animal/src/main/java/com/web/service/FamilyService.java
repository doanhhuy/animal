package com.web.service;

import com.web.dao.IFamilyDAO;
import com.web.entity.backend.Families;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
@Service
public class FamilyService implements IFamilyService{

    @Autowired
    private IFamilyDAO familyDAO;

    public List<Families> getAllFamilies() {
        return familyDAO.getAllFamilies();
    }

    public Families getFamilyById(int id) {
        return familyDAO.getFamilyById(id);
    }

    public int addFamily(Families families) {
        return familyDAO.addFamily(families);
    }

    public void updateFamily(Families families) {
        familyDAO.updateFamily(families);
    }

    public boolean isFamilyExistByFamily(Families families) {
        if (familyDAO.isFamilyExistByFamily(families)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Families> getFamiliesCreateOrUpdateLast(Date date) {
        return familyDAO.getFamiliesCreateOrUpdateLast(date);
    }

    public Timestamp getDateUpdateLastest() {
        return familyDAO.getDateUpdateLastest();
    }

    public List<Families> getFamilyByOrderId(int id) {
        return familyDAO.getFamilyByOrderId(id);
    }
}
