package com.web.controller;

import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.common.DateCommon;
import com.web.entity.JSON.HabitatJSON;
import com.web.entity.JSON.SpeciesJSON;
import com.web.entity.api.HabitatAPI;
import com.web.entity.api.SpeciesAPI;
import com.web.entity.backend.Habitat;
import com.web.entity.backend.Members;
import com.web.entity.backend.Species;
import com.web.service.IAccountService;
import com.web.service.IHabitatService;
import com.web.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 23/02/2017.
 */

@Controller
public class HabitatController {

    @Autowired
    private IHabitatService habitatService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IMemberService memberService;

    @RequestMapping(value = "/api/habitats", method = RequestMethod.GET)
    public ResponseEntity<HabitatJSON> getAllHabitats() {
        List<Habitat> habitatList = habitatService.getAllHabitats();
        if (habitatList.size() == 0) {
            return new ResponseEntity<HabitatJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<HabitatAPI> habitatAPIS = new ArrayList<HabitatAPI>();
            HabitatAPI habitatAPI;
            Members members;
            for (Habitat h : habitatList) {
                habitatAPI = new HabitatAPI();
                habitatAPI.setId(h.getId());
                habitatAPI.setLocationName(h.getLocationName());
                habitatAPI.setLatitude(h.getLatitude());
                habitatAPI.setLongitude(h.getLongitude());
                habitatAPI.setIdChecker(h.getIdChecker().getId());
                members = memberService.getMemberById(h.getIdChecker().getId());
                habitatAPI.setNameChecker(members.getFullName());
                habitatAPI.setIdCreator(h.getIdCreator().getId());
                members = memberService.getMemberById(h.getIdCreator().getId());
                habitatAPI.setNameCreator(members.getFullName());
                habitatAPIS.add(habitatAPI);
            }
            HabitatJSON habitatJSON = new HabitatJSON();
            habitatJSON.setHabitats(habitatAPIS);
            return new ResponseEntity<HabitatJSON>(habitatJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/habitats/", method = RequestMethod.POST)
    public ResponseEntity<HabitatJSON> addHabitat(@RequestParam("id") int id, @RequestBody HabitatAPI habitatAPI) throws ErrorException {
        Habitat habitat = habitatService.getHabitatByLongitudeLatitude(habitatAPI.getLongitude(), habitatAPI.getLatitude());
        if (habitat == null) {
            habitat = new Habitat();
            habitat.setLocationName(habitatAPI.getLocationName());
            habitat.setLongitude(habitatAPI.getLongitude());
            habitat.setLatitude(habitatAPI.getLatitude());
            habitat.setIdChecker(accountService.getAccountById(id));
            habitat.setIdCreator(accountService.getAccountById(id));

            int result = habitatService.addHabitat(habitat);
            if (result == 0) {
                throw new ErrorException("Thêm nơi sống không thành công");
            } else {
                Members members;
                Habitat habitat1 = habitatService.getHabitatById(result);
                HabitatAPI habitatAPI1 = new HabitatAPI();
                habitatAPI1.setId(habitat1.getId());
                habitatAPI1.setLocationName(habitat1.getLocationName());
                habitatAPI1.setLongitude(habitat1.getLongitude());
                habitatAPI1.setLatitude(habitat1.getLatitude());
                habitatAPI1.setIdChecker(habitat1.getIdChecker().getId());
                members = memberService.getMemberById(habitat1.getIdChecker().getId());
                habitatAPI1.setNameChecker(members.getFullName());
                members = memberService.getMemberById(habitat1.getIdCreator().getId());
                habitatAPI1.setIdCreator(habitat1.getIdCreator().getId());
                habitatAPI1.setNameCreator(members.getFullName());

                HabitatJSON habitatJSON = new HabitatJSON();
                habitatJSON.setHabitat(habitatAPI1);
                return new ResponseEntity<HabitatJSON>(habitatJSON, HttpStatus.OK);
            }
        } else {
            throw new ErrorException("Nơi sống đã tồn tại!");
        }
    }

    @RequestMapping(value = "/api/habitats/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HabitatJSON> updateHabitat(@PathVariable("id") int id, @RequestBody HabitatAPI habitatAPI) throws ErrorException {
        Members members;
        Habitat habitat = habitatService.getHabitatByLongitudeLatitude(habitatAPI.getLongitude(), habitatAPI.getLatitude());
        if (habitat == null) {
            habitat = new Habitat();
            habitat.setId(id);
            habitat.setLocationName(habitatAPI.getLocationName());
            habitat.setLongitude(habitatAPI.getLongitude());
            habitat.setLatitude(habitatAPI.getLatitude());
            habitat.setIdChecker(accountService.getAccountById(habitatAPI.getIdChecker()));
            habitat.setIdCreator(accountService.getAccountById(habitatAPI.getIdChecker()));

            habitatService.updateHabitat(habitat);
            Habitat habitat1 = habitatService.getHabitatById(id);
            HabitatAPI habitatAPI1 = new HabitatAPI();
            habitatAPI1.setId(habitat1.getId());
            habitatAPI1.setLocationName(habitat1.getLocationName());
            habitatAPI1.setLongitude(habitat1.getLongitude());
            habitatAPI1.setLatitude(habitat1.getLatitude());
            habitatAPI1.setIdChecker(habitat1.getIdChecker().getId());
            members = memberService.getMemberById(habitat1.getIdChecker().getId());
            habitatAPI1.setNameChecker(members.getFullName());
            habitatAPI1.setIdCreator(habitat1.getIdCreator().getId());
            members = memberService.getMemberById(habitat1.getIdChecker().getId());
            habitatAPI1.setNameCreator(members.getFullName());

            HabitatJSON habitatJSON = new HabitatJSON();
            habitatJSON.setHabitat(habitatAPI1);
            return new ResponseEntity<HabitatJSON>(habitatJSON, HttpStatus.OK);
        } else {
            throw new ErrorException("Nơi sống đã tồn tại!");
        }
    }

    @RequestMapping(value = "/api/habitat/species/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpeciesJSON> getHabitatByIdSpecies(@PathVariable("id") int id) {
        DateCommon dateCommon = new DateCommon();
        Habitat habitat = habitatService.getHabitatById(id);

        if (habitat == null) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<Species> species = new ArrayList<Species>();
            species.addAll(habitat.getSpeciesByIdHabitat());
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : species) {
                speciesAPI = new SpeciesAPI();
                speciesAPI.setId(s.getId());
                speciesAPI.setAlertlevel(s.getAlertlevel());
                speciesAPI.setBiologicalBehavior(s.getBiologicalBehavior());
                speciesAPI.setDateCreate(s.getDateCreate());
                speciesAPI.setDateUpdate(s.getDateUpdate().toString());
                speciesAPI.setDiscovererName(s.getDiscovererName());
                speciesAPI.setFood(s.getFood());
                speciesAPI.setIdChecker(s.getIdChecker().getId());
                speciesAPI.setIdCreator(s.getIdCreator().getId());
                speciesAPI.setIdGenus(s.getIdGenus().getId());
                speciesAPI.setImage(s.getImage());
                speciesAPI.setIndividualQuantity(s.getIndividualQuantity());
                speciesAPI.setMediumSize(s.getMediumSize());
                members = memberService.getMemberById(s.getIdChecker().getId());
                speciesAPI.setNameChecker(members.getFullName());
                members = memberService.getMemberById(s.getIdCreator().getId());
                speciesAPI.setNameCreator(members.getFullName());
                speciesAPI.setNotation(s.getNotation());
                speciesAPI.setOrigin(s.getOrigin());
                speciesAPI.setOrtherTraits(s.getOrtherTraits());
                speciesAPI.setOtherName(s.getOtherName());
                speciesAPI.setReproductionTraits(s.getReproductionTraits());
                speciesAPI.setScienceName(s.getScienceName());
                speciesAPI.setVietnameseName(s.getVietnameseName());
                speciesAPI.setScienceNameGenus(s.getIdGenus().getScienceName());
                speciesAPI.setVietnameseNameGenus(s.getIdGenus().getVietnameseName());
                speciesAPI.setSexualTraits(s.getSexualTraits());
                speciesAPI.setStatus(s.getStatus());
                speciesAPI.setYearDiscover(s.getYearDiscover());
                speciesAPI.setVietnameseNameFamily(s.getIdGenus().getIdFamily().getVietnameseName());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }

    }

    /*
       Function return error message by JSON
   */
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }

}
