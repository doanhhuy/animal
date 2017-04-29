package com.web.controller;

import com.web.common.DateCommon;
import com.web.entity.JSON.AllJSON;
import com.web.entity.api.*;
import com.web.entity.backend.*;
import com.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by duyle on 3/22/17.
 */
@Controller
public class MainController {
    @Autowired
    private IPhylumService phylumService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IFamilyService familyService;
    @Autowired
    private IGenusService genusService;
    @Autowired
    private IMemberService memberService;

    @RequestMapping(value = "/api/all/date", method = RequestMethod.GET)
    public ResponseEntity<AllJSON> getAllPhylumClassOrderFamilyGenus(@RequestParam(value = "date-update") String stringDate) {
        List<Phylum> phylumList = null;
        List<Classes> classesList = null;
        List<Orders> ordersList = null;
        List<Families> familiesList = null;
        List<Genus> genusList = null;
        if (stringDate == null || stringDate == "") {
            phylumList = phylumService.getAllPhylums();
            classesList = classService.getAllClasses();
            ordersList = orderService.getAllOrders();
            familiesList = familyService.getAllFamilies();
            genusList = genusService.getAllGenuses();
        } else {
            try {
                Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringDate);
                java.sql.Date date = new java.sql.Date(d.getTime());
                ordersList = orderService.getOrdersCreateOrUpdateLast(date);
                familiesList = familyService.getFamiliesCreateOrUpdateLast(date);
                genusList = genusService.getGenusCreateOrUpdateLast(date);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

        }
        Members members;
        List<PhylumAPI> phylumAPIList = new ArrayList<PhylumAPI>();
        List<ClassAPI> classAPIS = new ArrayList<ClassAPI>();
        if (phylumList != null) {
            PhylumAPI phylumAPI = new PhylumAPI();
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
        }
        if (classesList != null) {
            ClassAPI classAPI = null;
            for (Classes p : classesList) {
                classAPI = new ClassAPI();
                classAPI.setId(p.getId());
                classAPI.setNotation(p.getNotation());
                classAPI.setIdPhylum(p.getIdPhylum().getId());
                classAPI.setSciencePhylum(p.getIdPhylum().getScienceName());
                classAPI.setVietnameseName(p.getIdPhylum().getVietnameseName());
                classAPI.setScienceName(p.getScienceName());
                classAPI.setVietnameseName(p.getVietnameseName());
                classAPI.setDiscovererName(p.getDiscovererName());
                classAPI.setYearDiscover(p.getYearDiscover());
                classAPI.setIdCreator(p.getIdCreator().getId());
                members = memberService.getMemberById(p.getIdCreator().getId());
                classAPI.setNameCreator(members.getFullName());
                classAPIS.add(classAPI);
            }
        }

        List<OrderAPI> orderAPIS = new ArrayList<OrderAPI>();
        OrderAPI orderAPI = null;
        for (Orders p : ordersList) {
            orderAPI = new OrderAPI();
            orderAPI.setId(p.getId());
            orderAPI.setNotation(p.getNotation());
            orderAPI.setIdClass(p.getIdClass().getId());
            orderAPI.setScienceNameClass(p.getIdClass().getScienceName());
            orderAPI.setVietnameseNameClass(p.getIdClass().getVietnameseName());
            orderAPI.setScienceName(p.getScienceName());
            orderAPI.setVietnameseName(p.getVietnameseName());
            orderAPI.setDiscovererName(p.getDiscovererName());
            orderAPI.setYearDiscover(p.getYearDiscover());
            orderAPI.setIdCreator(p.getIdCreator().getId());
            members = memberService.getMemberById(p.getIdCreator().getId());
            orderAPI.setNameCreator(members.getFullName());
            orderAPI.setDateUpdate(p.getDateUpdate().toString());
            orderAPI.setDateCreate(p.getDateCreate().toString());
            orderAPI.setStatus(p.getStatus());
            orderAPI.setIdChecker(p.getIdChecker().getId());
            members = memberService.getMemberById(p.getIdChecker().getId());
            orderAPI.setNameChecker(members.getFullName());
            orderAPIS.add(orderAPI);
        }

        List<FamilyAPI> familyAPIS = new ArrayList<FamilyAPI>();
        FamilyAPI familyAPI = null;
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

        List<GenusAPI> genusAPIS = new ArrayList<GenusAPI>();
        GenusAPI genusAPI = null;
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
        ArrayList<Timestamp> timestamps = new ArrayList<Timestamp>();
        timestamps.add(orderService.getDateUpdateLastest());
        timestamps.add(familyService.getDateUpdateLastest());
        timestamps.add(genusService.getDateUpdateLastest());
        DateCommon dateCommon = new DateCommon();
        AllJSON allJSON = new AllJSON();
        allJSON.setPhylumList(phylumAPIList);
        allJSON.setClassList(classAPIS);
        allJSON.setOrderList(orderAPIS);
        allJSON.setFamilyList(familyAPIS);
        allJSON.setGenusList(genusAPIS);
        allJSON.setMaxDate(dateCommon.compareTimestamp(timestamps).toString());
        return new ResponseEntity<AllJSON>(allJSON, HttpStatus.OK);
    }

}
