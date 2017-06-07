package com.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bean.AuthTokenInfo;
import com.web.entity.api.HabitatAPI;
import com.web.entity.api.SpeciesAPI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 4/30/17.
 */

@PropertySource("classpath:url.properties")
@Controller
public class HabitatController {

    @Value("${url.REST_SERVICE_URI}")
    private String REST_SERVICE_URI;

    @Value("${url.AUTH_SERVER_URI}")
    private String AUTH_SERVER_URI;

    private AuthTokenInfo tokenInfo;

    @RequestMapping(value = "/species/habitat/", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String getHabitatByIdSpecies(HttpServletRequest httpServletRequest) {
        String idSpecies = httpServletRequest.getParameter("idSpecies");
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/species/habitat/" + idSpecies);
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
                        JSONArray jsonArray = (JSONArray) object.get("habitats");
                        List<HabitatAPI> habitatAPIS = new ArrayList<>();
                        JSONObject jsonObject;
                        HabitatAPI habitatAPI;
                        for (int i = 0; i < jsonArray.size(); i++) {
                            jsonObject = (JSONObject) jsonArray.get(i);
                            habitatAPI = new HabitatAPI();
                            habitatAPI.setId((Long) jsonObject.get("id"));
                            habitatAPI.setLocationName((String) jsonObject.get("locationName"));
                            habitatAPI.setLatitude((Double) jsonObject.get("latitude"));
                            habitatAPI.setLongitude((Double) jsonObject.get("longitude"));
                            habitatAPIS.add(habitatAPI);
                        }
                        ObjectMapper mapper = new ObjectMapper();
                        String result = mapper.writeValueAsString(habitatAPIS);
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

}
