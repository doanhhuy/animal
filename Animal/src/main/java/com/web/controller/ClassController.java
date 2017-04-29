package com.web.controller;

import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.entity.JSON.ClassJSON;
import com.web.entity.api.ClassAPI;
import com.web.entity.backend.Classes;
import com.web.entity.backend.Members;
import com.web.service.IAccountService;
import com.web.service.IClassService;
import com.web.service.IMemberService;
import com.web.service.IPhylumService;
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
public class ClassController {

    @Autowired
    private IPhylumService phylumService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IMemberService memberService;

    /*
       API get all of phylum (Lớp)
   */
    @RequestMapping(value = "/api/classes", method = RequestMethod.GET)
    public ResponseEntity<ClassJSON> getAllClasses() {
        List<Classes> classesList = classService.getAllClasses();

        if (classesList.isEmpty()) {
            return new ResponseEntity<ClassJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<ClassAPI> classAPIS = new ArrayList<ClassAPI>();
            ClassAPI classAPI = null;
            Members members;
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
            ClassJSON classJSON = new ClassJSON();
            classJSON.setList(classAPIS);
            return new ResponseEntity<ClassJSON>(classJSON, HttpStatus.OK);
        }
    }

    /*
       API get phylum by Id (Lớp)
   */
    @RequestMapping(value = "/api/classes/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClassJSON> getClassById(@PathVariable("id") int id) {
        Classes classes = classService.getClassById(id);
        if (classes == null) {
            return new ResponseEntity<ClassJSON>(HttpStatus.NO_CONTENT);
        } else {
            ClassAPI classAPI = new ClassAPI();
            Members members;
            classAPI.setId(classes.getId());
            classAPI.setNotation(classes.getNotation());
            classAPI.setIdPhylum(classes.getIdPhylum().getId());
            classAPI.setSciencePhylum(classes.getIdPhylum().getScienceName());
            classAPI.setVietnameseName(classes.getIdPhylum().getVietnameseName());
            classAPI.setScienceName(classes.getScienceName());
            classAPI.setVietnameseName(classes.getVietnameseName());
            classAPI.setDiscovererName(classes.getDiscovererName());
            classAPI.setYearDiscover(classes.getYearDiscover());
            classAPI.setIdCreator(classes.getIdCreator().getId());
            members = memberService.getMemberById(classes.getIdCreator().getId());
            classAPI.setNameCreator(members.getFullName());
            ClassJSON classJSON = new ClassJSON();
            classJSON.setClasses(classAPI);
            return new ResponseEntity<ClassJSON>(classJSON, HttpStatus.OK);
        }
    }

    /*
       API create a new phylum (Lớp)
       Have check isExist
   */
    @RequestMapping(value = "/api/classes", method = RequestMethod.POST)
    public ResponseEntity<ClassJSON> createPhylum(@RequestBody ClassAPI classAPI) throws ErrorException {
        Classes classes = new Classes();
        classes.setNotation(classAPI.getNotation());
        classes.setIdPhylum(phylumService.getPhylumById(classAPI.getIdPhylum()));
        classes.setScienceName(classAPI.getScienceName());
        classes.setVietnameseName(classAPI.getVietnameseName());
        classes.setYearDiscover(classAPI.getYearDiscover());
        classes.setDiscovererName(classAPI.getDiscovererName());
        if (classService.isClassExistByClass(classes)) {
            throw new ErrorException("Lớp này đã tồn tại!");
        } else {
            classes.setIdCreator(accountService.getAccountById(classAPI.getIdCreator()));
            int id = classService.addClass(classes);
            classes = classService.getClassById(id);
            Members members;
            classAPI.setId(classes.getId());
            classAPI.setNotation(classes.getNotation());
            classAPI.setIdPhylum(classes.getIdPhylum().getId());
            classAPI.setSciencePhylum(classes.getIdPhylum().getScienceName());
            classAPI.setVietnameseName(classes.getIdPhylum().getVietnameseName());
            classAPI.setScienceName(classes.getScienceName());
            classAPI.setVietnameseName(classes.getVietnameseName());
            classAPI.setDiscovererName(classes.getDiscovererName());
            classAPI.setYearDiscover(classes.getYearDiscover());
            classAPI.setIdCreator(classes.getIdCreator().getId());
            members = memberService.getMemberById(classes.getIdCreator().getId());
            classAPI.setNameCreator(members.getFullName());
            ClassJSON classJSON = new ClassJSON();
            classJSON.setClasses(classAPI);
            return new ResponseEntity<ClassJSON>(classJSON, HttpStatus.OK);
        }
    }

    /*
       API  update  phylum (Lớp)
   */
    @RequestMapping(value = "/api/classes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClassJSON> updatePhylum(@PathVariable("id") int id, @RequestBody ClassAPI classAPI) throws ErrorException {
        Classes classesById = classService.getClassById(id);
        if (classesById == null) {
            throw new ErrorException(" ID lớp không tồn tại!");
        } else if (classService.isClassExistByClass(classesById)) {
            throw new ErrorException("Lớp này đã tồn tại!");
        } else {
            Classes classes = new Classes();
            classes.setId(id);
            classes.setNotation(classAPI.getNotation());
            classes.setIdPhylum(phylumService.getPhylumById(classAPI.getIdPhylum()));
            classes.setScienceName(classAPI.getScienceName());
            classes.setVietnameseName(classAPI.getVietnameseName());
            classes.setYearDiscover(classAPI.getYearDiscover());
            classes.setDiscovererName(classAPI.getDiscovererName());
            classes.setIdCreator(accountService.getAccountById(classAPI.getIdCreator()));
            classService.updateClass(classes);

            classes = classService.getClassById(id);
            Members members;
            classAPI.setId(classes.getId());
            classAPI.setNotation(classes.getNotation());
            classAPI.setIdPhylum(classes.getIdPhylum().getId());
            classAPI.setSciencePhylum(classes.getIdPhylum().getScienceName());
            classAPI.setVietnameseName(classes.getIdPhylum().getVietnameseName());
            classAPI.setScienceName(classes.getScienceName());
            classAPI.setVietnameseName(classes.getVietnameseName());
            classAPI.setDiscovererName(classes.getDiscovererName());
            classAPI.setYearDiscover(classes.getYearDiscover());
            classAPI.setIdCreator(classes.getIdCreator().getId());
            members = memberService.getMemberById(classes.getIdCreator().getId());
            classAPI.setNameCreator(members.getFullName());
            ClassJSON classJSON = new ClassJSON();
            classJSON.setClasses(classAPI);
            return new ResponseEntity<ClassJSON>(classJSON, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/api/class/phylum/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClassJSON> getClassByPhylumId(@PathVariable("id") int id) {
        List<Classes> classesList = classService.getClassByPhylumId(id);
        if (classesList.isEmpty()) {
            return new ResponseEntity<ClassJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<ClassAPI> classAPIS = new ArrayList<ClassAPI>();
            ClassAPI classAPI = null;
            Members members;
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
            ClassJSON classJSON = new ClassJSON();
            classJSON.setList(classAPIS);
            return new ResponseEntity<ClassJSON>(classJSON, HttpStatus.OK);
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
