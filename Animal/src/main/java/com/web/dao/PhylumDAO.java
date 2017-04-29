package com.web.dao;

import com.web.entity.backend.Phylum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by duyle on 14/02/2017.
 */
@Transactional
@Repository
public class PhylumDAO implements IPhylumDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Phylum> getAllPhylums() {
        String hql = "FROM Phylum as p ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Phylum>) hibernateTemplate.find(hql);
    }

    public boolean isPhylumExistByPhylum(Phylum phylum) {

        String hql = "FROM Phylum Where scienceName = '" + phylum.getScienceName() + "' or vietnameseName = '" + phylum.getVietnameseName() + "' WHERE id <> " + phylum.getId();
        
        hibernateTemplate.setMaxResults(0);
        List<Phylum> phylumList = (List<Phylum>) hibernateTemplate.find(hql);
        if (phylumList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isPhylumExistByScienceNameVietnameseName(String scienceName, String vietnameseName) {

        String hql = "FROM Phylum Where scienceName = '" + scienceName + "' or vietnameseName = '" + vietnameseName + "'";
        hibernateTemplate.setMaxResults(0);
        List<Phylum> phylumList = (List<Phylum>) hibernateTemplate.find(hql);
        if (phylumList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public Phylum getPhylumById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Phylum.class, id);
    }

    public int addPhylum(Phylum phylum) {
        int id = (Integer) hibernateTemplate.save(phylum);
        return id;
    }

    public void updatePhylum(Phylum phylum) {
        Phylum p = getPhylumById(phylum.getId());
        p.setNotation(phylum.getNotation());
        p.setIdKingdom(phylum.getIdKingdom());
        p.setScienceName(phylum.getScienceName());
        p.setVietnameseName(phylum.getVietnameseName());
        p.setYearDiscover(phylum.getYearDiscover());
        p.setDiscovererName(phylum.getDiscovererName());
        p.setIdCreator(phylum.getIdCreator());
        hibernateTemplate.update(p);
    }

    public Phylum getPhylumNewest() {
        return null;
    }
}
