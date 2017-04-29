package com.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bean.AuthTokenInfo;
import com.web.common.DateCommon;
import com.web.entity.api.AccountAPI;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Created by duyle on 3/26/17.
 */

@PropertySource("classpath:url.properties")
@Controller
public class AccountController {

    @Value("${url.REST_SERVICE_URI}")
    private String REST_SERVICE_URI;

    @Value("${url.AUTH_SERVER_URI}")
    private String AUTH_SERVER_URI;

    private AuthTokenInfo tokenInfo;

    /*
     * Prepare HTTP Headers.
     */
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    /*
     * Add HTTP Authorization header, using Basic-Authentication to send client-credentials.
     */
    private String getHeadersWithClientCredentials() {
        String plainClientCredentials = "my-trusted-client:secret";
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

//        HttpHeaders headers = getHeaders();
//        headers.add("Authorization", "Basic " + base64ClientCredentials);
        return base64ClientCredentials;
    }

    @SuppressWarnings({"unchecked"})
    private AuthTokenInfo sendTokenRequest(String username, String password) {

        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(AUTH_SERVER_URI);
            builder.setParameter("grant_type", "password");
            builder.setParameter("username", username);
            builder.setParameter("password", password);

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Authorization", "Basic " + getHeadersWithClientCredentials());

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(json);
                if (jsonObject.size() > 2) {
                    AuthTokenInfo tokenInfo = new AuthTokenInfo();
                    tokenInfo.setAccess_token((String) jsonObject.get("access_token"));
                    tokenInfo.setToken_type((String) jsonObject.get("token_type"));
                    tokenInfo.setRefresh_token((String) jsonObject.get("refresh_token"));
                    tokenInfo.setExpires_in((long) jsonObject.get("expires_in"));
                    tokenInfo.setScope((String) jsonObject.get("scope"));
                    return tokenInfo;
                } else {
                    return null;
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
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("login");
        LoginForm loginForm = new LoginForm();
        view.addObject("loginForm", loginForm);
        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String doLogin(HttpServletRequest httpServletRequest, @ModelAttribute("loginForm") LoginForm loginForm) throws JsonProcessingException {
        tokenInfo = sendTokenRequest(loginForm.getUsername(), loginForm.getPassword());
        if (tokenInfo == null) {
            ModelAndView view = new ModelAndView("login");
            view.addObject("loginForm", loginForm);
            view.addObject("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString("<script>$(document).ready(function () {$.simplyToast" + "('Tên hoặc mật khẩu không đúng!', " + "'danger');});</script>");
            return result;
        } else {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("username", loginForm.getUsername());
            session.setAttribute("token", tokenInfo);
            HttpClient httpClient = HttpClients.createDefault();
            try {
                URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/accounts/username/" + loginForm.getUsername());
                builder.setParameter("access_token", tokenInfo.getAccess_token());
                URI uri = builder.build();
                HttpGet request = new HttpGet(uri);
                HttpResponse response = httpClient.execute(request);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String json = EntityUtils.toString(entity);
                    JSONParser parser = new JSONParser();
                    JSONObject object = (JSONObject) parser.parse(json);
                    DateCommon dateCommon = new DateCommon();
                    JSONObject jsonObject = (JSONObject) object.get("account");
                    AccountAPI accountAPI = new AccountAPI();
                    if (jsonObject.containsKey("fullName")) {
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
                        accountAPI.setUsername(loginForm.getUsername());
                    } else {
                        accountAPI.setRoleName((String) jsonObject.get("roleName"));
                        accountAPI.setIdRole((long) jsonObject.get("idRole"));
                        accountAPI.setId((long) jsonObject.get("id"));
                        accountAPI.setPassword((String) jsonObject.get("password"));
                        accountAPI.setEmail((String) jsonObject.get("email"));
                        accountAPI.setUsername(loginForm.getUsername());
                    }
                    session.setAttribute("accountAPI", accountAPI);
                }
                ObjectMapper mapper = new ObjectMapper();
                String result = mapper.writeValueAsString("<script>$( location ).attr(\"href\", \"/\");" + "</script>");
                return result;
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
//        ModelAndView view = new ModelAndView("login");
//        view.addObject("loginForm", loginForm);
//        view.addObject("error", "Đăng nhập thất bại!");
//        return view;
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString("<script>$(document).ready(function () {$.simplyToast" + "('Đăng nhập không thành công!', " + "'danger');});</script>");
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView doLogout(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        if (tokenInfo == null) {
            ModelAndView view = new ModelAndView("login");
            view.addObject("message", "Đăng xuất thành công!");
            return view;
        } else {
            HttpSession session = httpServletRequest.getSession();
            HttpClient httpClient = HttpClients.createDefault();
            try {
                URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/logout");
                builder.setParameter("access_token", tokenInfo.getAccess_token());
                URI uri = builder.build();
                HttpPost request = new HttpPost(uri);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("access_token", tokenInfo.getAccess_token());
                jsonObject.put("token_type", tokenInfo.getToken_type());
                jsonObject.put("refresh_token", tokenInfo.getRefresh_token());
                jsonObject.put("expires_in", tokenInfo.getExpires_in());
                jsonObject.put("scope", tokenInfo.getScope());
                StringEntity stringEntity = new StringEntity(jsonObject.toString());
                request.setHeader("content-type", "application/json");
                request.setEntity(stringEntity);
                HttpResponse response = httpClient.execute(request);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String json = EntityUtils.toString(entity);
                    JSONParser parser = new JSONParser();
                    JSONObject object = (JSONObject) parser.parse(json);
                    if (object.containsKey("message")) {
                        String message = (String) object.get("message");
                        if ("Đăng xuất thành công!".equals(message)) {
                            ModelAndView view = new ModelAndView("index");
                            tokenInfo = null;
                            session.invalidate();
                            view.addObject("message", object.get("message"));
                            return view;
                        } else {
                            ModelAndView view = new ModelAndView("login");
                            view.addObject("error", object.get("message"));
                            return view;
                        }
                    } else {
//                        ModelAndView view = new ModelAndView("login");
                        return null;
                    }
                } else {
                    return null;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
//        ModelAndView view = new ModelAndView("login");
//        view.addObject("loginForm", loginForm);
//        view.addObject("error", "Đăng nhập thất bại!");
//        return view;
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView view = new ModelAndView("login");
        RegisterForm registerForm = new RegisterForm();
        view.addObject("registerForm", registerForm);
        return view;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView doRegister(@ModelAttribute("registerForm") RegisterForm registerForm) {

        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/register/");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", registerForm.getUsername());
            jsonObject.put("password", registerForm.getPassword());
            jsonObject.put("email", registerForm.getEmail());
            StringEntity stringEntity = new StringEntity(jsonObject.toString());
            request.setHeader("content-type", "application/json");
            request.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.containsKey("message")) {
                    ModelAndView view = new ModelAndView("login");
                    view.addObject("registerForm", registerForm);
                    view.addObject("error", object.get("message"));
                    return view;
                } else {
                    JSONObject account = (JSONObject) object.get("account");
                    ModelAndView view = new ModelAndView("login");
                    LoginForm loginForm = new LoginForm();
                    loginForm.setUsername((String) account.get("username"));
                    view.addObject("loginForm", loginForm);
                    view.addObject("message", "Vui lòng check mail và kích hoạt tài khoản!");
                    return view;
                }
            } else {
                return null;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
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

    @RequestMapping(value = "/activation/{email:.+}", method = RequestMethod.GET)
    public ModelAndView activationAccount(@PathVariable("email") String email) {
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/activation/" + email);
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.size() > 0) {
                    String message = (String) object.get("message");
                    ModelAndView view = new ModelAndView("activation_result");
                    view.addObject("message", message);
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

    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public ModelAndView resetPassword() {
        ModelAndView view = new ModelAndView("reset_password");
        ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
        view.addObject("resetPasswordForm", resetPasswordForm);
        return view;
    }

    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ModelAndView doResetPassword(@ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm) {

        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/resetpassword/" + resetPasswordForm.getEmail());
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.size() > 0) {
                    String message = (String) object.get("message");
                    if ("Email không tồn tại!".equals(message)) {
                        ModelAndView view = new ModelAndView("reset_password");
                        view.addObject("error", message);
                        return view;
                    } else {
                        ModelAndView view = new ModelAndView("reset_password_success");
                        view.addObject("message", message);
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

    @RequestMapping(value = "/updatepassword/{email:.+}", method = RequestMethod.GET)
    public ModelAndView updatePassword(@PathVariable("email") String email) {
        ModelAndView view = new ModelAndView("update_password");
        ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
        view.addObject("resetPasswordForm", resetPasswordForm);
        view.addObject("email", email);
        return view;
    }

    @RequestMapping(value = "/updatepassword/{email:.+}", method = RequestMethod.POST)
    public ModelAndView doUpdatePassword(@PathVariable("email") String email, @ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm) {

        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(REST_SERVICE_URI + "api/updatepassword/" + email);
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("activationCode", resetPasswordForm.getCode());
            jsonObject.put("password", resetPasswordForm.getPassword());
            StringEntity stringEntity = new StringEntity(jsonObject.toString());
            stringEntity.setContentType("application/json");
            stringEntity.setContentEncoding("UTF-8");
            request.setHeader("content-type", "application/json");
            request.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(json);
                if (object.size() > 0) {
                    String message = (String) object.get("message");
                    if ("Mật khẩu đã được thay đổi!".equals(message)) {
                        ModelAndView view = new ModelAndView("reset_password_success");
                        view.addObject("message", message);
                        return view;
                    } else if ("Email không tồn tại!".equals(message)) {
                        ModelAndView view = new ModelAndView("redirect:/updatepassword/" + email);
                        view.addObject("error", 0);
                        return view;
                    } else if ("Link này đã hết hạn!".equals(message)) {
                        ModelAndView view = new ModelAndView("redirect:/updatepassword/" + email);
                        view.addObject("error", 1);
                        return view;
                    } else {
                        ModelAndView view = new ModelAndView("redirect:/updatepassword/" + email);
                        view.addObject("error", 2);
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

    @RequestMapping(value = "/userprofile", method = RequestMethod.GET)
    private ModelAndView showUserProfile(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (accountAPI == null || tokenInfo == null) {
            ModelAndView view = new ModelAndView("index");
            String message = "Bạn chưa đăng nhập!";
            view.addObject("error", message);
            return view;
        } else {
            ModelAndView view = new ModelAndView("user_profile");
            view.addObject("accountAPI", accountAPI);
            return view;
        }
    }

    @RequestMapping(value = "/updateaccount", method = RequestMethod.POST)
    public ModelAndView updateAccount(HttpServletRequest httpServletRequest, @ModelAttribute("accountForm") AccountForm accountForm) {
        HttpSession session = httpServletRequest.getSession();
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (accountAPI == null || tokenInfo == null) {
            ModelAndView view = new ModelAndView("index");
            String message = "Bạn chưa đăng nhập!";
            view.addObject("error", message);
            return view;
        } else {
            HttpClient httpClient = HttpClients.createDefault();
            try {
                URIBuilder builder = null;
                builder = new URIBuilder(REST_SERVICE_URI + "api/accounts/" + accountForm.getId());
                builder.setParameter("access_token", tokenInfo.getAccess_token());
                URI uri = builder.build();
                HttpPut request = new HttpPut(uri);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fullName", accountForm.getFullName());
                String date = accountForm.getBirthday();
                String[] splits = date.split("-");
                jsonObject.put("date", splits[0]);
                jsonObject.put("month", splits[1]);
                jsonObject.put("year", splits[2]);
                jsonObject.put("address", accountForm.getAddress());
                jsonObject.put("detail", accountForm.getDetail());
                jsonObject.put("phonenumber", accountForm.getPhonenumber());
                jsonObject.put("email", accountForm.getEmail());
                jsonObject.put("idRole", accountForm.getIdRole());
                jsonObject.put("idMember", accountForm.getIdMember());
                jsonObject.put("id", accountForm.getId());
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
                        if (object.containsKey("message")) {
                            String error = (String) object.get("message");
                            ModelAndView view = new ModelAndView("redirect:/userprofile");
                            view.addObject("accountForm", accountForm);
                            view.addObject("error", 1);
                            return view;
                        } else {
                            DateCommon dateCommon = new DateCommon();
                            jsonObject = (JSONObject) object.get("account");
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
                            session.setAttribute("accountAPI", accountAPI);
                            ModelAndView view = new ModelAndView("user_profile");
                            view.addObject("accountAPI", accountAPI);
                            view.addObject("message", "Cập nhật thành công!");
                            return view;
                        }
                    }
                } else {
                    return null;
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public ModelAndView changePassword(HttpServletRequest httpServletRequest, @ModelAttribute("accountForm") AccountForm accountForm) {
        HttpSession session = httpServletRequest.getSession();
        AccountAPI accountAPI = (AccountAPI) session.getAttribute("accountAPI");
        if (accountAPI == null || tokenInfo == null) {
            ModelAndView view = new ModelAndView("index");
            String message = "Bạn chưa đăng nhập!";
            view.addObject("error", message);
            return view;
        } else {
            HttpClient httpClient = HttpClients.createDefault();
            try {
                URIBuilder builder = null;
                builder = new URIBuilder(REST_SERVICE_URI + "api/changepassword/" + accountForm.getId());
                builder.setParameter("access_token", tokenInfo.getAccess_token());
                URI uri = builder.build();
                HttpPut request = new HttpPut(uri);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("newPassword", accountForm.getNewPassword());
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
                        if (object.containsKey("message")) {
                            ModelAndView view = new ModelAndView("redirect:/userprofile");
                            view.addObject("accountForm", accountForm);
                            view.addObject("error", 0);
                            return view;
                        } else {
                            DateCommon dateCommon = new DateCommon();
                            jsonObject = (JSONObject) object.get("account");
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
                            session.setAttribute("accountAPI", accountAPI);
                            ModelAndView view = new ModelAndView("user_profile");
                            view.addObject("accountAPI", accountAPI);
                            view.addObject("message", "Cập nhật thành công!");
                            return view;
                        }
                    }
                } else {
                    return null;
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
