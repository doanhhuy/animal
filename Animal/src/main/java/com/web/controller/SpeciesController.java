package com.web.controller;

import com.web.bean.*;
import com.web.common.DateCommon;
import com.web.common.ImageCommon;
import com.web.common.MicrosoftCommon;
import com.web.entity.JSON.HabitatJSON;
import com.web.entity.JSON.SpeciesJSON;
import com.web.entity.api.HabitatAPI;
import com.web.entity.api.SpeciesAPI;
import com.web.entity.api.SpeciesShareAPI;
import com.web.entity.backend.Account;
import com.web.entity.backend.Habitat;
import com.web.entity.backend.Members;
import com.web.entity.backend.Species;
import com.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by duyle on 22/02/2017.
 */

@Controller
public class SpeciesController {

    @Autowired
    private ISpeciesService speciesService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IHabitatService habitatService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IGenusService genusService;
    @Autowired
    private ServletContext context;
    @Autowired
    private HttpServletRequest request;

    /*
    * API get all of species
    * */
    @RequestMapping(value = "/api/species", method = RequestMethod.GET)
    public ResponseEntity<SpeciesJSON> getAllSpecies() {
        List<Species> speciesList = speciesService.getAllSpecies();

        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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

    @RequestMapping(value = "/api/species/all", method = RequestMethod.GET)
    public ResponseEntity<SpeciesJSON> getAllSpeciesByOffset(@RequestParam("offset") int offset) throws ErrorException {
        List<Species> speciesList = speciesService.getAllSpeciesByOffset(offset);
        if (speciesList.size() == 0) {
            throw new ErrorException("Không tìm thấy dữ liệu!");
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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

    @RequestMapping(value = "/api/species/rare", method = RequestMethod.GET)
    public ResponseEntity<SpeciesJSON> getRareSpeciesByOffset(@RequestParam("offset") int offset) throws ErrorException {
        List<Species> speciesList = speciesService.getRareSpeciesByOffset(offset);
        if (speciesList.size() == 0) {
            throw new ErrorException("Không tìm thấy dữ liệu!");
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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

    @RequestMapping(value = "/api/species/normal", method = RequestMethod.GET)
    public ResponseEntity<SpeciesJSON> getNormalSpeciesByOffset(@RequestParam("offset") int offset) throws ErrorException {
        List<Species> speciesList = speciesService.getNormalSpeciesByOffset(offset);
        if (speciesList.size() == 0) {
            throw new ErrorException("Không tìm thấy dữ liệu!");
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
    * API get habitats where species live
    * */
    @RequestMapping(value = "/api/species/habitat/{id}", method = RequestMethod.GET)
    public ResponseEntity<HabitatJSON> getHabitatByIdSpecies(@PathVariable("id") int id) {
        DateCommon dateCommon = new DateCommon();
        Species species = speciesService.getSpeciesById(id);

        if (species == null) {
            return new ResponseEntity<HabitatJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<Habitat> habitats = new ArrayList<Habitat>();
            habitats.addAll(species.getHabitatByIdSpecies());
            List<HabitatAPI> habitatAPIS = new ArrayList<HabitatAPI>();
            HabitatAPI habitatAPI;
            Members members;
            for (Habitat h : habitats) {
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

    /*
    * API get all of species by vietnamesename or othername
    * */
    @RequestMapping(value = "/api/species/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<SpeciesJSON> getSpeciesByName(@PathVariable("name") String name) {
        DateCommon dateCommon = new DateCommon();
        List<Species> speciesList = speciesService.getSpeciesByName(name);

        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
                speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }

    }

    /*
    * API get a species by id
    * */
    @RequestMapping(value = "/api/species/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpeciesJSON> getSpecieById(@PathVariable("id") int id) throws ErrorException {
        Species species;
        SpeciesAPI speciesAPI;
        Members members;
        species = speciesService.getSpeciesById(id);
        if (species != null) {
            speciesAPI = new SpeciesAPI();
            speciesAPI.setId(species.getId());
            speciesAPI.setAlertlevel(species.getAlertlevel());
            speciesAPI.setBiologicalBehavior(species.getBiologicalBehavior());
            speciesAPI.setDateCreate(species.getDateCreate());
            speciesAPI.setDateUpdate(species.getDateUpdate().toString());
            speciesAPI.setDiscovererName(species.getDiscovererName());
            speciesAPI.setFood(species.getFood());
            speciesAPI.setIdChecker(species.getIdChecker().getId());
            speciesAPI.setIdCreator(species.getIdCreator().getId());
            speciesAPI.setIdGenus(species.getIdGenus().getId());
            speciesAPI.setImage(species.getImage());
            speciesAPI.setIndividualQuantity(species.getIndividualQuantity());
            speciesAPI.setMediumSize(species.getMediumSize());
            members = memberService.getMemberById(species.getIdChecker().getId());
            speciesAPI.setNameChecker(members.getFullName());
            members = memberService.getMemberById(species.getIdCreator().getId());
            speciesAPI.setNameCreator(members.getFullName());
            speciesAPI.setNotation(species.getNotation());
            speciesAPI.setOrigin(species.getOrigin());
            speciesAPI.setOrtherTraits(species.getOrtherTraits());
            speciesAPI.setOtherName(species.getOtherName());
            speciesAPI.setReproductionTraits(species.getReproductionTraits());
            speciesAPI.setScienceName(species.getScienceName());
            speciesAPI.setVietnameseName(species.getVietnameseName());
            speciesAPI.setScienceNameGenus(species.getIdGenus().getScienceName());
            speciesAPI.setVietnameseNameGenus(species.getIdGenus().getVietnameseName());
            speciesAPI.setSexualTraits(species.getSexualTraits());
            speciesAPI.setStatus(species.getStatus());
            speciesAPI.setYearDiscover(species.getYearDiscover());
            speciesAPI.setVietnameseNameFamily(species.getIdGenus().getIdFamily().getVietnameseName());
            speciesAPI.setIdFamily(species.getIdGenus().getIdFamily().getId());
            speciesAPI.setIdOrder(species.getIdGenus().getIdFamily().getIdOrder().getId());
            speciesAPI.setIdClass(species.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
            speciesAPI.setIdPhylum(species.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setSpecies(speciesAPI);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        } else {
            throw new ErrorException("Không tìm thấy ID loài!");
        }

    }

    @RequestMapping(value = "/api/species/search/", method = RequestMethod.GET)
    public ResponseEntity<SpeciesJSON> getSpecieByKey(@RequestParam("key") String key) throws ErrorException {
        List<Species> speciesList = speciesService.getSpecieByKey(key.toLowerCase());

        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
    * API create a species
    * */
    @RequestMapping(value = "/api/species/", method = RequestMethod.POST)
    public ResponseEntity<SpeciesJSON> createSpecies(@RequestBody SpeciesAPI speciesAPI) throws ErrorException {
        DateCommon dateCommon = new DateCommon();
        if (!speciesService.checkSpeciesByName(speciesAPI.getScienceName(), speciesAPI.getVietnameseName(), speciesAPI.getOtherName())) {
            Habitat habitat = habitatService.getHabitatByLongitudeLatitude(speciesAPI.getLongitude(), speciesAPI.getLatitude());
            Collection<Habitat> habitats = new ArrayList<Habitat>();
            int idHabitat;
            if (habitat == null) {
                habitat.setIdCreator(accountService.getAccountById(speciesAPI.getIdCreator()));
                habitat.setIdChecker(accountService.getAccountById(speciesAPI.getIdCreator()));
                habitat.setLatitude(speciesAPI.getLatitude());
                habitat.setLongitude(speciesAPI.getLongitude());
                idHabitat = habitatService.addHabitat(habitat);
                habitat = habitatService.getHabitatById(idHabitat);
                habitats.add(habitat);
            } else {
                habitats.add(habitat);
            }

            Species species = new Species();
            species.setIdCreator(accountService.getAccountById(speciesAPI.getIdCreator()));
            species.setAlertlevel(speciesAPI.getAlertlevel());
            species.setBiologicalBehavior(speciesAPI.getBiologicalBehavior());
            species.setFood(speciesAPI.getFood());
            species.setHabitatByIdSpecies(habitats);
            species.setNotation(speciesAPI.getNotation());
            species.setStatus(1);
            species.setVietnameseName(speciesAPI.getVietnameseName());
            species.setScienceName(speciesAPI.getScienceName());
            species.setOtherName(species.getOtherName());
            species.setIndividualQuantity(species.getIndividualQuantity());
            species.setDiscovererName(speciesAPI.getDiscovererName());
            species.setMediumSize(speciesAPI.getMediumSize());
            species.setOrigin(speciesAPI.getOrigin());
            species.setOrtherTraits(speciesAPI.getOrtherTraits());
            species.setIdGenus(genusService.getGenusById(speciesAPI.getIdGenus()));
            species.setReproductionTraits(speciesAPI.getReproductionTraits());
            species.setSexualTraits(speciesAPI.getSexualTraits());
            species.setYearDiscover(speciesAPI.getYearDiscover());
            species.setDateCreate(dateCommon.getDateNow());
            species.setDateUpdate(dateCommon.getTimestampNow());
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(speciesAPI.getFileName());
            fileInfo.setEncodeString(speciesAPI.getEncodeString());
            if (fileInfo != null) {
                try {
                    ImageCommon imageCommon = new ImageCommon();
                    byte[] imageByteArray = imageCommon.decodeImage(fileInfo.getEncodeString());
                /*
                 * Write a image byte array into file system
                 */
//                String url = request.getSession().getServletContext().getRealPath("/resources/images/");
                    if (imageByteArray.length > 4000000) {
                        imageByteArray = imageCommon.resizeImage(imageByteArray);
                    }
                    String url = "/home/duyle/Documents/2016-winter-biodiversity-back/Animal/src/main/webapp/resources/images/";
                    File newFile = new File(url + fileInfo.getFileName());
                    FileOutputStream imageOutFile = new FileOutputStream(newFile);
                    imageOutFile.write(imageByteArray);
                    imageOutFile.close();

                    MicrosoftCommon microsoftCommon = new MicrosoftCommon();
                    Object[] objects = microsoftCommon.getTypeColorFromImage(newFile);
                    ArrayList<String> type = (ArrayList<String>) objects[0];
                    ArrayList<String> color = (ArrayList<String>) objects[1];
                    species.setImage(fileInfo.getFileName());
                    species.setType(StringUtils.collectionToDelimitedString(type, ", "));
                    species.setColor(StringUtils.collectionToDelimitedString(color, ", "));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Integer result = speciesService.addSpecies(species);
            Members members;
            species = speciesService.getSpeciesById(result);
            speciesAPI = new SpeciesAPI();
            speciesAPI.setId(species.getId());
            speciesAPI.setAlertlevel(species.getAlertlevel());
            speciesAPI.setBiologicalBehavior(species.getBiologicalBehavior());
            speciesAPI.setDateCreate(species.getDateCreate());
            speciesAPI.setDateUpdate(species.getDateUpdate().toString());
            speciesAPI.setDiscovererName(species.getDiscovererName());
            speciesAPI.setFood(species.getFood());
            speciesAPI.setIdChecker(species.getIdChecker().getId());
            speciesAPI.setIdCreator(species.getIdCreator().getId());
            speciesAPI.setIdGenus(species.getIdGenus().getId());
            speciesAPI.setImage(species.getImage());
            speciesAPI.setIndividualQuantity(species.getIndividualQuantity());
            speciesAPI.setMediumSize(species.getMediumSize());
            members = memberService.getMemberById(species.getIdChecker().getId());
            speciesAPI.setNameChecker(members.getFullName());
            members = memberService.getMemberById(species.getIdCreator().getId());
            speciesAPI.setNameCreator(members.getFullName());
            speciesAPI.setNotation(species.getNotation());
            speciesAPI.setOrigin(species.getOrigin());
            speciesAPI.setOrtherTraits(species.getOrtherTraits());
            speciesAPI.setOtherName(species.getOtherName());
            speciesAPI.setReproductionTraits(species.getReproductionTraits());
            speciesAPI.setScienceName(species.getScienceName());
            speciesAPI.setVietnameseName(species.getVietnameseName());
            speciesAPI.setScienceNameGenus(species.getIdGenus().getScienceName());
            speciesAPI.setVietnameseNameGenus(species.getIdGenus().getVietnameseName());
            speciesAPI.setSexualTraits(species.getSexualTraits());
            speciesAPI.setStatus(species.getStatus());
            speciesAPI.setYearDiscover(species.getYearDiscover());
            speciesAPI.setVietnameseNameFamily(species.getIdGenus().getIdFamily().getVietnameseName());
            speciesAPI.setIdFamily(species.getIdGenus().getIdFamily().getId());
            speciesAPI.setIdOrder(species.getIdGenus().getIdFamily().getIdOrder().getId());
            speciesAPI.setIdClass(species.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
            speciesAPI.setIdPhylum(species.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setSpecies(speciesAPI);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        } else {
            throw new ErrorException("Loài đã tồn tại!");
        }

    }

    @RequestMapping(value = "/api/species/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SpeciesJSON> updateSpecies(@PathVariable("id") int id, @RequestBody SpeciesAPI speciesAPI) throws ErrorException {
        DateCommon dateCommon = new DateCommon();
        Species species = speciesService.getSpeciesById(id);
        if (!speciesService.checkSpeciesByNameId(speciesAPI.getScienceName(), speciesAPI.getVietnameseName(), speciesAPI.getOtherName(), id)) {
            Habitat habitat = habitatService.getHabitatByLongitudeLatitude(speciesAPI.getLongitude(), speciesAPI.getLatitude());
            Collection<Habitat> habitats = new ArrayList<Habitat>();
            int idHabitat;
            if (habitat == null) {
                habitat.setIdCreator(accountService.getAccountById(speciesAPI.getIdCreator()));
                habitat.setIdChecker(accountService.getAccountById(speciesAPI.getIdCreator()));
                habitat.setLatitude(speciesAPI.getLatitude());
                habitat.setLongitude(speciesAPI.getLongitude());
                idHabitat = habitatService.addHabitat(habitat);
                habitat = habitatService.getHabitatById(idHabitat);
                habitats.add(habitat);
            } else {
                habitats.add(habitat);
            }

            species.setIdCreator(accountService.getAccountById(speciesAPI.getIdCreator()));
            species.setAlertlevel(speciesAPI.getAlertlevel());
            species.setBiologicalBehavior(speciesAPI.getBiologicalBehavior());
            species.setFood(speciesAPI.getFood());
            species.setHabitatByIdSpecies(habitats);
            species.setNotation(speciesAPI.getNotation());
            species.setVietnameseName(speciesAPI.getVietnameseName());
            species.setScienceName(speciesAPI.getScienceName());
            species.setOtherName(species.getOtherName());
            species.setIndividualQuantity(species.getIndividualQuantity());
            species.setDiscovererName(speciesAPI.getDiscovererName());
            species.setMediumSize(speciesAPI.getMediumSize());
            species.setOrigin(speciesAPI.getOrigin());
            species.setOrtherTraits(speciesAPI.getOrtherTraits());
            species.setIdGenus(genusService.getGenusById(speciesAPI.getIdGenus()));
            species.setReproductionTraits(speciesAPI.getReproductionTraits());
            species.setSexualTraits(speciesAPI.getSexualTraits());
            species.setYearDiscover(speciesAPI.getYearDiscover());
            species.setDateCreate(speciesAPI.getDateCreate());
            species.setDateUpdate(dateCommon.getTimestampNow());
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(speciesAPI.getFileName());
            fileInfo.setEncodeString(speciesAPI.getEncodeString());
            if (fileInfo != null) {
                try {
                    ImageCommon imageCommon = new ImageCommon();
                    byte[] imageByteArray = imageCommon.decodeImage(fileInfo.getEncodeString());
                /*
                 * Write a image byte array into file system
                 */
//                String url = request.getSession().getServletContext().getRealPath("/resources/images/");
                    String url = "/home/duyle/Documents/biodiversity-backend/Animal/src/main/webapp/resources/";
                    File newFile = new File(url + fileInfo.getFileName());
                    FileOutputStream imageOutFile = new FileOutputStream(newFile);
                    imageOutFile.write(imageByteArray);
                    imageOutFile.close();

                    MicrosoftCommon microsoftCommon = new MicrosoftCommon();
                    Object[] objects = microsoftCommon.getTypeColorFromImage(newFile);
                    ArrayList<String> type = (ArrayList<String>) objects[0];
                    ArrayList<String> color = (ArrayList<String>) objects[1];
                    species.setImage(species.getImage() + "," + fileInfo.getFileName());
                    species.setType(species.getType() + ", " + StringUtils.collectionToDelimitedString(type, ", "));
                    species.setColor(species.getColor() + ", " + StringUtils.collectionToDelimitedString(color, ", "));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            speciesService.updateSpecies(species);
            Members members;
            species = speciesService.getSpeciesById(id);
            speciesAPI = new SpeciesAPI();
            speciesAPI.setId(species.getId());
            speciesAPI.setAlertlevel(species.getAlertlevel());
            speciesAPI.setBiologicalBehavior(species.getBiologicalBehavior());
            speciesAPI.setDateCreate(species.getDateCreate());
            speciesAPI.setDateUpdate(species.getDateUpdate().toString());
            speciesAPI.setDiscovererName(species.getDiscovererName());
            speciesAPI.setFood(species.getFood());
            speciesAPI.setIdChecker(species.getIdChecker().getId());
            speciesAPI.setIdCreator(species.getIdCreator().getId());
            speciesAPI.setIdGenus(species.getIdGenus().getId());
            speciesAPI.setImage(species.getImage());
            speciesAPI.setIndividualQuantity(species.getIndividualQuantity());
            speciesAPI.setMediumSize(species.getMediumSize());
            members = memberService.getMemberById(species.getIdChecker().getId());
            speciesAPI.setNameChecker(members.getFullName());
            members = memberService.getMemberById(species.getIdCreator().getId());
            speciesAPI.setNameCreator(members.getFullName());
            speciesAPI.setNotation(species.getNotation());
            speciesAPI.setOrigin(species.getOrigin());
            speciesAPI.setOrtherTraits(species.getOrtherTraits());
            speciesAPI.setOtherName(species.getOtherName());
            speciesAPI.setReproductionTraits(species.getReproductionTraits());
            speciesAPI.setScienceName(species.getScienceName());
            speciesAPI.setVietnameseName(species.getVietnameseName());
            speciesAPI.setScienceNameGenus(species.getIdGenus().getScienceName());
            speciesAPI.setVietnameseNameGenus(species.getIdGenus().getVietnameseName());
            speciesAPI.setSexualTraits(species.getSexualTraits());
            speciesAPI.setStatus(species.getStatus());
            speciesAPI.setYearDiscover(species.getYearDiscover());
            speciesAPI.setVietnameseNameFamily(species.getIdGenus().getIdFamily().getVietnameseName());
            speciesAPI.setIdFamily(species.getIdGenus().getIdFamily().getId());
            speciesAPI.setIdOrder(species.getIdGenus().getIdFamily().getIdOrder().getId());
            speciesAPI.setIdClass(species.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
            speciesAPI.setIdPhylum(species.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setSpecies(speciesAPI);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        } else {
            throw new ErrorException("Loài đã tồn tại!");
        }

    }

    @RequestMapping(value = "/api/species/date", method = RequestMethod.GET)
    private ResponseEntity<SpeciesJSON> getSpeciesLastest(@RequestParam(value = "date-update") String stringDate) {
        DateCommon dateCommon = new DateCommon();
        List<Species> speciesList = null;
        if (stringDate == null || stringDate == "") {
            speciesList = speciesService.getAllSpecies();
        } else {
            try {
                Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringDate);
                java.sql.Date date = new java.sql.Date(d.getTime());

                speciesList = speciesService.getSpeciesCreateOrUpdateLast(date);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

        }
        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
                speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setMaxDate(speciesService.getDateUpdateLastest().toString());
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/species/phylum/{id}", method = RequestMethod.GET)
    private ResponseEntity<SpeciesJSON> getSpeciesByPhylumId(@PathVariable("id") int id) {
        List<Species> speciesList = speciesService.getSpeciesByPhylumId(id);
        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
                speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setMaxDate(speciesService.getDateUpdateLastest().toString());
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/species/class/{id}", method = RequestMethod.GET)
    private ResponseEntity<SpeciesJSON> getSpeciesByClassId(@PathVariable("id") int id) {
        List<Species> speciesList = speciesService.getSpeciesByClassId(id);
        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
                speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setMaxDate(speciesService.getDateUpdateLastest().toString());
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/species/order/{id}", method = RequestMethod.GET)
    private ResponseEntity<SpeciesJSON> getSpeciesByOrderId(@PathVariable("id") int id) {
        List<Species> speciesList = speciesService.getSpeciesByOrderId(id);
        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
                speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setMaxDate(speciesService.getDateUpdateLastest().toString());
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/species/family/{id}", method = RequestMethod.GET)
    private ResponseEntity<SpeciesJSON> getSpeciesByFamilyId(@PathVariable("id") int id) {
        List<Species> speciesList = speciesService.getSpeciesByFamilyId(id);
        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
                speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setMaxDate(speciesService.getDateUpdateLastest().toString());
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/species/genus/{id}", method = RequestMethod.GET)
    private ResponseEntity<SpeciesJSON> getSpeciesByGenusId(@PathVariable("id") int id) {
        List<Species> speciesList = speciesService.getSpeciesByGenusId(id);
        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
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
                speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setMaxDate(speciesService.getDateUpdateLastest().toString());
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/species/list/share", method = RequestMethod.GET)
    private ResponseEntity<SpeciesJSON> getListSpeciesShare(@RequestParam("id") int id) throws ErrorException {
        List<Species> speciesList = speciesService.getListSpeciesShare(id);
        if (speciesList.size() == 0) {
            throw new ErrorException("Không tìm thấy dữ liệu");
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            ArrayList<Habitat> habitats = new ArrayList<Habitat>();
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
                speciesAPI = new SpeciesAPI();
                habitats.addAll(s.getHabitatByIdSpecies());
                speciesAPI.setLocationName(habitats.get(0).getLocationName());
                speciesAPI.setLatitude(habitats.get(0).getLatitude());
                speciesAPI.setLongitude(habitats.get(0).getLongitude());
                speciesAPI.setId(s.getId());
                speciesAPI.setAlertlevel(s.getAlertlevel());
                speciesAPI.setBiologicalBehavior(s.getBiologicalBehavior());
                speciesAPI.setDateCreate(s.getDateCreate());
                speciesAPI.setDateUpdate(s.getDateUpdate().toString());
                speciesAPI.setDiscovererName(s.getDiscovererName());
                speciesAPI.setFood(s.getFood());
                if (s.getIdChecker() != null) {
                    speciesAPI.setIdChecker(s.getIdChecker().getId());
                }
                if (s.getIdGenus() != null) {
                    speciesAPI.setIdGenus(s.getIdGenus().getId());
                    speciesAPI.setScienceNameGenus(s.getIdGenus().getScienceName());
                    speciesAPI.setVietnameseNameGenus(s.getIdGenus().getVietnameseName());
                    speciesAPI.setIdFamily(s.getIdGenus().getIdFamily().getId());
                    speciesAPI.setVietnameseNameFamily(s.getIdGenus().getIdFamily().getVietnameseName());
                    speciesAPI.setIdOrder(s.getIdGenus().getIdFamily().getIdOrder().getId());
                    speciesAPI.setIdClass(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getId());
                    speciesAPI.setIdPhylum(s.getIdGenus().getIdFamily().getIdOrder().getIdClass().getIdPhylum().getId());
                }
                speciesAPI.setImage(s.getImage());
                speciesAPI.setIndividualQuantity(s.getIndividualQuantity());
                speciesAPI.setMediumSize(s.getMediumSize());
                if (s.getIdChecker() != null) {
                    members = memberService.getMemberById(s.getIdChecker().getId());
                    if (members != null) {
                        speciesAPI.setNameChecker(members.getFullName());
                    }
                }
                speciesAPI.setNotation(s.getNotation());
                speciesAPI.setOrigin(s.getOrigin());
                speciesAPI.setOrtherTraits(s.getOrtherTraits());
                speciesAPI.setOtherName(s.getOtherName());
                speciesAPI.setReproductionTraits(s.getReproductionTraits());
                speciesAPI.setScienceName(s.getScienceName());
                speciesAPI.setVietnameseName(s.getVietnameseName());
                speciesAPI.setSexualTraits(s.getSexualTraits());
                speciesAPI.setStatus(s.getStatus());
                speciesAPI.setYearDiscover(s.getYearDiscover());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setMaxDate(speciesService.getDateUpdateLastest().toString());
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/species/share", method = RequestMethod.POST)
    private ResponseEntity<ResultRespone> shareSpecies(@RequestParam("id") int id, @RequestBody SpeciesShareAPI speciesShareAPI) {
        DateCommon dateCommon = new DateCommon();
        if (speciesShareAPI.getScienceName() != "" || speciesShareAPI.getVietnameseName() != "") {
            if (speciesService.checkSpeciesByName(speciesShareAPI.getScienceName(), speciesShareAPI.getVietnameseName(), "")) {
                return new ResponseEntity<ResultRespone>(new ResultRespone("Tên khoa học hoặc tên tiếng việt đã tồn " + "tại!"), HttpStatus.OK);
            }
        }
        Habitat habitat = habitatService.getHabitatByLongitudeLatitude(speciesShareAPI.getLongitude(), speciesShareAPI.getLatitude());
        Collection<Habitat> habitats = new ArrayList<Habitat>();
        Account account = accountService.getAccountById(id);
        int idHabitat;

        if (habitat == null) {
            habitat = new Habitat();
            if (speciesShareAPI.getLocationName() != null || speciesShareAPI.getLocationName() != "") {
                habitat.setLocationName(speciesShareAPI.getLocationName());
            }
            habitat.setLongitude(speciesShareAPI.getLongitude());
            habitat.setLatitude(speciesShareAPI.getLatitude());
            habitat.setIdChecker(accountService.getAccountById(id));
            habitat.setIdCreator(accountService.getAccountById(id));
            idHabitat = habitatService.addHabitat(habitat);
            habitat = habitatService.getHabitatById(idHabitat);
            habitats.add(habitat);
        } else {
            habitats.add(habitat);
        }
        Species species = new Species();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(speciesShareAPI.getFileName());
        fileInfo.setEncodeString(speciesShareAPI.getEncodeString());
        if (fileInfo != null) {
            try {
                ImageCommon imageCommon = new ImageCommon();
                byte[] imageByteArray = imageCommon.decodeImage(fileInfo.getEncodeString());
                /*
                 * Write a image byte array into file system
                 */
//                String url = request.getSession().getServletContext().getRealPath("/resources/images/");
                if (imageByteArray.length > 4000000) {
                    imageByteArray = imageCommon.resizeImage(imageByteArray);
                }
                String url = "/home/duyle/Documents/2016-winter-biodiversity-back/Animal/src/main/webapp" + "/resources/images/";
                File newFile = new File(url + fileInfo.getFileName());
                FileOutputStream imageOutFile = new FileOutputStream(newFile);
                imageOutFile.write(imageByteArray);
                imageOutFile.close();

                MicrosoftCommon microsoftCommon = new MicrosoftCommon();
                Object[] objects = microsoftCommon.getTypeColorFromImage(newFile);
                ArrayList<String> type = (ArrayList<String>) objects[0];
                ArrayList<String> color = (ArrayList<String>) objects[1];
                species.setImage(fileInfo.getFileName());
                species.setType(StringUtils.collectionToDelimitedString(type, ", "));
                species.setColor(StringUtils.collectionToDelimitedString(color, ", "));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        species.setIdCreator(account);
        species.setHabitatByIdSpecies(habitats);
        species.setStatus(0);
        species.setDiscovererName(memberService.getMemberById(id).getFullName());
        species.setYearDiscover(dateCommon.getDateNow());
        species.setDateCreate(dateCommon.getDateNow());
        species.setDateUpdate(dateCommon.getTimestampNow());
        if (speciesShareAPI.getMediumSize() != "") {
            species.setMediumSize(speciesShareAPI.getMediumSize());
        }
        if (speciesShareAPI.getOrtherTraits() != "") {
            species.setOrtherTraits(speciesShareAPI.getOrtherTraits());
        }
        if (speciesShareAPI.getIdGenus() != 0) {
            species.setIdGenus(genusService.getGenusById(speciesShareAPI.getIdGenus()));
        }
        if (speciesShareAPI.getReproductionTraits() != "") {
            species.setReproductionTraits(speciesShareAPI.getReproductionTraits());
        }
        if (speciesShareAPI.getSexualTraits() != "") {
            species.setSexualTraits(speciesShareAPI.getSexualTraits());
        }
        if (speciesShareAPI.getVietnameseName() != "") {
            species.setVietnameseName(speciesShareAPI.getVietnameseName());
        }
        if (speciesShareAPI.getScienceName() != "") {
            species.setScienceName(speciesShareAPI.getScienceName());
        }
        if (speciesShareAPI.getBiologicalBehavior() != "") {
            species.setBiologicalBehavior(speciesShareAPI.getBiologicalBehavior());
        }
        if (speciesShareAPI.getFood() != "") {
            species.setFood(speciesShareAPI.getFood());
        }
        Integer result = speciesService.addSpeciesShare(species);
        species = speciesService.getSpeciesById(result);
        if (species != null) {
            return new ResponseEntity<ResultRespone>(new ResultRespone("Chia sẻ thành công! Vui lòng chờ phê duyệt " + "từ chuyên gia!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<ResultRespone>(new ResultRespone("Thêm thất bại!"), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/species/list/approve", method = RequestMethod.GET)
    private ResponseEntity<SpeciesJSON> getListSpeciesApprove() {
        List<Species> speciesList = speciesService.getListSpeciesApprove();

        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            SpeciesAPI speciesAPI;
            Members members;
            List<Habitat> habitats = new ArrayList<Habitat>();
            List<SpeciesAPI> speciesAPIS = new ArrayList<SpeciesAPI>();
            for (Species s : speciesList) {
                speciesAPI = new SpeciesAPI();
                speciesAPI.setId(s.getId());
                speciesAPI.setAlertlevel(s.getAlertlevel());
                speciesAPI.setBiologicalBehavior(s.getBiologicalBehavior());
                speciesAPI.setDateCreate(s.getDateCreate());
                speciesAPI.setDateUpdate(s.getDateUpdate().toString());
                speciesAPI.setDiscovererName(s.getDiscovererName());
                speciesAPI.setFood(s.getFood());
                speciesAPI.setIdCreator(s.getIdCreator().getId());
                if (s.getIdGenus() != null) {
                    speciesAPI.setIdGenus(s.getIdGenus().getId());
                    speciesAPI.setScienceNameGenus(s.getIdGenus().getScienceName());
                    speciesAPI.setVietnameseNameGenus(s.getIdGenus().getVietnameseName());
                }
                speciesAPI.setImage(s.getImage());
                speciesAPI.setIndividualQuantity(s.getIndividualQuantity());
                speciesAPI.setMediumSize(s.getMediumSize());
                members = memberService.getMemberById(s.getIdCreator().getId());
                speciesAPI.setNameCreator(members.getFullName());
                speciesAPI.setNotation(s.getNotation());
                speciesAPI.setOrigin(s.getOrigin());
                speciesAPI.setOrtherTraits(s.getOrtherTraits());
                speciesAPI.setOtherName(s.getOtherName());
                speciesAPI.setReproductionTraits(s.getReproductionTraits());
                speciesAPI.setScienceName(s.getScienceName());
                speciesAPI.setVietnameseName(s.getVietnameseName());
                speciesAPI.setSexualTraits(s.getSexualTraits());
                speciesAPI.setStatus(s.getStatus());
                speciesAPI.setYearDiscover(s.getYearDiscover());
                habitats.addAll(s.getHabitatByIdSpecies());
                speciesAPI.setLatitude(habitats.get(0).getLatitude());
                speciesAPI.setLongitude(habitats.get(0).getLongitude());
                speciesAPIS.add(speciesAPI);
            }
            SpeciesJSON speciesJSON = new SpeciesJSON();
            speciesJSON.setSpecieses(speciesAPIS);
            return new ResponseEntity<SpeciesJSON>(speciesJSON, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/api/species/approve/{idSpecies}", method = RequestMethod.PUT)
    private ResponseEntity<ResultRespone> approveSpecies(@PathVariable("idSpecies") int idSpecies, @RequestParam("idChecker")
            int idChecker, @RequestBody SpeciesShareAPI speciesShareAPI) {
        DateCommon dateCommon = new DateCommon();
        if (speciesShareAPI.getScienceName() != "" || speciesShareAPI.getVietnameseName() != "") {
            if (speciesService.checkSpeciesByName(speciesShareAPI.getScienceName(), speciesShareAPI.getVietnameseName(), "")) {
                return new ResponseEntity<com.web.bean.ResultRespone>(new com.web.bean.ResultRespone("Tên khoa học hoặc tên tiếng việt đã tồn " + "tại!"), HttpStatus.OK);
            }
        }
        Account account = accountService.getAccountById(idChecker);
        Species species = new Species();
        species.setId(idSpecies);
        species.setIdChecker(account);
        species.setDateUpdate(dateCommon.getTimestampNow());
        if (speciesShareAPI.getMediumSize() != "") {
            species.setMediumSize(speciesShareAPI.getMediumSize());
        }
        if (speciesShareAPI.getOrtherTraits() != "") {
            species.setOrtherTraits(speciesShareAPI.getOrtherTraits());
        }
        if (speciesShareAPI.getIdGenus() != 0) {
            species.setIdGenus(genusService.getGenusById(speciesShareAPI.getIdGenus()));
        }
        if (speciesShareAPI.getReproductionTraits() != "") {
            species.setReproductionTraits(speciesShareAPI.getReproductionTraits());
        }
        if (speciesShareAPI.getSexualTraits() != "") {
            species.setSexualTraits(speciesShareAPI.getSexualTraits());
        }
        if (speciesShareAPI.getVietnameseName() != "") {
            species.setVietnameseName(speciesShareAPI.getVietnameseName());
        }
        if (speciesShareAPI.getScienceName() != "") {
            species.setScienceName(speciesShareAPI.getScienceName());
        }
        if (speciesShareAPI.getBiologicalBehavior() != "") {
            species.setBiologicalBehavior(speciesShareAPI.getBiologicalBehavior());
        }
        if (speciesShareAPI.getFood() != "") {
            species.setFood(speciesShareAPI.getFood());
        }
        speciesService.approveSpecies(species);
        species = speciesService.getSpeciesById(idSpecies);
        if (species.getStatus() == 1) {
            return new ResponseEntity<com.web.bean.ResultRespone>(new com.web.bean.ResultRespone("Phê duyệt thành " + "công!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<com.web.bean.ResultRespone>(new com.web.bean.ResultRespone("Phê duyệt thất " + "bại!"), HttpStatus.OK);
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
