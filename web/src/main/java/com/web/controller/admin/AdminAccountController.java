package com.web.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bean.AuthTokenInfo;
import com.web.bean.Location;
import com.web.common.DateCommon;
import com.web.entity.api.AccountAPI;
import com.web.entity.api.SpeciesAPI;
import com.web.model.LoginForm;
import com.web.model.RegisterForm;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by duyle on 3/26/17.
 */

@PropertySource("classpath:url.properties")
@Controller
public class AdminAccountController {

    @Value("${url.REST_SERVICE_URI}")
    private String REST_SERVICE_URI;

    @Value("${url.AUTH_SERVER_URI}")
    private String AUTH_SERVER_URI;

    private AuthTokenInfo tokenInfo;

    /*
     * Prepare HTTP Headers.
     */

    @RequestMapping(value = "/manageraccount", method = RequestMethod.GET)
    public ModelAndView showListAccount(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        tokenInfo = (AuthTokenInfo) session.getAttribute("token");
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (accountAPI == null || tokenInfo == null) {
            ModelAndView view = new ModelAndView("login");
            view.addObject("error", "Bạn chưa đăng nhập");
            return view;
        } else {
            HttpClient httpClient = HttpClients.createDefault();
            try {
                URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "/api/accounts");
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
                        ModelAndView view = new ModelAndView("manager_account");
                        view.addObject("error", "Không có dữ liệu");
                        return view;
                    } else if (object.containsKey("error")) {
                        ModelAndView view = new ModelAndView("login");
                        view.addObject("error", "Bạn chưa đăng nhập");
                        session.invalidate();
                        return view;
                    } else {
                        DateCommon dateCommon = new DateCommon();
                        JSONArray jsonArray = (JSONArray) object.get("accounts");
                        JSONObject jsonObject;
                        ArrayList<AccountAPI> accountAPIS = new ArrayList<>();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            jsonObject = (JSONObject) jsonArray.get(i);
                            accountAPI = new AccountAPI();
                            accountAPI.setIdMember((long) jsonObject.get("idMember"));
                            accountAPI.setRoleName((String) jsonObject.get("roleName"));
                            accountAPI.setIdRole((long) jsonObject.get("idRole"));
                            accountAPI.setBirthday(dateCommon.convertStringToDateSql((String) jsonObject.get("birthday")));
                            accountAPI.setPhonenumber((String) jsonObject.get("phonenumber"));
                            accountAPI.setAddress((String) jsonObject.get("address"));
                            accountAPI.setDetail((String) jsonObject.get("detail"));
                            accountAPI.setEmail((String) jsonObject.get("email"));
                            accountAPI.setFullName((String) jsonObject.get("fullName"));
                            accountAPI.setPassword((String) jsonObject.get("password"));
                            accountAPI.setId((long) jsonObject.get("id"));
                            accountAPI.setUsername((String) jsonObject.get("username"));
                            accountAPIS.add(accountAPI);
                        }

                        ModelAndView view = new ModelAndView("manager_account");
                        view.addObject("list", accountAPIS);
                        return view;
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
