package com.web.controller;

import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.bean.FileInfo;
import com.web.bean.MicrosoftJson;
import com.web.common.*;
import com.web.entity.JSON.SpeciesJSON;
import com.web.entity.api.SpeciesAPI;
import com.web.entity.backend.Members;
import com.web.entity.backend.Species;
import com.web.service.IMemberService;
import com.web.service.ISpeciesService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 28/02/2017.
 */

@Controller
public class ImageController {
    @Autowired
    ServletContext context;
    @Autowired
    private ISpeciesService speciesService;
    @Autowired
    private IMemberService memberService;

    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public ResponseEntity<SpeciesJSON> compareImage(@RequestBody FileInfo fileInfo) throws Exception {
        ArrayList<String> type = null;
        ArrayList<String> color = null;
        File newFile = null;
        String url = "/home/duyle/Documents/2016-winter-biodiversity-back/Animal/src/main/webapp/resources/upload/";
//        String url = "/home/duyle/Documents/";
        MicrosoftCommon microsoftCommon = new MicrosoftCommon();
        if (fileInfo != null) {
            try {
                ImageCommon imageCommon = new ImageCommon();
                byte[] imageByteArray = imageCommon.decodeImage(fileInfo.getEncodeString());
                if (imageByteArray.length > 4000000) {
                    byte[] imageByte = imageCommon.resizeImage(imageByteArray);
                    newFile = new File(url + fileInfo.getFileName());
                    FileOutputStream imageOutFile = new FileOutputStream(newFile);
                    imageOutFile.write(imageByte);
                    imageOutFile.close();
                } else {
                    newFile = new File(url + fileInfo.getFileName());
                    FileOutputStream imageOutFile = new FileOutputStream(newFile);
                    imageOutFile.write(imageByteArray);
                    imageOutFile.close();
                }
                Object[] objects = microsoftCommon.getTypeColorFromImage(newFile);
                type = (ArrayList<String>) objects[0];
                color = (ArrayList<String>) objects[1];
                newFile.delete();
            } catch (IOException e) {
                throw new ErrorException("Error I/O Buffer");
            }
        } else {
            throw new ErrorException("Không có file!");
        }

        List<Species> speciesList = speciesService.getSpeciesByTypeColor(type, color);

        if (speciesList.size() == 0) {
            return new ResponseEntity<SpeciesJSON>(HttpStatus.NO_CONTENT);
        } else {
            Members members;
            SpeciesAPI speciesAPI;
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

    @RequestMapping(value = "/api/upload1", method = RequestMethod.POST)
    public void compareImage1(@RequestBody FileInfo fileInfo) throws Exception {
        ArrayList<String> type = null;
        ArrayList<String> color = null;
        File newFile = null;
//        String url = "/home/duyle/Documents/2016-winter-biodiversity-back/Animal/src/main/webapp/resources/upload/";
        String url = "/home/duyle/Documents/";
        MicrosoftCommon microsoftCommon = new MicrosoftCommon();
        if (fileInfo != null) {
            try {
                ImageCommon imageCommon = new ImageCommon();
                byte[] imageByteArray = imageCommon.decodeImage(fileInfo.getEncodeString());
                if (imageByteArray.length > 4000000) {
                    byte[] imageByte = imageCommon.resizeImage(imageByteArray);
                    newFile = new File(url + fileInfo.getFileName());
                    FileOutputStream imageOutFile = new FileOutputStream(newFile);
                    imageOutFile.write(imageByte);
                    imageOutFile.close();
                } else {
                    newFile = new File(url + fileInfo.getFileName());
                    FileOutputStream imageOutFile = new FileOutputStream(newFile);
                    imageOutFile.write(imageByteArray);
                    imageOutFile.close();
                }
            } catch (IOException e) {
                throw new ErrorException("Error I/O Buffer");
            }
        } else {
            throw new ErrorException("Không có file!");
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

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleError2(MaxUploadSizeExceededException e) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("File vượt quá 50MB!");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }


}
