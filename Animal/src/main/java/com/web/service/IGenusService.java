package com.web.service;

import com.web.entity.backend.Genus;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
public interface IGenusService {

    List<Genus> getAllGenuses();

    Genus getGenusById(int id);

    int addGenus(Genus genus);

    void updateGenus(Genus genus);

    boolean isGenusExistByGenus(Genus genus);

    List<Genus> getGenusCreateOrUpdateLast(Date date);

    Timestamp getDateUpdateLastest();

    List<Genus> getGenusByFamilyId(int id);
}
