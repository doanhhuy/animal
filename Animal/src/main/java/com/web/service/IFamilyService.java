package com.web.service;

import com.web.entity.backend.Families;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
public interface IFamilyService {

    List<Families> getAllFamilies();

    Families getFamilyById(int id);

    int addFamily(Families families);

    void updateFamily(Families families);

    boolean isFamilyExistByFamily(Families families);

    List<Families> getFamiliesCreateOrUpdateLast(Date date);

    Timestamp getDateUpdateLastest();

    List<Families> getFamilyByOrderId(int id);
}
