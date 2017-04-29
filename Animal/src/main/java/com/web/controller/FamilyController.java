package com.web.controller;

import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.common.DateCommon;
import com.web.entity.JSON.FamilyJSON;
import com.web.entity.api.FamilyAPI;
import com.web.entity.backend.Families;
import com.web.entity.backend.Members;
import com.web.service.IAccountService;
import com.web.service.IFamilyService;
import com.web.service.IMemberService;
import com.web.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
@Controller
public class FamilyController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IFamilyService familyService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IMemberService memberService;

    /*
       API get all of phylum (họ)
   */
    @RequestMapping(value = "/api/families", method = RequestMethod.GET)
    public ResponseEntity<FamilyJSON> getAllFamilies() {
        List<Families> familiesList = familyService.getAllFamilies();
        if (familiesList.isEmpty()) {
            return new ResponseEntity<FamilyJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<FamilyAPI> familyAPIS = new ArrayList<FamilyAPI>();
            FamilyAPI familyAPI = null;
            Members members;
            for (Families p : familiesList) {
                familyAPI = new FamilyAPI();
                familyAPI.setId(p.getId());
                familyAPI.setNotation(p.getNotation());
                familyAPI.setIdOrder(p.getIdOrder().getId());
                familyAPI.setScienceNameOrder(p.getIdOrder().getScienceName());
                familyAPI.setVietnameseNameOrder(p.getIdOrder().getVietnameseName());
                familyAPI.setScienceName(p.getScienceName());
                familyAPI.setVietnameseName(p.getVietnameseName());
                familyAPI.setDiscovererName(p.getDiscovererName());
                familyAPI.setYearDiscover(p.getYearDiscover());
                familyAPI.setIdCreator(p.getIdCreator().getId());
                members = memberService.getMemberById(p.getIdCreator().getId());
                familyAPI.setNameCreator(members.getFullName());
                familyAPI.setDateUpdate(p.getDateUpdate().toString());
                familyAPI.setDateCreate(p.getDateCreate());
                familyAPI.setStatus(p.getStatus());
                familyAPI.setIdChecker(p.getIdChecker().getId());
                members = memberService.getMemberById(p.getIdChecker().getId());
                familyAPI.setNameChecker(members.getFullName());
                familyAPIS.add(familyAPI);
            }
            FamilyJSON familyJSON = new FamilyJSON();
            familyJSON.setList(familyAPIS);
            return new ResponseEntity<FamilyJSON>(familyJSON, HttpStatus.OK);
        }
    }

    /*
       API get phylum by Id (họ)
   */
    @RequestMapping(value = "/api/families/{id}", method = RequestMethod.GET)
    public ResponseEntity<FamilyJSON> getFamilyById(@PathVariable("id") int id) {
        Families families = familyService.getFamilyById(id);
        Members members;
        if (families == null) {
            return new ResponseEntity<FamilyJSON>(HttpStatus.NO_CONTENT);
        } else {
            FamilyAPI familyAPI = new FamilyAPI();
            familyAPI.setId(families.getId());
            familyAPI.setNotation(families.getNotation());
            familyAPI.setIdOrder(families.getIdOrder().getId());
            familyAPI.setScienceNameOrder(families.getIdOrder().getScienceName());
            familyAPI.setVietnameseNameOrder(families.getIdOrder().getVietnameseName());
            familyAPI.setScienceName(families.getScienceName());
            familyAPI.setVietnameseName(families.getVietnameseName());
            familyAPI.setDiscovererName(families.getDiscovererName());
            familyAPI.setYearDiscover(families.getYearDiscover());
            familyAPI.setIdCreator(families.getIdCreator().getId());
            members = memberService.getMemberById(families.getIdCreator().getId());
            familyAPI.setNameCreator(members.getFullName());
            familyAPI.setDateUpdate(families.getDateUpdate().toString());
            familyAPI.setDateCreate(families.getDateCreate());
            familyAPI.setStatus(families.getStatus());
            familyAPI.setIdChecker(families.getIdChecker().getId());
            members = memberService.getMemberById(families.getIdChecker().getId());
            familyAPI.setNameChecker(members.getFullName());
            FamilyJSON familyJSON = new FamilyJSON();
            familyJSON.setFamily(familyAPI);
            return new ResponseEntity<FamilyJSON>(familyJSON, HttpStatus.OK);
        }
    }

    /*
       API create a new phylum (họ)
       Have check isExist
   */
    @RequestMapping(value = "/api/families", method = RequestMethod.POST)
    public ResponseEntity<FamilyJSON> createFamily(@RequestBody FamilyAPI familyAPI) throws ErrorException {
        Families families = new Families();
        families.setNotation(familyAPI.getNotation());
        families.setIdOrder(orderService.getOrderById(familyAPI.getIdOrder()));
        families.setScienceName(familyAPI.getScienceName());
        families.setVietnameseName(familyAPI.getVietnameseName());
        families.setYearDiscover(familyAPI.getYearDiscover());
        families.setDiscovererName(familyAPI.getDiscovererName());
        families.setStatus(familyAPI.getStatus());
        if (familyService.isFamilyExistByFamily(families)) {
            throw new ErrorException("họ này đã tồn tại!");
        } else {
            DateCommon dateCommon = new DateCommon();
            families.setIdCreator(accountService.getAccountById(familyAPI.getIdCreator()));
            families.setIdChecker(accountService.getAccountById(familyAPI.getIdChecker()));
            families.setDateCreate(dateCommon.getDateNow());
            families.setDateUpdate(dateCommon.getTimestampNow());
            int id = familyService.addFamily(families);
            Members members;
            families = familyService.getFamilyById(id);
            familyAPI.setId(families.getId());
            familyAPI.setNotation(families.getNotation());
            familyAPI.setIdOrder(families.getIdOrder().getId());
            familyAPI.setScienceNameOrder(families.getIdOrder().getScienceName());
            familyAPI.setVietnameseNameOrder(families.getIdOrder().getVietnameseName());
            familyAPI.setScienceName(families.getScienceName());
            familyAPI.setVietnameseName(families.getVietnameseName());
            familyAPI.setDiscovererName(families.getDiscovererName());
            familyAPI.setYearDiscover(families.getYearDiscover());
            familyAPI.setDateCreate(dateCommon.getDateNow());
            familyAPI.setIdCreator(families.getIdCreator().getId());
            members = memberService.getMemberById(families.getIdCreator().getId());
            familyAPI.setNameCreator(members.getFullName());
            familyAPI.setDateUpdate(families.getDateUpdate().toString());
            familyAPI.setDateCreate(families.getDateCreate());
            familyAPI.setStatus(families.getStatus());
            familyAPI.setIdChecker(families.getIdChecker().getId());
            members = memberService.getMemberById(families.getIdChecker().getId());
            familyAPI.setNameChecker(members.getFullName());
            FamilyJSON familyJSON = new FamilyJSON();
            familyJSON.setFamily(familyAPI);
            return new ResponseEntity<FamilyJSON>(familyJSON, HttpStatus.OK);
        }
    }

    /*
       API  update  phylum (họ)
   */
    @RequestMapping(value = "/api/families/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FamilyJSON> updateFamily(@PathVariable("id") int id, @RequestBody FamilyAPI familyAPI) throws ErrorException {
        Families familyById = familyService.getFamilyById(id);
        if (familyById == null) {
            throw new ErrorException(" ID họ không tồn tại!");
        } else if (familyService.isFamilyExistByFamily(familyById)) {
            throw new ErrorException("họ này đã tồn tại!");
        } else {
            DateCommon dateCommon = new DateCommon();
            Families families = new Families();
            families.setId(id);
            families.setNotation(familyAPI.getNotation());
            families.setIdOrder(orderService.getOrderById(familyAPI.getIdOrder()));
            families.setScienceName(familyAPI.getScienceName());
            families.setVietnameseName(familyAPI.getVietnameseName());
            families.setYearDiscover(familyAPI.getYearDiscover());
            families.setDiscovererName(familyAPI.getDiscovererName());
            families.setStatus(familyAPI.getStatus());
            families.setDateUpdate(dateCommon.getTimestampNow());
            families.setIdChecker(accountService.getAccountById(familyAPI.getIdChecker()));
            families.setIdCreator(accountService.getAccountById(familyAPI.getIdCreator()));
            familyService.updateFamily(families);
            Members members;
            families = familyService.getFamilyById(id);
            familyAPI.setId(families.getId());
            familyAPI.setNotation(families.getNotation());
            familyAPI.setIdOrder(families.getIdOrder().getId());
            familyAPI.setScienceNameOrder(families.getIdOrder().getScienceName());
            familyAPI.setVietnameseNameOrder(families.getIdOrder().getVietnameseName());
            familyAPI.setScienceName(families.getScienceName());
            familyAPI.setVietnameseName(families.getVietnameseName());
            familyAPI.setDiscovererName(families.getDiscovererName());
            familyAPI.setYearDiscover(families.getYearDiscover());
            familyAPI.setIdCreator(families.getIdCreator().getId());
            members = memberService.getMemberById(families.getIdCreator().getId());
            familyAPI.setNameCreator(members.getFullName());
            familyAPI.setDateUpdate(families.getDateUpdate().toString());
            familyAPI.setDateCreate(families.getDateCreate());
            familyAPI.setStatus(families.getStatus());
            familyAPI.setIdChecker(families.getIdChecker().getId());
            members = memberService.getMemberById(families.getIdChecker().getId());
            familyAPI.setNameChecker(members.getFullName());
            FamilyJSON familyJSON = new FamilyJSON();
            familyJSON.setFamily(familyAPI);
            return new ResponseEntity<FamilyJSON>(familyJSON, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/api/family/order/{id}", method = RequestMethod.GET)
    public ResponseEntity<FamilyJSON> getFamilyByOrderId(@PathVariable("id") int id) {
        List<Families> familiesList = familyService.getFamilyByOrderId(id);
        if (familiesList.isEmpty()) {
            return new ResponseEntity<FamilyJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<FamilyAPI> familyAPIS = new ArrayList<FamilyAPI>();
            FamilyAPI familyAPI = null;
            Members members;
            for (Families p : familiesList) {
                familyAPI = new FamilyAPI();
                familyAPI.setId(p.getId());
                familyAPI.setScienceName(p.getScienceName());
                familyAPI.setVietnameseName(p.getVietnameseName());
                familyAPI.setDiscovererName(p.getDiscovererName());
                familyAPI.setYearDiscover(p.getYearDiscover());
                familyAPI.setIdCreator(p.getIdCreator().getId());
                members = memberService.getMemberById(p.getIdCreator().getId());
                familyAPI.setNameCreator(members.getFullName());
                familyAPI.setDateUpdate(p.getDateUpdate().toString());
                familyAPI.setDateCreate(p.getDateCreate());
                familyAPI.setStatus(p.getStatus());
                familyAPI.setIdChecker(p.getIdChecker().getId());
                members = memberService.getMemberById(p.getIdChecker().getId());
                familyAPI.setNameChecker(members.getFullName());
                familyAPIS.add(familyAPI);
            }
            FamilyJSON familyJSON = new FamilyJSON();
            familyJSON.setList(familyAPIS);
            return new ResponseEntity<FamilyJSON>(familyJSON, HttpStatus.OK);
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
