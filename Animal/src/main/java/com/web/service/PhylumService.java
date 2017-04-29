package com.web.service;

import com.web.dao.IPhylumDAO;
import com.web.entity.backend.Phylum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duyle on 14/02/2017.
 */
@Service
public class PhylumService implements IPhylumService{

    @Autowired
    private IPhylumDAO phylumDAO;

    public List<Phylum> getAllPhylums() {
        return phylumDAO.getAllPhylums();
    }

    public Phylum getPhylumById(int id) {
        Phylum obj = phylumDAO.getPhylumById(id);
        return obj;
    }

    public int addPhylum(Phylum phylum) {
        return phylumDAO.addPhylum(phylum);
    }

    public void updatePhylum(Phylum phylum) {
        phylumDAO.updatePhylum(phylum);
    }

    public boolean isPhylumExistByPhylum(Phylum phylum) {
        if(phylumDAO.isPhylumExistByPhylum(phylum)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPhylumExistByScienceNameVietnameseName(String scienceName, String vietnameseName){
        return phylumDAO.isPhylumExistByScienceNameVietnameseName(scienceName, vietnameseName);
    }

}
