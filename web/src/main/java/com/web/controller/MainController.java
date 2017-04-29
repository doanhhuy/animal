package com.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bean.AuthTokenInfo;
import com.web.bean.FileInfo;
import com.web.common.DateCommon;
import com.web.common.ImageCommon;
import com.web.entity.api.*;
import com.web.model.AccountForm;
import com.web.model.LoginForm;
import com.web.model.RegisterForm;
import com.web.model.ResetPasswordForm;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by duyle on 02/03/2017.
 */

@PropertySource("classpath:url.properties")
@Controller
public class MainController {

    @Value("${url.REST_SERVICE_URI}")
    private String REST_SERVICE_URI;

    @Value("${url.AUTH_SERVER_URI}")
    private String AUTH_SERVER_URI;

    private String server = "http://localhost:8081/api/phylums/11";
    private RestTemplate rest;
    private AuthTokenInfo tokenInfo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMap() {
        return "index";
    }

//    @RequestMapping(value = "/test")
//    public ModelAndView showPhylums() {
//        HttpEntity<String> requestEntity = new HttpEntity<String>("", getHeaders());
//        ResponseEntity<PhylumJSON> responseEntity = rest.exchange(server, HttpMethod.GET, requestEntity, PhylumJSON.class);
//        PhylumJSON name = responseEntity.getBody();
//        ModelAndView model = new ModelAndView("test");
//        List<PhylumAPI> phylumAPIS = name.getPhylums();
//        List<PhylumForm> phylumForms = new ArrayList<>();
//        PhylumForm phylumForm;
//        for (PhylumAPI phylumAPI : phylumAPIS) {
//            phylumForm = new PhylumForm();
//            phylumForm.setId(phylumAPI.getId());
//            phylumForm.setDiscovererName(phylumAPI.getDiscovererName());
//            phylumForms.add(phylumForm);
//        }
//
//        model.addObject("phylumForms", phylumForms);
//
//        return model;
//    }

//    @RequestMapping(value = "test", method = RequestMethod.GET)
//    public String test() {
//        return "test";
//    }

    @RequestMapping(value = "/searchimage", method = RequestMethod.GET)
    public ModelAndView searchImage() {
        ModelAndView view = new ModelAndView("search_image");
        return view;
    }

    @RequestMapping(value = "/searchimage", headers = "content-type=multipart/*", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String doSearchImage(@RequestParam("file") MultipartFile image) {
        try {
            ImageCommon imageCommon = new ImageCommon();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(image.getOriginalFilename());
            fileInfo.setEncodeString(imageCommon.encodeImage(image.getBytes()));
            HttpClient httpClient = HttpClients.createDefault();
            URIBuilder builder = null;
            builder = new URIBuilder(REST_SERVICE_URI + "api/upload");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileName", fileInfo.getFileName());
            jsonObject.put("encodeString", fileInfo.getEncodeString());
            StringEntity stringEntity = new StringEntity(jsonObject.toString(), "application/json", "UTF-8");
            request.setHeader("content-type", "application/json");
            request.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.size() > 0) {
                    JSONArray jsonArray = (JSONArray) object.get("specieses");
                    List<SpeciesAPI> speciesAPIS = new ArrayList<>();
                    SpeciesAPI speciesAPI;
                    for (int i = 0; i < jsonArray.size(); i++) {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        speciesAPI = new SpeciesAPI();
                        speciesAPI.setId((Long) jsonObject.get("id"));
                        speciesAPI.setAlertlevel((String) jsonObject.get("alertlevel"));
                        speciesAPI.setBiologicalBehavior((String) jsonObject.get("biologicalBehavior"));
                        speciesAPI.setDateCreate((String) jsonObject.get("dateCreate"));
                        speciesAPI.setDateUpdate((String) jsonObject.get("dateUpdate"));
                        speciesAPI.setDiscovererName((String) jsonObject.get("discovererName"));
                        speciesAPI.setFood((String) jsonObject.get("food"));
                        speciesAPI.setIdChecker((Long) jsonObject.get("idChecker"));
                        speciesAPI.setIdCreator((Long) jsonObject.get("idCreator"));
                        speciesAPI.setIdGenus((Long) jsonObject.get("idGenus"));
                        speciesAPI.setImage((String) jsonObject.get("image"));
                        speciesAPI.setIndividualQuantity((String) jsonObject.get("individualQuantity"));
                        speciesAPI.setMediumSize((String) jsonObject.get("mediumSize"));
                        speciesAPI.setNameChecker((String) jsonObject.get("nameChecker"));
                        speciesAPI.setNameCreator((String) jsonObject.get("nameCreator"));
                        speciesAPI.setNotation((String) jsonObject.get("notation"));
                        speciesAPI.setOrigin((String) jsonObject.get("origin"));
                        speciesAPI.setOrtherTraits((String) jsonObject.get("ortherTraits"));
                        speciesAPI.setOtherName((String) jsonObject.get("otherName"));
                        speciesAPI.setReproductionTraits((String) jsonObject.get("reproductionTraits"));
                        speciesAPI.setScienceName((String) jsonObject.get("scienceName"));
                        speciesAPI.setVietnameseName((String) jsonObject.get("vietnameseName"));
                        speciesAPI.setScienceNameGenus((String) jsonObject.get("scienceNameGenus"));
                        speciesAPI.setVietnameseNameGenus((String) jsonObject.get("vietnameseNameGenus"));
                        speciesAPI.setSexualTraits((String) jsonObject.get("sexualTraits"));
                        speciesAPI.setStatus((long) jsonObject.get("status"));
                        speciesAPI.setYearDiscover((String) jsonObject.get("yearDiscover"));
                        speciesAPI.setVietnameseNameFamily((String) jsonObject.get("vietnameseNameFamily"));
                        speciesAPIS.add(speciesAPI);
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    String result = mapper.writeValueAsString(speciesAPIS);
                    return result;
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ModelAndView filter() {
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/phylums");
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.isEmpty()) {
                    return null;
                } else {
                    JSONArray jsonArray = (JSONArray) object.get("list");
                    JSONObject jsonObject;
                    List<PhylumAPI> phylumAPIS = new ArrayList<>();
                    PhylumAPI phylumAPI;
                    for (int i = 0; i < jsonArray.size(); i++) {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        phylumAPI = new PhylumAPI();
                        phylumAPI.setId((long) jsonObject.get("id"));
                        phylumAPI.setScienceName((String) jsonObject.get("scienceName"));
                        phylumAPI.setVietnameseName((String) jsonObject.get("vietnameseName"));
                        phylumAPIS.add(phylumAPI);
                    }
                    ModelAndView view = new ModelAndView("filter");
                    view.addObject("phylumAPIS", phylumAPIS);
                    return view;
                }
            } else {
                return null;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}