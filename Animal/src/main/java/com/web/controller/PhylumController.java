package com.web.controller;

import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.entity.JSON.PhylumJSON;
import com.web.entity.api.PhylumAPI;
import com.web.entity.backend.Members;
import com.web.entity.backend.Phylum;
import com.web.service.IAccountService;
import com.web.service.IMemberService;
import com.web.service.IPhylumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 14/02/2017.
 */

@Controller
public class PhylumController {

    @Autowired
    private IPhylumService phylumService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private IMemberService memberService;

    /*
        API get all of phylum (Ngành)
    */
    @RequestMapping(value = "/api/phylums", method = RequestMethod.GET)
    public ResponseEntity<PhylumJSON> getAllPhylums() {
        List<Phylum> phylumList = phylumService.getAllPhylums();

        if (phylumList.isEmpty()) {
            return new ResponseEntity<PhylumJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<PhylumAPI> phylumAPIList = new ArrayList<PhylumAPI>();
            PhylumAPI phylumAPI = new PhylumAPI();
            Members members;
            for (Phylum p : phylumList) {
                phylumAPI = new PhylumAPI();
                phylumAPI.setId(p.getId());
                phylumAPI.setNotation(p.getNotation());
                phylumAPI.setIdKingdom(p.getIdKingdom());
                phylumAPI.setScienceName(p.getScienceName());
                phylumAPI.setVietnameseName(p.getVietnameseName());
                phylumAPI.setDiscovererName(p.getDiscovererName());
                phylumAPI.setYearDiscover(p.getYearDiscover());
                phylumAPI.setIdCreator(p.getIdCreator().getId());
                members = memberService.getMemberById(p.getIdCreator().getId());
                phylumAPI.setNameCreator(members.getFullName());
                phylumAPIList.add(phylumAPI);
            }

            PhylumJSON phylumJSON = new PhylumJSON();
            phylumJSON.setList(phylumAPIList);
            return new ResponseEntity<PhylumJSON>(phylumJSON, HttpStatus.OK);
        }
    }

    /*
       API get phylum by Id (Ngành)
   */
    @RequestMapping(value = "/api/phylums/{id}", method = RequestMethod.GET)
    public ResponseEntity<PhylumJSON> getPhylumById(@PathVariable("id") int id) {
        Phylum phylum = phylumService.getPhylumById(id);

        if (phylum == null) {
            return new ResponseEntity<PhylumJSON>(HttpStatus.NO_CONTENT);
        } else {
            Members members;
            PhylumAPI phylumAPI = new PhylumAPI();
            phylumAPI.setId(phylum.getId());
            phylumAPI.setNotation(phylum.getNotation());
            phylumAPI.setIdKingdom(phylum.getIdKingdom());
            phylumAPI.setScienceName(phylum.getScienceName());
            phylumAPI.setVietnameseName(phylum.getVietnameseName());
            phylumAPI.setDiscovererName(phylum.getDiscovererName());
            phylumAPI.setYearDiscover(phylum.getYearDiscover());
            phylumAPI.setIdCreator(phylum.getIdCreator().getId());
            members = memberService.getMemberById(phylum.getIdCreator().getId());
            phylumAPI.setNameCreator(members.getFullName());
            PhylumJSON phylumJSON = new PhylumJSON();
            phylumJSON.setPhylum(phylumAPI);
            return new ResponseEntity<PhylumJSON>(phylumJSON, HttpStatus.OK);
        }
    }


