package com.web.dao;

import com.web.entity.backend.Habitat;

import java.util.List;

/**
 * Created by duyle on 23/02/2017.
 */
public interface IHabitatDAO {

    List<Habitat> getAllHabitats();

    Habitat getHabitatById(int id);

    int addHabitat(Habitat habitat);

    void updateHabitat(Habitat habitat);

    boolean isHabitatExistByHabitat(Habitat habitat);

    Habitat getHabitatByLongitudeLatitude(double longitude, double latitude);
}
