package com.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.entity.api.OrderAPI;
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
public class OrderController {

    @Value("${url.REST_SERVICE_URI}")
    private String REST_SERVICE_URI;

    @Value("${url.AUTH_SERVER_URI}")
    private String AUTH_SERVER_URI;

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String getAllOrders(HttpServletRequest httpServletRequest) {
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/orders");
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
                    List<OrderAPI> orderAPIS = new ArrayList<>();
                    OrderAPI orderAPI;
                    for (int i = 0; i < jsonArray.size(); i++) {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        orderAPI = new OrderAPI();
                        if (((long) jsonObject.get("id")) != 0) {
                            orderAPI.setId((long) jsonObject.get("id"));
                            orderAPI.setScienceName((String) jsonObject.get("scienceName"));
                            orderAPI.setVietnameseName((String) jsonObject.get("vietnameseName"));
                            orderAPIS.add(orderAPI);
                        }
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    String result = mapper.writeValueAsString(orderAPIS);
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

    @RequestMapping(value = "/order/class/{id}", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String getOrderByClassId(HttpServletRequest httpServletRequest, @PathVariable("id") int id) {
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/order/class/" + id);
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
                    List<OrderAPI> orderAPIS = new ArrayList<>();
                    OrderAPI orderAPI;
                    for (int i = 0; i < jsonArray.size(); i++) {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        orderAPI = new OrderAPI();
                        orderAPI.setId((long) jsonObject.get("id"));
                        orderAPI.setScienceName((String) jsonObject.get("scienceName"));
                        orderAPI.setVietnameseName((String) jsonObject.get("vietnameseName"));
                        orderAPIS.add(orderAPI);
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    String result = mapper.writeValueAsString(orderAPIS);
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

}
