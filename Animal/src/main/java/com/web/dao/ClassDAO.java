package com.web.dao;

import com.web.entity.backend.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
@Transactional
@Repository
public class ClassDAO implements IClassDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Classes> getAllClasss() {
        String hql = "FROM Classes as p ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Classes>) hibernateTemplate.find(hql);
    }

    public boolean isClassExistByClass(Classes classes) {

        String hql = "FROM Classes Where id <> " + classes.getId() + " and (scienceName = '" + classes.getScienceName() + "' or vietnameseName = '" + classes.getVietnameseName() + "')";
        
        hibernateTemplate.setMaxResults(0);
        List<Classes> classesList = (List<Classes>) hibernateTemplate.find(hql);
        if (classesList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public Classes getClassById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Classes.class, id);
    }

    public int addClass(Classes classes) {
        return (Integer) hibernateTemplate.save(classes);
    }

    public void updateClass(Classes classes) {
        Classes p = getClassById(classes.getId());
        p.setNotation(classes.getNotation());
        p.setIdPhylum(classes.getIdPhylum());
        p.setScienceName(classes.getScienceName());
        p.setVietnameseName(classes.getVietnameseName());
        p.setYearDiscover(classes.getYearDiscover());
        p.setDiscovererName(classes.getDiscovererName());
        p.setIdCreator(classes.getIdCreator());
        hibernateTemplate.merge(p);
    }

    public List<Classes> getClassByPhylumId(int id) {
        String hql = "FROM Classes as p WHERE idPhylum.id =" + id + " ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Classes>) hibernateTemplate.find(hql);
    }
}
