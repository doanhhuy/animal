package com.web.controller;

import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.common.DateCommon;
import com.web.entity.JSON.GenusJSON;
import com.web.entity.api.GenusAPI;
import com.web.entity.backend.Genus;
import com.web.entity.backend.Members;
import com.web.service.IAccountService;
import com.web.service.IFamilyService;
import com.web.service.IGenusService;
import com.web.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */

@Controller
public class GenusController {

    @Autowired
    private IGenusService genusService;
    @Autowired
    private IFamilyService familyService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IMemberService memberService;

    /*
       API get all of phylum (chi)
   */
    @RequestMapping(value = "/api/genus", method = RequestMethod.GET)
    public ResponseEntity<GenusJSON> getAllGenuses() {
        List<Genus> genusList = genusService.getAllGenuses();
        if (genusList.isEmpty()) {
            return new ResponseEntity<GenusJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<GenusAPI> genusAPIS = new ArrayList<GenusAPI>();
            GenusAPI genusAPI = null;
            Members members;
            for (Genus p : genusList) {
                genusAPI = new GenusAPI();
                genusAPI.setId(p.getId());
                genusAPI.setNotation(p.getNotation());
                genusAPI.setIdFamily(p.getIdFamily().getId());
                genusAPI.setVietnameseNameFamily(p.getIdFamily().getVietnameseName());
                genusAPI.setScienceNameFamily(p.getIdFamily().getScienceName());
                genusAPI.setScienceName(p.getScienceName());
                genusAPI.setVietnameseName(p.getVietnameseName());
                genusAPI.setDiscovererName(p.getDiscovererName());
                genusAPI.setYearDiscover(p.getYearDiscover());
                genusAPI.setIdCreator(p.getIdCreator().getId());
                members = memberService.getMemberById(p.getIdCreator().getId());
                genusAPI.setNameCreator(members.getFullName());
                genusAPI.setDateUpdate(p.getDateUpdate().toString());
                genusAPI.setDateCreate(p.getDateCreate());
                genusAPI.setStatus(p.getStatus());
                genusAPI.setIdChecker(p.getIdChecker().getId());
                members = memberService.getMemberById(p.getIdChecker().getId());
                genusAPI.setNameChecker(members.getFullName());
                genusAPIS.add(genusAPI);
            }
            GenusJSON genusJSON = new GenusJSON();
            genusJSON.setList(genusAPIS);
            return new ResponseEntity<GenusJSON>(genusJSON, HttpStatus.OK);
        }
    }

    /*
       API get phylum by Id (chi)
   */
    @RequestMapping(value = "/api/genus/{id}", method = RequestMethod.GET)
    public ResponseEntity<GenusJSON> getGenusById(@PathVariable("id") int id) {
        Genus genus = genusService.getGenusById(id);
        if (genus == null) {
            return new ResponseEntity<GenusJSON>(HttpStatus.NO_CONTENT);
        } else {
            Members members;
            GenusAPI genusAPI = new GenusAPI();
            genusAPI.setId(genus.getId());
            genusAPI.setNotation(genus.getNotation());
            genusAPI.setIdFamily(genus.getIdFamily().getId());
            genusAPI.setVietnameseNameFamily(genus.getIdFamily().getScienceName());
            genusAPI.setScienceNameFamily(genus.getIdFamily().getVietnameseName());
            genusAPI.setScienceName(genus.getScienceName());
            genusAPI.setVietnameseName(genus.getVietnameseName());
            genusAPI.setDiscovererName(genus.getDiscovererName());
            genusAPI.setYearDiscover(genus.getYearDiscover());
            genusAPI.setIdCreator(genus.getIdCreator().getId());
            members = memberService.getMemberById(genus.getIdCreator().getId());
            genusAPI.setNameCreator(members.getFullName());
            genusAPI.setDateUpdate(genus.getDateUpdate().toString());
            genusAPI.setDateCreate(genus.getDateCreate());
            genusAPI.setStatus(genus.getStatus());
            members = memberService.getMemberById(genus.getIdChecker().getId());
            genusAPI.setIdChecker(genus.getIdChecker().getId());
            genusAPI.setNameChecker(members.getFullName());
            GenusJSON genusJSON = new GenusJSON();
            genusJSON.setGenus(genusAPI);
            return new ResponseEntity<GenusJSON>(genusJSON, HttpStatus.OK);
        }
    }

