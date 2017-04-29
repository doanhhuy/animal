package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.HabitatAPI;

import java.util.List;

/**
 * Created by duyle on 23/02/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HabitatJSON {

    private HabitatAPI habitat;
    private List<HabitatAPI> habitats;

    public HabitatAPI getHabitat() {
        return habitat;
    }

    public void setHabitat(HabitatAPI habitat) {
        this.habitat = habitat;
    }

    public List<HabitatAPI> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<HabitatAPI> habitats) {
        this.habitats = habitats;
    }
}
