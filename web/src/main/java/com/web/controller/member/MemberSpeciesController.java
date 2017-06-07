package com.web.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bean.AuthTokenInfo;
import com.web.bean.FileInfo;
import com.web.bean.Location;
import com.web.common.ImageCommon;
import com.web.entity.api.AccountAPI;
import com.web.entity.api.GenusAPI;
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
import org.springframework.http.MediaType;
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
 * Created by duyle on 5/2/17.
 */
@PropertySource("classpath:url.properties")
@Controller
public class MemberSpeciesController {

    @Value("${url.REST_SERVICE_URI}")
    private String REST_SERVICE_URI;

    @Value("${url.AUTH_SERVER_URI}")
    private String AUTH_SERVER_URI;

    private AuthTokenInfo tokenInfo;

    private List<SpeciesAPI> listShare;
    private List<Location> locationList;

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

    @RequestMapping(value = "/sharespecies", method = RequestMethod.GET)
    public ModelAndView shareSpecies(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        tokenInfo = (AuthTokenInfo) session.getAttribute("token");
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (tokenInfo != null && accountAPI != null) {
            HttpClient httpClient = HttpClients.createDefault();
            try {
                URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/genus");
                URI uri = builder.build();
                HttpGet request = new HttpGet(uri);
                HttpResponse response = httpClient.execute(request);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String json = EntityUtils.toString(entity);
                    JSONParser parser = new JSONParser();
                    JSONObject object = (JSONObject) parser.parse(json);
                    if (object.isEmpty()) {
                        ModelAndView view = new ModelAndView("share_species");
                        view.addObject("error", "Bạn không thể thêm loài vào lúc này!");
                        return view;
                    } else {
                        JSONArray jsonArray = (JSONArray) object.get("list");
                        JSONObject jsonObject;
                        List<GenusAPI> genusAPIS = new ArrayList<>();
                        GenusAPI genusAPI;
                        for (int i = 0; i < jsonArray.size(); i++) {
                            jsonObject = (JSONObject) jsonArray.get(i);
                            genusAPI = new GenusAPI();
                            if (((long) jsonObject.get("status")) != 0) {
                                genusAPI.setId((long) jsonObject.get("id"));
                                genusAPI.setScienceName((String) jsonObject.get("scienceName"));
                                genusAPI.setVietnameseName((String) jsonObject.get("vietnameseName"));
                                genusAPIS.add(genusAPI);
                            }
                        }
                        ModelAndView view = new ModelAndView("share_species");
                        view.addObject("listGenus", genusAPIS);
                        return view;
                    }
                } else {
                    ModelAndView view = new ModelAndView("share_species");
                    view.addObject("error", "Bạn không thể thêm loài vào lúc này!");
                    return view;
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
        } else {
            ModelAndView view = new ModelAndView("index");
            String message = "Bạn chưa đăng nhập!";
            view.addObject("error", message);
            return view;
        }
        return null;
    }

    @RequestMapping(value = "/sharespecies", headers = "content-type=multipart/*", method = RequestMethod.POST, produces = "text/html; charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String doShareSpecies(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile image, @RequestParam("vietnameseName") String vietnameseName, @RequestParam("scienceName") String scienceName, @RequestParam("location") String location, @RequestParam("reproductionTraits") String reproductionTraits, @RequestParam("sexualTraits") String sexualTraits, @RequestParam("ortherTraits") String ortherTraits, @RequestParam("biologicalBehavior") String biologicalBehavior, @RequestParam("mediumSize") String mediumSize, @RequestParam("origin") String origin, @RequestParam("food") String food, @RequestParam("idGenus") String idGenus) {
        HttpSession session = httpServletRequest.getSession();
        tokenInfo = (AuthTokenInfo) session.getAttribute("token");
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        try {
            String temp = location;
            ImageCommon imageCommon = new ImageCommon();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(image.getOriginalFilename());
            fileInfo.setEncodeString(imageCommon.encodeImage(image.getBytes()));
            HttpClient httpClient = HttpClients.createDefault();
            URIBuilder builder = null;
            builder = new URIBuilder(REST_SERVICE_URI + "/api/species/share");
            builder.setParameter("id", String.valueOf(accountAPI.getId()));
            builder.setParameter("access_token", tokenInfo.getAccess_token());
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            JSONObject jsonObject = new JSONObject();
            String[] parts = location.split(" - ");
            jsonObject.put("fileName", fileInfo.getFileName());
            jsonObject.put("encodeString", fileInfo.getEncodeString());
            jsonObject.put("latitude", parts[0]);
            jsonObject.put("longitude", parts[1]);
            if (vietnameseName != "") {
                jsonObject.put("vietnameseName", vietnameseName);
            }
            if (scienceName != "") {
                jsonObject.put("scienceName", scienceName);
            }
            if (idGenus != "") {
                jsonObject.put("idGenus", idGenus);
            }
            if (reproductionTraits != "") {
                jsonObject.put("reproductionTraits", reproductionTraits);
            }
            if (sexualTraits != "") {
                jsonObject.put("sexualTraits", sexualTraits);
            }
            if (ortherTraits != "") {
                jsonObject.put("ortherTraits", ortherTraits);
            }
            if (biologicalBehavior != "") {
                jsonObject.put("biologicalBehavior", biologicalBehavior);
            }
            if (mediumSize != "") {
                jsonObject.put("mediumSize", mediumSize);
            }
            if (food != "") {
                jsonObject.put("food", food);
            }
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
                    if (object.containsKey("error")) {
                        ObjectMapper mapper = new ObjectMapper();
                        String result = mapper.writeValueAsString("Bạn chưa đăng nhập!");
                        session.invalidate();
                        return result;
                    } else {
                        String message = (String) object.get("message");
                        ObjectMapper mapper = new ObjectMapper();
                        String result = mapper.writeValueAsString(message);
                        return result;
                    }
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
