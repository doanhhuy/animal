package com.web.service;

import com.web.dao.IHabitatDAO;
import com.web.entity.backend.Habitat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duyle on 23/02/2017.
 */
@Service
public class HabitatService implements IHabitatService{

    @Autowired
    private IHabitatDAO habitatDAO;

    public List<Habitat> getAllHabitats() {
        return habitatDAO.getAllHabitats();
    }

    public Habitat getHabitatById(int id) {
        return habitatDAO.getHabitatById(id);
    }

    public int addHabitat(Habitat habitat) {
        return habitatDAO.addHabitat(habitat);
    }

    public void updateHabitat(Habitat habitat) {
        habitatDAO.updateHabitat(habitat);
    }

    public boolean isHabitatExistByHabitat(Habitat habitat) {
        return false;
    }

    public Habitat getHabitatByLongitudeLatitude(double longitude, double latitude) {
        return habitatDAO.getHabitatByLongitudeLatitude(longitude, latitude);
    }
}
