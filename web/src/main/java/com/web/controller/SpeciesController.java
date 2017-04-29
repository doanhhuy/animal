package com.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bean.AuthTokenInfo;
import com.web.bean.FileInfo;
import com.web.bean.Location;
import com.web.common.ImageCommon;
import com.web.entity.api.AccountAPI;
import com.web.entity.api.SpeciesAPI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 3/26/17.
 */

@PropertySource("classpath:url.properties")
@Controller
public class SpeciesController {

    @Value("${url.REST_SERVICE_URI}")
    private String REST_SERVICE_URI;

    @Value("${url.AUTH_SERVER_URI}")
    private String AUTH_SERVER_URI;

    private AuthTokenInfo tokenInfo;

    private String url = "api/species/all";

    private List<SpeciesAPI> listShare;
    private List<Location> locationList;

    @RequestMapping(value = "/libraryspecies", method = RequestMethod.GET)
    public ModelAndView showLibrarySpecies() {
        ModelAndView view = new ModelAndView("library_species");
        return view;
    }

    @RequestMapping(value = "/libraryspecies/{offset}", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String doShowLibrarySpecies(HttpServletRequest httpServletRequest, @PathVariable("offset") int offset) {
        String temp = httpServletRequest.getParameter("filter");
        HttpSession session = httpServletRequest.getSession();
        String filterSession = (String) session.getAttribute("filter");
        if (filterSession == null) {
            session.setAttribute("filter", "0");
        }
        String filter = (String) session.getAttribute("filter");
        if (!filter.equals(temp)) {
            filter = temp;
            if (filter.equals("0")) {
                url = "api/species/all";
            } else if (filter.equals("1")) {
                url = "api/species/rare";
            } else {
                url = "api/species/normal";
            }
            offset = offset * 60;
            session.setAttribute("filter", temp);
        } else {
            offset = offset * 60;
        }
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + url);
            builder.addParameter("offset", String.valueOf(offset));
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.size() > 0) {
                    if (object.containsKey("message")) {
                        ObjectMapper mapper = new ObjectMapper();
                        String result = mapper.writeValueAsString(object.get("message"));
                        return null;
                    } else {
                        JSONArray jsonArray = (JSONArray) object.get("specieses");
                        List<SpeciesAPI> speciesAPIS = new ArrayList<>();
                        JSONObject jsonObject;
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

    @RequestMapping(value = "/speciessearch", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String searchSpecies(HttpServletRequest httpServletRequest) {
        String key = (String) httpServletRequest.getParameter("key");
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/species/search/");
            builder.setParameter("key", key);
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.size() > 0) {
                    JSONArray jsonArray = (JSONArray) object.get("specieses");
                    List<SpeciesAPI> speciesAPIS = new ArrayList<>();
                    JSONObject jsonObject;
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
        } catch (URISyntaxException e)

        {
            e.printStackTrace();
        } catch (ClientProtocolException e)

        {
            e.printStackTrace();
        } catch (IOException e)

        {
            e.printStackTrace();
        } catch (ParseException e)

        {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/detailspecies/{id}", method = RequestMethod.GET)
    private ModelAndView showDetailSpeciesById(@PathVariable("id") int id) {
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/species/" + id);
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.size() > 0) {
                    if (object.containsKey("message")) {
                        ModelAndView view = new ModelAndView("detail_species");
                        String message = "Không tìm thấy dữ liệu";
                        view.addObject("message", message);
                        return view;
                    } else {
                        JSONObject jsonObject = (JSONObject) object.get("species");
                        SpeciesAPI speciesAPI = new SpeciesAPI();
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
                        ModelAndView view = new ModelAndView("detail_species");
                        view.addObject("species", speciesAPI);
                        return view;
                    }
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

    @RequestMapping(value = "/species/habitat", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String getSpeciesByHabitat(HttpServletRequest httpServletRequest) {
        String id = httpServletRequest.getParameter("id");
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/habitat/species/" + id);
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
                    JSONArray jsonArray = (JSONArray) object.get("specieses");
                    JSONObject jsonObject;
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

    @RequestMapping(value = "/species/type", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String getSpeciesByType(HttpServletRequest httpServletRequest) {
        int idType = Integer.valueOf(httpServletRequest.getParameter("idType"));
        String idData = httpServletRequest.getParameter("idData");
        String path = null;
        if (idType == 1) {
            path = "/api/species/phylum/";
        } else if (idType == 2) {
            path = "/api/species/class/";
        } else if (idType == 3) {
            path = "/api/species/order/";
        } else if (idType == 4) {
            path = "/api/species/family/";
        } else {
            path = "/api/species/genus/";
        }
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + path + idData);
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
                    JSONArray jsonArray = (JSONArray) object.get("specieses");
                    JSONObject jsonObject;
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

    @RequestMapping(value = "species/list/share", method = RequestMethod.GET)
    public ModelAndView informationShare(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        tokenInfo = (AuthTokenInfo) session.getAttribute("token");
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (tokenInfo != null && accountAPI != null) {
            ModelAndView view = new ModelAndView("information_share");
            return view;
        } else {
            ModelAndView view = new ModelAndView("index");
            String message = "Bạn chưa đăng nhập!";
            view.addObject("error", message);
            return view;
        }
    }

    @RequestMapping(value = "species/list/share", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String showInformationShare(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        tokenInfo = (AuthTokenInfo) session.getAttribute("token");
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (accountAPI == null || tokenInfo == null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String result = null;
                result = mapper.writeValueAsString("Bạn chưa đăng nhập!");
                return result;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            HttpClient httpClient = HttpClients.createDefault();
            try {
                URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "/api/species/list/share");
                builder.setParameter("id", String.valueOf(accountAPI.getId()));
                builder.setParameter("access_token", tokenInfo.getAccess_token());
                URI uri = builder.build();
                HttpGet request = new HttpGet(uri);
                HttpResponse response = httpClient.execute(request);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String json = EntityUtils.toString(entity);
                    JSONParser parser = new JSONParser();
                    JSONObject object = (JSONObject) parser.parse(json);
                    if (object.containsKey("message")) {
                        return null;
                    } else if (object.containsKey("error")) {
                        ObjectMapper mapper = new ObjectMapper();
                        String result = mapper.writeValueAsString("Bạn chưa đăng nhập!");
                        session.invalidate();
                        return result;
                    } else {
                        JSONArray jsonArray = (JSONArray) object.get("specieses");
                        JSONObject jsonObject;
                        listShare = new ArrayList<>();
                        locationList = new ArrayList<>();
                        SpeciesAPI speciesAPI;
                        Location location;
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
                            speciesAPI.setIdGenus((Long) jsonObject.get("idGenus"));
                            speciesAPI.setImage((String) jsonObject.get("image"));
                            speciesAPI.setIndividualQuantity((String) jsonObject.get("individualQuantity"));
                            speciesAPI.setMediumSize((String) jsonObject.get("mediumSize"));
                            speciesAPI.setNameChecker((String) jsonObject.get("nameChecker"));
                            speciesAPI.setOrigin((String) jsonObject.get("origin"));
                            speciesAPI.setOrtherTraits((String) jsonObject.get("ortherTraits"));
                            speciesAPI.setOtherName((String) jsonObject.get("otherName"));
                            speciesAPI.setReproductionTraits((String) jsonObject.get("reproductionTraits"));
                            speciesAPI.setScienceName((String) jsonObject.get("scienceName"));
                            speciesAPI.setVietnameseName((String) jsonObject.get("vietnameseName"));
                            speciesAPI.setScienceNameGenus((String) jsonObject.get("scienceNameGenus"));
                            speciesAPI.setVietnameseNameGenus((String) jsonObject.get("vietnameseNameGenus"));
                            speciesAPI.setSexualTraits((String) jsonObject.get("sexualTraits"));
                            speciesAPI.setVietnameseNameFamily((String) jsonObject.get("vietnameseNameFamily"));
                            speciesAPI.setLocationName((String) jsonObject.get("locationName"));
                            speciesAPI.setLatitude((Double) jsonObject.get("latitude"));
                            speciesAPI.setLongitude((Double) jsonObject.get("longitude"));
                            location = new Location();
                            location.setLocationName(speciesAPI.getLocationName());
                            location.setLatitude(speciesAPI.getLatitude());
                            location.setLongitude(speciesAPI.getLongitude());
                            locationList.add(location);
                            listShare.add(speciesAPI);
                        }
                        ObjectMapper mapper = new ObjectMapper();
                        String result = mapper.writeValueAsString(listShare);
                        return result;
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "species/list/share/map", method = RequestMethod.GET)
    public ModelAndView showShareMap(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        tokenInfo = (AuthTokenInfo) session.getAttribute("token");
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (accountAPI == null || tokenInfo == null) {
            ModelAndView view = new ModelAndView("index");
            view.addObject("error", "Bạn chưa đăng nhập!");
            return view;
        } else {
            ModelAndView view = new ModelAndView("map_share");
            view.addObject("listShare", listShare);
            return view;
        }
    }

    @RequestMapping(value = "/sharespecies", method = RequestMethod.GET)
    public ModelAndView shareSpecies() {
        ModelAndView view = new ModelAndView("share_species");
        return view;
    }

    @RequestMapping(value = "/sharespecies", headers = "content-type=multipart/*", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String doShareSpecies(@RequestParam("file") MultipartFile image) {
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

}
