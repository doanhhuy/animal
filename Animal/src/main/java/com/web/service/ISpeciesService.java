package com.web.service;

import com.web.entity.backend.Species;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 22/02/2017.
 */
public interface ISpeciesService {

    List<Species> getAllSpecies();

    Species getSpeciesById(int id);

    int addSpecies(Species species);

    void updateSpecies(Species species);

    boolean isSpeciesExistBySpecies(Species species);

    List<Species> getSpeciesByTypeColor(ArrayList<String> type, ArrayList<String> color);

    List<Species> getSpeciesByName(String name);

    boolean checkSpeciesByName(String scienceName, String vietnameseName, String otherName);

    boolean checkSpeciesByNameId(String scienceName, String vietnameseName, String otherName, int id);

    List<Species> getSpeciesCreateOrUpdateLast(Date date);

    Timestamp getDateUpdateLastest();

    List<Species> getSpeciesByPhylumId(int id);

    List<Species> getSpeciesByClassId(int id);

    List<Species> getSpeciesByOrderId(int id);

    List<Species> getSpeciesByFamilyId(int id);

    List<Species> getSpeciesByGenusId(int id);

    List<Species> getAllSpeciesByOffset(int offset);

    List<Species> getRareSpeciesByOffset(int offset);

    List<Species> getNormalSpeciesByOffset(int offset);

    List<Species> getSpecieByKey(String key);

    List<Species> getListSpeciesShare(int id);

    Integer addSpeciesShare(Species species);

    List<Species> getListSpeciesApprove();

    void approveSpecies(Species species);
}