    /*
       API create a new phylum (Ngành)
       Have check isExist
   */
    @RequestMapping(value = "/api/phylums", method = RequestMethod.POST)
    public ResponseEntity<PhylumJSON> createPhylum(@RequestBody PhylumAPI phylumAPI) throws ErrorException {
        Phylum phylum = new Phylum();
        phylum.setNotation(phylumAPI.getNotation());
        phylum.setIdKingdom(phylumAPI.getIdKingdom());
        phylum.setScienceName(phylumAPI.getScienceName());
        phylum.setVietnameseName(phylumAPI.getVietnameseName());
        phylum.setYearDiscover(phylumAPI.getYearDiscover());
        phylum.setDiscovererName(phylumAPI.getDiscovererName());
        if (phylumService.isPhylumExistByScienceNameVietnameseName(phylumAPI.getScienceName(), phylumAPI.getVietnameseName())) {
            throw new ErrorException("Ngành này đã tồn tại!");
        } else {
            phylum.setIdCreator(accountService.getAccountById(phylumAPI.getIdCreator()));
            int result = phylumService.addPhylum(phylum);
            if (result == 0) {
                throw new ErrorException("Thêm ngành không thành công!");
            } else {
                Members members;
                PhylumAPI phylumAPI1 = new PhylumAPI();
                Phylum phylum1 = phylumService.getPhylumById(result);
                phylumAPI.setId(phylum.getId());
                phylumAPI.setNotation(phylum1.getNotation());
                phylumAPI.setIdKingdom(phylum1.getIdKingdom());
                phylumAPI.setScienceName(phylum1.getScienceName());
                phylumAPI.setVietnameseName(phylum1.getVietnameseName());
                phylumAPI.setDiscovererName(phylum1.getDiscovererName());
                phylumAPI.setYearDiscover(phylum1.getYearDiscover());
                phylumAPI.setIdCreator(phylum1.getIdCreator().getId());
                members = memberService.getMemberById(phylum1.getIdCreator().getId());
                phylumAPI.setNameCreator(members.getFullName());
                PhylumJSON phylumJSON = new PhylumJSON();
                phylumJSON.setPhylum(phylumAPI);
                return new ResponseEntity<PhylumJSON>(phylumJSON, HttpStatus.CREATED);
            }
        }
    }

    /*
       API  update  phylum (Ngành)
   */
    @RequestMapping(value = "/api/phylums/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PhylumJSON> updatePhylum(@PathVariable("id") int id, @RequestBody PhylumAPI phylumAPI) throws ErrorException {
        Phylum phylumById = phylumService.getPhylumById(id);
        Phylum phylum = new Phylum();
        phylum.setNotation(phylumAPI.getNotation());
        phylum.setIdKingdom(phylumAPI.getIdKingdom());
        phylum.setScienceName(phylumAPI.getScienceName());
        phylum.setVietnameseName(phylumAPI.getVietnameseName());
        phylum.setYearDiscover(phylumAPI.getYearDiscover());
        phylum.setDiscovererName(phylumAPI.getDiscovererName());
        phylum.setIdCreator(accountService.getAccountById(phylumAPI.getIdCreator()));
        if (phylumById == null) {
            throw new ErrorException("ID ngành không tồn tại!");
        } else if (phylumService.isPhylumExistByPhylum(phylum)) {
            throw new ErrorException("Ngành đã tồn tại!");
        } else {
            Members members;
            phylum.setId(id);
            phylumService.updatePhylum(phylum);
            phylumById = phylumService.getPhylumById(id);
            phylumAPI.setId(phylum.getId());
            phylumAPI.setNotation(phylumById.getNotation());
            phylumAPI.setIdKingdom(phylumById.getIdKingdom());
            phylumAPI.setScienceName(phylumById.getScienceName());
            phylumAPI.setVietnameseName(phylumById.getVietnameseName());
            phylumAPI.setDiscovererName(phylumById.getDiscovererName());
            phylumAPI.setYearDiscover(phylumById.getYearDiscover());
            phylumAPI.setIdCreator(phylumById.getIdCreator().getId());
            members = memberService.getMemberById(phylumById.getIdCreator().getId());
            phylumAPI.setNameCreator(members.getFullName());
            PhylumJSON phylumJSON = new PhylumJSON();
            phylumJSON.setPhylum(phylumAPI);
            return new ResponseEntity<PhylumJSON>(phylumJSON, HttpStatus.OK);
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