    /*
       API create a new phylum (chi)
       Have check isExist
   */
    @RequestMapping(value = "/api/genus", method = RequestMethod.POST)
    public ResponseEntity<GenusJSON> createGenus(@RequestBody GenusAPI genusAPI) throws ErrorException {
        DateCommon dateCommon = new DateCommon();
        Genus genus = new Genus();
        genus.setNotation(genusAPI.getNotation());
        genus.setIdFamily(familyService.getFamilyById(genusAPI.getIdFamily()));
        genus.setScienceName(genusAPI.getScienceName());
        genus.setVietnameseName(genusAPI.getVietnameseName());
        genus.setYearDiscover(genusAPI.getYearDiscover());
        genus.setDiscovererName(genusAPI.getDiscovererName());
        genus.setStatus(genusAPI.getStatus());
        genus.setDateCreate(dateCommon.getDateNow());
        genus.setDateUpdate(dateCommon.getTimestampNow());
        if (genusService.isGenusExistByGenus(genus)) {
            throw new ErrorException("chi này đã tồn tại!");
        } else {
            Members members;
            genus.setIdCreator(accountService.getAccountById(genusAPI.getIdCreator()));
            genus.setIdChecker(accountService.getAccountById(genusAPI.getIdChecker()));
            int id = genusService.addGenus(genus);
            genus = genusService.getGenusById(id);
            genusAPI.setId(genus.getId());
            genusAPI.setNotation(genus.getNotation());
            genusAPI.setIdFamily(genus.getIdFamily().getId());
            genusAPI.setVietnameseNameFamily(genus.getIdFamily().getScienceName());
            genusAPI.setScienceNameFamily(genus.getIdFamily().getVietnameseName());
            genusAPI.setScienceName(genus.getScienceName());
            genusAPI.setVietnameseName(genus.getVietnameseName());
            genusAPI.setDiscovererName(genus.getDiscovererName());
            genusAPI.setYearDiscover(genus.getYearDiscover());
            genusAPI.setIdCreator(genus.getIdCreator().getId());
            members = memberService.getMemberById(genus.getIdCreator().getId());
            genusAPI.setNameCreator(members.getFullName());
            genusAPI.setDateUpdate(genus.getDateUpdate().toString());
            genusAPI.setDateCreate(genus.getDateCreate());
            genusAPI.setStatus(genus.getStatus());
            members = memberService.getMemberById(genus.getIdChecker().getId());
            genusAPI.setIdChecker(genus.getIdChecker().getId());
            genusAPI.setNameChecker(members.getFullName());
            GenusJSON genusJSON = new GenusJSON();
            genusJSON.setGenus(genusAPI);
            return new ResponseEntity<GenusJSON>(genusJSON, HttpStatus.OK);
        }
    }

