package com.web.dao;

import com.web.entity.backend.Species;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */

@Transactional
@Repository
public class SpeciesDAO implements ISpeciesDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Species> getAllSpecies() {
        String hql = "FROM Species as p WHERE p.status <> 0 ORDER BY p.id";

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public Species getSpeciesById(int id) {
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Species.class, id);
    }

    public int addSpecies(Species species) {
        return (Integer) hibernateTemplate.save(species);
    }

    public void updateSpecies(Species species) {
        Species p = getSpeciesById(species.getId());
        p.setIdCreator(species.getIdCreator());
        p.setAlertlevel(species.getAlertlevel());
        p.setBiologicalBehavior(species.getBiologicalBehavior());
        p.setFood(species.getFood());
        p.setHabitatByIdSpecies(species.getHabitatByIdSpecies());
        p.setNotation(species.getNotation());
        p.setVietnameseName(species.getVietnameseName());
        p.setScienceName(species.getScienceName());
        p.setOtherName(species.getOtherName());
        p.setIndividualQuantity(species.getIndividualQuantity());
        p.setDiscovererName(species.getDiscovererName());
        p.setMediumSize(species.getMediumSize());
        p.setOrigin(species.getOrigin());
        p.setOrtherTraits(species.getOrtherTraits());
        p.setIdGenus(species.getIdGenus());
        p.setReproductionTraits(species.getReproductionTraits());
        p.setSexualTraits(species.getSexualTraits());
        p.setYearDiscover(species.getYearDiscover());
        p.setDateCreate(species.getDateCreate());
        p.setDateUpdate(species.getDateUpdate());
        p.setImage(species.getImage());
        p.setStatus(1);
        p.setIdChecker(p.getIdChecker());
        p.setType(species.getType());
        p.setColor(species.getColor());
        hibernateTemplate.merge(p);
    }

    public boolean isSpeciesExistBySpecies(Species species) {
        return false;
    }

    public List<Species> getSpeciesByTypeColor(ArrayList<String> type, ArrayList<String> color) {

        int typeSize = type.size();
        int colorSize = color.size();
        int length = Math.max(typeSize, colorSize);
        String compareType = "";
        String compareColor = "";
        String tempType = "";
        String tempColor = "";
        for (int i = 0; i < length; i++) {
            if (i < typeSize) {
                tempType = type.get(i);
                if (i == 0) {
                    if (tempType != null || tempType != "") {
                        compareType += "type like '%" + tempType + "%' ";
                    }
                } else {
                    if (tempType != null || tempType != "") {
                        compareType += "OR type like '%" + tempType + "%' ";
                    }
                }
            }
            if (i < colorSize) {
                tempColor = color.get(i);
                if (i == 0) {
                    if (tempColor != null || tempColor != "") {
                        compareColor += "color like '%" + tempColor + "%' ";
                    }
                } else {
                    if (tempColor != null || tempColor != "") {
                        compareColor += "OR color like '%" + tempColor + "%' ";
                    }
                }
            }

        }

        String hql = "";

        if (compareType == "") {
            return new ArrayList<Species>();
        } else {
            if (tempColor != "") {
                hql = "FROM Species WHERE status <> 0 AND ( " + compareType + " ) AND ( " + compareColor + " )";
            } else {
                hql = "FROM Species WHERE status <> 0 AND ( " + compareType + " )";
            }
        }

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public List<Species> getSpeciesByName(String name) {

        String hql = "FROM Species WHERE status <> 0 AND (vietnameseName like '%" + StringUtils.capitalize(name) + "%' OR " + "vietnameseName " + "like '%" + name + "%' OR otherName like '%" + StringUtils.capitalize(name) + "%'" + "" + " OR otherName " + "like '%" + name + "%' )";

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public boolean checkSpeciesByName(String scienceName, String vietnameseName, String otherName) {
        String hql;
        if (otherName != "") {
            hql = "FROM Species WHERE status <> 0 AND ( scienceName = '" + scienceName + "' OR vietnameseName = '" + vietnameseName + "' OR otherName = '" + otherName + "' )";
        } else {
            hql = "FROM Species WHERE status <> 0 AND ( scienceName = '" + scienceName + "' OR vietnameseName = '" + vietnameseName + "')";
        }
        hibernateTemplate.setMaxResults(0);
        List<Species> species = (List<Species>) hibernateTemplate.find(hql);
        if (species.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkSpeciesByNameId(String scienceName, String vietnameseName, String otherName, int id) {
        String hql = "FROM Species WHERE status <> 0 AND (scienceName == '" + scienceName + "' OR vietnameseName " + "= '" + vietnameseName + "' OR otherName = '" + otherName + "') AND id <> " + id;

        hibernateTemplate.setMaxResults(0);
        List<Species> species = (List<Species>) hibernateTemplate.find(hql);
        if (species.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<Species> getSpeciesCreateOrUpdateLast(Date date) {
        String hql = "FROM Species as p WHERE p.status <> 0 AND p.dateUpdate > '" + date + "' ORDER BY p.id";
//        hibernateTemplate.setMaxResults(200);

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public Timestamp getDateUpdateLastest() {
        String hql = "FROM Species as p WHERE p.status <> 0 ORDER BY p.dateUpdate DESC";

        hibernateTemplate.setMaxResults(1);
        List<Species> species = (List<Species>) hibernateTemplate.find(hql);
        return species.get(0).getDateUpdate();
    }

    public List<Species> getSpeciesByPhylumId(int id) {
        String hql = "FROM Species as p WHERE p.status <> 0 AND p.idGenus.idFamily.idOrder.idClass.idPhylum.id = '" + id + "' ORDER BY" + " p.id";
//        hibernateTemplate.setMaxResults(200);

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public List<Species> getSpeciesByClassId(int id) {
        String hql = "FROM Species as p WHERE p.status <> 0 AND p.idGenus.idFamily.idOrder.idClass.id = '" + id + "' ORDER BY p.id";
//        hibernateTemplate.setMaxResults(200);

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public List<Species> getSpeciesByOrderId(int id) {
        String hql = "FROM Species as p WHERE p.status <> 0 AND p.idGenus.idFamily.idOrder.id = '" + id + "' ORDER BY p.id";
//        hibernateTemplate.setMaxResults(200);

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public List<Species> getSpeciesByFamilyId(int id) {
        String hql = "FROM Species as p WHERE p.status <> 0 AND p.idGenus.idFamily.id = '" + id + "' ORDER BY p.id";
//        hibernateTemplate.setMaxResults(200);

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public List<Species> getSpeciesByGenusId(int id) {
        String hql = "FROM Species as p WHERE p.status <> 0 AND p.idGenus.id = '" + id + "' ORDER BY p.id";
//        hibernateTemplate.setMaxResults(200);

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public List<Species> getAllSpeciesByOffset(int offset) {
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from Species WHERE status <> 0 ORDER BY id");
        query.setMaxResults(60);
        query.setFirstResult(offset);
        return query.list();
    }

    public List<Species> getRareSpeciesByOffset(int offset) {
//        String hql = "FROM Species as p WHERE p.status <> 0 AND p.alertLevel NOT LIKE '%chưa có%' ORDER BY p.id";
//        DetachedCriteria criteria = DetachedCriteria.forClass(Species.class).add(Restrictions.ne("status", 0)).add
//                (Restrictions.not(Restrictions.like("alertLevel", "%chưa có%")));
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from Species WHERE status <> 0 AND alertLevel NOT LIKE '%chưa có%' ORDER BY id");
        query.setMaxResults(60);
        query.setFirstResult(offset);
        return query.list();
//        return (List<Species>) hibernateTemplate.findByCriteria(criteria, offset, 60);
    }

    public List<Species> getNormalSpeciesByOffset(int offset) {
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from Species WHERE status <> 0 AND alertLevel LIKE '%chưa có%' ORDER BY id");
        query.setMaxResults(60);
        query.setFirstResult(offset);
        return query.list();
    }

    public List<Species> getSpecieByKey(String key) {
        String hql = "FROM Species as p WHERE p.status <> 0 AND (p.otherName like '%" + StringUtils.capitalize(key) + "%' OR  p.vietnameseName" + " like " + "'%" + StringUtils.capitalize(key) + "%' OR p.scienceName like" + " '%" + StringUtils.capitalize(key) + "%'" + " OR p.otherName like '%" + key + "%' OR  p" + "" + ".vietnameseName" + " like " + "'%" + key + "%' OR p.scienceName like '%" + key + "%' )";
//        hibernateTemplate.setMaxResults(200);

        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public List<Species> getListSpeciesShare(int id) {
        String hql = "FROM Species WHERE idCreator.id = " + id;
        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public Integer addSpeciesShare(Species species) {
        return (Integer) hibernateTemplate.save(species);
    }

    public List<Species> getListSpeciesApprove() {
        String hql = "FROM Species WHERE status = 0 ORDER BY id";
        hibernateTemplate.setMaxResults(0);
        return (List<Species>) hibernateTemplate.find(hql);
    }

    public void approveSpecies(Species species) {
        Species p = getSpeciesById(species.getId());
        p.setStatus(1);
        p.setIdChecker(species.getIdChecker());
        p.setDateUpdate(species.getDateUpdate());
        if (species.getMediumSize() != "") {
            p.setMediumSize(species.getMediumSize());
        }
        if (species.getOrtherTraits() != "") {
            p.setOrtherTraits(species.getOrtherTraits());
        }
        if (species.getIdGenus() != null) {
            p.setIdGenus(species.getIdGenus());
        }
        if (species.getReproductionTraits() != "") {
            p.setReproductionTraits(species.getReproductionTraits());
        }
        if (species.getSexualTraits() != "") {
            p.setSexualTraits(species.getSexualTraits());
        }
        if (species.getVietnameseName() != "") {
            p.setVietnameseName(species.getVietnameseName());
        }
        if (species.getScienceName() != "") {
            p.setScienceName(species.getScienceName());
        }
        if (species.getBiologicalBehavior() != "") {
            p.setBiologicalBehavior(species.getBiologicalBehavior());
        }
        if (species.getFood() != "") {
            p.setFood(species.getFood());
        }
        hibernateTemplate.merge(p);
    }
}
