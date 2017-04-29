package com.web.service;

import com.web.dao.IGenusDAO;
import com.web.entity.backend.Genus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */

@Service
public class GenusService implements IGenusService {

    @Autowired
    private IGenusDAO genusDAO;

    public List<Genus> getAllGenuses() {
        return genusDAO.getAllGenuses();
    }

    public Genus getGenusById(int id) {
        return genusDAO.getGenusById(id);
    }

    public int addGenus(Genus genus) {
        return genusDAO.addGenus(genus);
    }

    public void updateGenus(Genus genus) {
        genusDAO.updateGenus(genus);
    }

    public boolean isGenusExistByGenus(Genus genus) {
        if (genusDAO.isGenusExistByGenus(genus)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Genus> getGenusCreateOrUpdateLast(Date date) {
        return genusDAO.getGenusCreateOrUpdateLast(date);
    }

    public Timestamp getDateUpdateLastest() {
        return genusDAO.getDateUpdateLastest();
    }

    public List<Genus> getGenusByFamilyId(int id) {
        return genusDAO.getGenusByFamilyId(id);
    }
}
