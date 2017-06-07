package com.web.service;

import com.web.dao.ISpeciesDAO;
import com.web.entity.backend.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 22/02/2017.
 */

@Service
public class SpeciesService implements ISpeciesService {

    @Autowired
    private ISpeciesDAO speciesDAO;

    public List<Species> getAllSpecies() {
        return speciesDAO.getAllSpecies();
    }

    public Species getSpeciesById(int id) {
        return speciesDAO.getSpeciesById(id);
    }

    public int addSpecies(Species species) {
        return speciesDAO.addSpecies(species);
    }

    public void updateSpecies(Species species) {
        speciesDAO.updateSpecies(species);
    }

    public boolean isSpeciesExistBySpecies(Species species) {
        return false;
    }

    public List<Species> getSpeciesByTypeColor(ArrayList<String> type, ArrayList<String> color) {
        return speciesDAO.getSpeciesByTypeColor(type, color);
    }

    public List<Species> getSpeciesByName(String name) {
        return speciesDAO.getSpeciesByName(name);
    }

    public boolean checkSpeciesByName(String scienceName, String vietnameseName, String otherName) {
        return speciesDAO.checkSpeciesByName(scienceName, vietnameseName, otherName);
    }

    public boolean checkSpeciesByNameId(String scienceName, String vietnameseName, String otherName, int id) {
        return speciesDAO.checkSpeciesByNameId(scienceName, vietnameseName, otherName, id);
    }

    public List<Species> getSpeciesCreateOrUpdateLast(Date date) {
        return speciesDAO.getSpeciesCreateOrUpdateLast(date);
    }

    public Timestamp getDateUpdateLastest() {
        return speciesDAO.getDateUpdateLastest();
    }

    public List<Species> getSpeciesByPhylumId(int id) {
        return speciesDAO.getSpeciesByPhylumId(id);
    }

    public List<Species> getSpeciesByClassId(int id) {
        return speciesDAO.getSpeciesByClassId(id);
    }

    public List<Species> getSpeciesByOrderId(int id) {
        return speciesDAO.getSpeciesByOrderId(id);
    }

    public List<Species> getSpeciesByFamilyId(int id) {
        return speciesDAO.getSpeciesByFamilyId(id);
    }

    public List<Species> getSpeciesByGenusId(int id) {
        return speciesDAO.getSpeciesByGenusId(id);
    }

    public List<Species> getAllSpeciesByOffset(int offset) {
        return speciesDAO.getAllSpeciesByOffset(offset);
    }

    public List<Species> getRareSpeciesByOffset(int offset) {
        return speciesDAO.getRareSpeciesByOffset(offset);
    }

    public List<Species> getNormalSpeciesByOffset(int offset) {
        return speciesDAO.getNormalSpeciesByOffset(offset);
    }

    public List<Species> getSpecieByKey(String key) {
        return speciesDAO.getSpecieByKey(key);
    }

    public List<Species> getListSpeciesShare(int id) {
        return speciesDAO.getListSpeciesShare(id);
    }

    public List<Species> getListSpeciesApprove() {
        return speciesDAO.getListSpeciesApprove();
    }

    public Integer addSpeciesShare(Species species) {
        return speciesDAO.addSpeciesShare(species);
    }

    public void approveSpecies(Species species) {
        speciesDAO.approveSpecies(species);
    }
}