    /*
       API  update  phylum (chi)
   */
    @RequestMapping(value = "/api/genus/{id}", method = RequestMethod.PUT)
    public ResponseEntity<GenusJSON> updateGenus(@PathVariable("id") int id, @RequestBody GenusAPI genusAPI) throws ErrorException {
        Genus genusById = genusService.getGenusById(id);
        if (genusById == null) {
            throw new ErrorException(" ID chi không tồn tại!");
        } else if (genusService.isGenusExistByGenus(genusById)) {
            throw new ErrorException("chi này đã tồn tại!");
        } else {
            DateCommon dateCommon = new DateCommon();
            Genus genus = new Genus();
            genus.setId(id);
            genus.setNotation(genusAPI.getNotation());
            genus.setIdFamily(familyService.getFamilyById(genusAPI.getIdFamily()));
            genus.setScienceName(genusAPI.getScienceName());
            genus.setVietnameseName(genusAPI.getVietnameseName());
            genus.setYearDiscover(genusAPI.getYearDiscover());
            genus.setDiscovererName(genusAPI.getDiscovererName());
            genus.setStatus(genusAPI.getStatus());
            genus.setDateUpdate(dateCommon.getTimestampNow());
            genus.setIdChecker(accountService.getAccountById(genusAPI.getIdChecker()));
            genus.setIdCreator(accountService.getAccountById(genusAPI.getIdCreator()));
            genusService.updateGenus(genus);
            Members members;
            genus = genusService.getGenusById(id);
            genusAPI.setId(genus.getId());
            genusAPI.setNotation(genus.getNotation());
            genusAPI.setIdFamily(genus.getIdFamily().getId());
            genusAPI.setVietnameseNameFamily(genus.getIdFamily().getScienceName());
            genusAPI.setScienceNameFamily(genus.getIdFamily().getVietnameseName());
            genusAPI.setScienceName(genus.getScienceName());
            genusAPI.setVietnameseName(genus.getVietnameseName());
            genusAPI.setDiscovererName(genus.getDiscovererName());
            genusAPI.setYearDiscover(genus.getYearDiscover());
            genusAPI.setIdCreator(genus.getIdCreator().getId());
            members = memberService.getMemberById(genus.getIdCreator().getId());
            genusAPI.setNameCreator(members.getFullName());
            genusAPI.setDateUpdate(genus.getDateUpdate().toString());
            genusAPI.setDateCreate(genus.getDateCreate());
            genusAPI.setStatus(genus.getStatus());
            members = memberService.getMemberById(genus.getIdChecker().getId());
            genusAPI.setIdChecker(genus.getIdChecker().getId());
            genusAPI.setNameChecker(members.getFullName());
            GenusJSON genusJSON = new GenusJSON();
            genusJSON.setGenus(genusAPI);
            return new ResponseEntity<GenusJSON>(genusJSON, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/api/genus/family/{id}", method = RequestMethod.GET)
    public ResponseEntity<GenusJSON> getGenusByFamilyId(@PathVariable("id") int id) {
        List<Genus> genusList = genusService.getGenusByFamilyId(id);
        if (genusList.isEmpty()) {
            return new ResponseEntity<GenusJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<GenusAPI> genusAPIS = new ArrayList<GenusAPI>();
            GenusAPI genusAPI = null;
            Members members;
            for (Genus p : genusList) {
                genusAPI = new GenusAPI();
                genusAPI.setId(p.getId());
                genusAPI.setNotation(p.getNotation());
                genusAPI.setIdFamily(p.getIdFamily().getId());
                genusAPI.setVietnameseNameFamily(p.getIdFamily().getVietnameseName());
                genusAPI.setScienceNameFamily(p.getIdFamily().getScienceName());
                genusAPI.setScienceName(p.getScienceName());
                genusAPI.setVietnameseName(p.getVietnameseName());
                genusAPI.setDiscovererName(p.getDiscovererName());
                genusAPI.setYearDiscover(p.getYearDiscover());
                genusAPI.setIdCreator(p.getIdCreator().getId());
                members = memberService.getMemberById(p.getIdCreator().getId());
                genusAPI.setNameCreator(members.getFullName());
                genusAPI.setDateUpdate(p.getDateUpdate().toString());
                genusAPI.setDateCreate(p.getDateCreate());
                genusAPI.setStatus(p.getStatus());
                genusAPI.setIdChecker(p.getIdChecker().getId());
                members = memberService.getMemberById(p.getIdChecker().getId());
                genusAPI.setNameChecker(members.getFullName());
                genusAPIS.add(genusAPI);
            }
            GenusJSON genusJSON = new GenusJSON();
            genusJSON.setList(genusAPIS);
            return new ResponseEntity<GenusJSON>(genusJSON, HttpStatus.OK);
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
