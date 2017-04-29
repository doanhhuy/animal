package com.web.service;

import com.web.entity.backend.Phylum;

import java.sql.Date;
import java.util.List;

/**
 * Created by duyle on 14/02/2017.
 */
public interface IPhylumService {

    List<Phylum> getAllPhylums();

    Phylum getPhylumById(int id);

    int addPhylum(Phylum phylum);

    void updatePhylum(Phylum phylum);

    boolean isPhylumExistByPhylum(Phylum phylum);

    boolean isPhylumExistByScienceNameVietnameseName(String scinceName, String vietnameseName);

}
