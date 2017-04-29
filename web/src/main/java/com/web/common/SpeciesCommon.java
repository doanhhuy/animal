package com.web.common;

import com.web.entity.backend.Species;

import java.util.List;

/**
 * Created by duyle on 3/30/17.
 */
public class SpeciesCommon {

    public int getTotalOfRareAnimal(List<Species> speciesList) {
        int result = 0;
        for (Species species : speciesList) {
            if (!species.getAlertlevel().contains("chưa có")) {
                result++;
            }
        }
        return result;
    }

    public int getTotalOfNormalAnimal(List<Species> speciesList) {
        int result = 0;
        for (Species species : speciesList) {
            if (species.getAlertlevel().contains("chưa có")) {
                result++;
            }
        }
        return result;
    }

}
