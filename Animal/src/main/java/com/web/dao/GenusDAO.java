package com.web.dao;

import com.web.entity.backend.Genus;
import com.web.entity.backend.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */

@Transactional
@Repository
public class GenusDAO implements IGenusDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Genus> getAllGenuses() {
        String hql = "FROM Genus as p ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Genus>) hibernateTemplate.find(hql);
    }

    public Genus getGenusById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Genus.class, id);
    }

    public int addGenus(Genus genus) {
        return (Integer) hibernateTemplate.save(genus);
    }

    public void updateGenus(Genus genus) {
        Genus p = getGenusById(genus.getId());
        p.setNotation(genus.getNotation());
        p.setStatus(genus.getStatus());
        p.setScienceName(genus.getScienceName());
        p.setVietnameseName(genus.getVietnameseName());
        p.setYearDiscover(genus.getYearDiscover());
        p.setDiscovererName(genus.getDiscovererName());
        p.setIdCreator(genus.getIdCreator());
        p.setIdChecker(genus.getIdChecker());
        p.setDateCreate(genus.getDateCreate());
        p.setDateUpdate(genus.getDateUpdate());
        p.setIdFamily(genus.getIdFamily());
        hibernateTemplate.merge(genus);
    }

    public boolean isGenusExistByGenus(Genus genus) {
        String hql = "FROM Genus Where scienceName = '" + genus.getScienceName() + "' or vietnameseName = '" + genus.getVietnameseName() + "'";
        
        hibernateTemplate.setMaxResults(0);
        List<Genus> genusList = (List<Genus>) hibernateTemplate.find(hql);
        if (genusList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<Genus> getGenusCreateOrUpdateLast(Date date) {
        String hql = "FROM Genus as p WHERE p.dateUpdate > '" + date + "' ORDER BY p.id";
//        hibernateTemplate.setMaxResults(200);
        
        hibernateTemplate.setMaxResults(0);
        return (List<Genus>) hibernateTemplate.find(hql);
    }

    public Timestamp getDateUpdateLastest() {
        String hql = "FROM Genus as p ORDER BY p.dateUpdate DESC";
        
        hibernateTemplate.setMaxResults(1);
        List<Genus> genusList = (List<Genus>) hibernateTemplate.find(hql);
        return genusList.get(0).getDateUpdate();
    }

    public List<Genus> getGenusByFamilyId(int id) {
        String hql = "FROM Genus as p WHERE p.idFamily.id = " + id + " ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Genus>) hibernateTemplate.find(hql);
    }
}
