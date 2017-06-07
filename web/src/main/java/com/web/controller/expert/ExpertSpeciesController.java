package com.web.controller.expert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bean.AuthTokenInfo;
import com.web.bean.Location;
import com.web.entity.api.AccountAPI;
import com.web.entity.api.SpeciesAPI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 5/2/17.
 */
@PropertySource("classpath:url.properties")
@Controller
public class ExpertSpeciesController {

    @Value("${url.REST_SERVICE_URI}")
    private String REST_SERVICE_URI;

    @Value("${url.AUTH_SERVER_URI}")
    private String AUTH_SERVER_URI;

    private AuthTokenInfo tokenInfo;

    private List<SpeciesAPI> listShare;
    private List<Location> locationList;

    @RequestMapping(value = "species/list/approve", method = RequestMethod.GET)
    public ModelAndView informationShare(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        tokenInfo = (AuthTokenInfo) session.getAttribute("token");
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (tokenInfo != null && accountAPI != null) {
            ModelAndView view = new ModelAndView("approve_species");
            return view;
        } else {
            ModelAndView view = new ModelAndView("index");
            String message = "Bạn chưa đăng nhập!";
            view.addObject("error", message);
            return view;
        }
    }

    @RequestMapping(value = "species/list/approve", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
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
                URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "/api/species/list/approve");
                builder.setParameter("access_token", tokenInfo.getAccess_token());
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
                            speciesAPI.setNameCreator((String) jsonObject.get("nameCreator"));
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

}