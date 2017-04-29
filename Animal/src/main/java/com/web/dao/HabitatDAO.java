package com.web.dao;

import com.web.entity.backend.Habitat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by duyle on 23/02/2017.
 */

@Transactional
@Repository
public class HabitatDAO implements IHabitatDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Habitat> getAllHabitats() {
        String hql = "From Habitat as p ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Habitat>) hibernateTemplate.find(hql);
    }

    public Habitat getHabitatById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Habitat.class, id);
    }

    public int addHabitat(Habitat habitat) {
        return (Integer) hibernateTemplate.save(habitat);
    }

    public void updateHabitat(Habitat habitat) {
        Habitat h = getHabitatById(habitat.getId());
        h.setLatitude(habitat.getLatitude());
        h.setLongitude(habitat.getLongitude());
        h.setLocationName(habitat.getLocationName());
        hibernateTemplate.update(h);
    }

    public Habitat getHabitatByLongitudeLatitude(double longitude, double latitude) {
        String hql = "From Habitat as p WHERE p.longitude = " + longitude + " AND p.latitude = " + latitude;
        
        hibernateTemplate.setMaxResults(0);
        List<Habitat> habitats = (List<Habitat>) hibernateTemplate.find(hql);
        if(habitats.size() == 0){
            return null;
        } else {
            return habitats.get(0);
        }
    }

    public boolean isHabitatExistByHabitat(Habitat habitat) {
        return false;
    }
}
