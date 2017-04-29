package com.web.dao;

import com.web.entity.backend.Families;
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
public class FamilyDAO implements IFamilyDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Families> getAllFamilies() {
        String hql = "FROM Families as p ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Families>) hibernateTemplate.find(hql);
    }

    public Families getFamilyById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Families.class, id);
    }

    public int addFamily(Families families) {
        return (Integer) hibernateTemplate.save(families);
    }

    public void updateFamily(Families families) {
        Families p = getFamilyById(families.getId());
        p.setNotation(families.getNotation());
        p.setStatus(families.getStatus());
        p.setScienceName(families.getScienceName());
        p.setVietnameseName(families.getVietnameseName());
        p.setYearDiscover(families.getYearDiscover());
        p.setDiscovererName(families.getDiscovererName());
        p.setIdCreator(families.getIdCreator());
        p.setIdChecker(families.getIdChecker());
        p.setDateCreate(families.getDateCreate());
        p.setDateUpdate(families.getDateUpdate());
        p.setIdOrder(families.getIdOrder());
        hibernateTemplate.merge(families);
    }

    public boolean isFamilyExistByFamily(Families families) {
        String hql = "FROM Families Where scienceName = '" + families.getScienceName() + "' or vietnameseName = '" + families.getVietnameseName() + "'";
        
        hibernateTemplate.setMaxResults(0);
        List<Families> ordersList = (List<Families>) hibernateTemplate.find(hql);
        if (ordersList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<Families> getFamiliesCreateOrUpdateLast(Date date) {
        String hql = "FROM Families as p WHERE p.dateUpdate > '" + date + "' ORDER BY p.id";
//        hibernateTemplate.setMaxResults(200);
        
        hibernateTemplate.setMaxResults(0);
        return (List<Families>) hibernateTemplate.find(hql);
    }

    public Timestamp getDateUpdateLastest() {
        String hql = "FROM Families as p ORDER BY p.dateUpdate DESC";
        
        hibernateTemplate.setMaxResults(1);
        List<Families> families = (List<Families>) hibernateTemplate.find(hql);
        return families.get(0).getDateUpdate();
    }

    public List<Families> getFamilyByOrderId(int id) {
        String hql = "FROM Families as p WHERE p.idOrder.id = " + id + " ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Families>) hibernateTemplate.find(hql);
    }
}
