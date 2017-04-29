package com.web.controller;

import com.web.bean.AuthTokenInfo;
import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.bean.ResultRespone;
import com.web.common.DateCommon;
import com.web.common.RandomKey;
import com.web.entity.JSON.AccountJSON;
import com.web.entity.api.AccountAPI;
import com.web.entity.api.ChangePasswordAPI;
import com.web.entity.api.Login;
import com.web.entity.api.RegisterAPI;
import com.web.entity.backend.Account;
import com.web.entity.backend.Classes;
import com.web.entity.backend.Members;
import com.web.mail.MailService;
import com.web.service.IAccountService;
import com.web.service.IMemberService;
import com.web.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 16/02/2017.
 */

@Controller
public class AccountController {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IRoleService roleService;
    private DateCommon dateCommon;

    /*
        API login
        With function login, must use "Method.Post" in order to security data of user
    */
    @RequestMapping(value = "/api/accounts/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<AccountJSON> login(@PathVariable("username") String username) {
        Account account = accountService.getAccountByUser(username);
        Members members = memberService.getMemberById(account.getId());
        if (account == null) {
            return new ResponseEntity<AccountJSON>(HttpStatus.NO_CONTENT);
        } else {
            AccountAPI accountAPI = new AccountAPI();
            if (members == null) {
                accountAPI.setId(account.getId());
                accountAPI.setUsername(account.getUsername());
                accountAPI.setPassword(account.getPassword());
                accountAPI.setEmail(account.getEmail());
                accountAPI.setIdRole(account.getIdRole().getId());
                accountAPI.setRoleName(account.getIdRole().getRoleName());
            } else {
                accountAPI.setId(account.getId());
                accountAPI.setUsername(account.getUsername());
                accountAPI.setFullName(members.getFullName());
                accountAPI.setEmail(account.getEmail());
                accountAPI.setBirthday(members.getBirthday());
                accountAPI.setAddress(members.getAddress());
                accountAPI.setPhonenumber(members.getPhoneNumber());
                accountAPI.setIdRole(account.getIdRole().getId());
                accountAPI.setRoleName(account.getIdRole().getRoleName());
                accountAPI.setDetail(account.getDetail());
                accountAPI.setIdMember(members.getId());
                accountAPI.setPassword(account.getPassword());
            }
            AccountJSON accountJSON = new AccountJSON();
            accountJSON.setAccount(accountAPI);
            return new ResponseEntity<AccountJSON>(accountJSON, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/api/resetpassword/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<ResultRespone> resetPassword(@PathVariable("email") String email) throws ErrorException {
        System.out.print(email);
        Account account = accountService.getAccountByEmail(email);
        if (account != null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
            RandomKey random = new RandomKey();
            int min = 1000000;
            int max = 9999999;
            int key = random.rand(min, max);
            account.setActivationCode(String.valueOf(key));
            accountService.resetPassword(account);
            MailService mailService = (MailService) context.getBean("mailMail");
            mailService.sendEmailResetPassword(email, key);
            ResultRespone message = new ResultRespone("Vui lòng kiểm tra email của bạn!");
            return new ResponseEntity<ResultRespone>(message, HttpStatus.OK);
        } else {
            throw new ErrorException("Email không tồn tại!");
        }
    }

    @RequestMapping(value = "/api/accounts", method = RequestMethod.GET)
    public ResponseEntity<AccountJSON> getAllAccounts() {
        List<Account> accountList = accountService.getAllAccounts();

        if (accountList.isEmpty()) {
            return new ResponseEntity<AccountJSON>(HttpStatus.NO_CONTENT);
        } else {
            AccountAPI accountAPI = null;
            List<AccountAPI> accountAPIS = new ArrayList<AccountAPI>();
            Members members;
            for (Account a : accountList) {
                accountAPI = new AccountAPI();
                members = memberService.getMemberById(a.getId());
                if (members != null) {
                    accountAPI.setIdMember(members.getId());
                    accountAPI.setAddress(members.getAddress());
                    accountAPI.setBirthday(members.getBirthday());
                    accountAPI.setPhonenumber(members.getPhoneNumber());
                    accountAPI.setBirthday(members.getBirthday());
                    accountAPI.setFullName(members.getFullName());
                }
                accountAPI.setId(a.getId());
                accountAPI.setRoleName(a.getIdRole().getRoleName());
                accountAPI.setIdRole(a.getIdRole().getId());
                accountAPI.setActivationCode(a.getActivationCode());
                accountAPI.setActivationDate(a.getActivationDate());
                accountAPI.setDetail(a.getDetail());
                accountAPI.setEmail(a.getEmail());
                accountAPI.setPassword(a.getPassword());
                accountAPIS.add(accountAPI);
            }
            AccountJSON accountJSON = new AccountJSON();
            accountJSON.setAccounts(accountAPIS);
            return new ResponseEntity<AccountJSON>(accountJSON, HttpStatus.OK);
        }

    }


    @RequestMapping(value = "/api/changepassword/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountJSON> changePassword(@PathVariable("id") int id, @RequestBody ChangePasswordAPI changePasswordAPI) throws ErrorException {
        if (accountService.checkPasswordById(id, changePasswordAPI.getOldPassword())) {
            Account account = accountService.getAccountById(id);
            account.setPassword(changePasswordAPI.getNewPassword());
            accountService.changePassword(account);

            AccountAPI accountAPI1 = new AccountAPI();
            Account account1 = accountService.getAccountById(id);
            Members members = memberService.getMemberById(id);
            accountAPI1.setId(account1.getId());
            accountAPI1.setIdMember(members.getId());
            accountAPI1.setRoleName(account1.getIdRole().getRoleName());
            accountAPI1.setIdRole(account1.getIdRole().getId());
            accountAPI1.setBirthday(members.getBirthday());
            accountAPI1.setPhonenumber(members.getPhoneNumber());
            accountAPI1.setActivationCode(account1.getActivationCode());
            accountAPI1.setActivationDate(account1.getActivationDate());
            accountAPI1.setAddress(members.getAddress());
            accountAPI1.setDetail(account1.getDetail());
            accountAPI1.setEmail(account1.getEmail());
            accountAPI1.setBirthday(members.getBirthday());
            accountAPI1.setFullName(members.getFullName());
            accountAPI1.setPassword(account1.getPassword());
            AccountJSON accountJSON = new AccountJSON();
            accountJSON.setAccount(accountAPI1);

            return new ResponseEntity<AccountJSON>(accountJSON, HttpStatus.OK);
        } else {
            throw new ErrorException("Mật khẩu không đúng!");
        }
    }

    @RequestMapping(value = "/api/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountJSON> getAccountById(@PathVariable("id") int id) {
        Account account = accountService.getAccountById(id);
        if (account == null) {
            return new ResponseEntity<AccountJSON>(HttpStatus.NO_CONTENT);
        } else {
            Members members = memberService.getMemberById(id);
            AccountAPI accountAPI = new AccountAPI();
            accountAPI.setId(account.getId());
            accountAPI.setIdMember(members.getId());
            accountAPI.setRoleName(account.getIdRole().getRoleName());
            accountAPI.setIdRole(account.getIdRole().getId());
            accountAPI.setBirthday(members.getBirthday());
            accountAPI.setPhonenumber(members.getPhoneNumber());
            accountAPI.setActivationCode(account.getActivationCode());
            accountAPI.setActivationDate(account.getActivationDate());
            accountAPI.setAddress(members.getAddress());
            accountAPI.setDetail(account.getDetail());
            accountAPI.setEmail(account.getEmail());
            accountAPI.setBirthday(members.getBirthday());
            accountAPI.setFullName(members.getFullName());
            accountAPI.setPassword(account.getPassword());
            AccountJSON accountJSON = new AccountJSON();
            accountJSON.setAccount(accountAPI);
            return new ResponseEntity<AccountJSON>(accountJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/accounts/", method = RequestMethod.POST)
    public ResponseEntity<AccountJSON> createAccount(@RequestBody AccountAPI accountAPI) throws ErrorException {

        Account account = new Account();
        RandomKey randomKey = new RandomKey();
        int min = 1000000;
        int max = 9999999;
        int key = randomKey.rand(min, max);
        account.setActivationCode(String.valueOf(key));
        account.setDetail(accountAPI.getDetail());
        account.setPassword(accountAPI.getPassword());
        account.setEmail(accountAPI.getEmail());
        account.setUsername(accountAPI.getUsername());
        account.setStatus(0);
        account.setIdRole(roleService.getRoleByRoleName("Member"));
        if (accountService.isAccountByUsername(account)) {
            throw new ErrorException("Username này đã tồn tại!");
        } else if (accountService.isAccountByEmail(account)) {
            throw new ErrorException("Email này đã tồn tại!");
        } else {
            Members members = memberService.isMemberExistByMember(accountAPI.getFullName(), accountAPI.getBirthday());
            if (members == null) {
                dateCommon = new DateCommon();
                account.setActivationDate(dateCommon.getDateNow());
                int result = accountService.addAccount(account);
                Account account1 = accountService.getAccountById(result);
                if (result == 0) {
                    throw new ErrorException("Thêm tài khoản không thành công!");
                } else {
                    members = new Members();
                    members.setFullName(accountAPI.getFullName());
                    members.setDetail(accountAPI.getDetail());
                    members.setAddress(accountAPI.getAddress());
                    members.setPhoneNumber(accountAPI.getPhonenumber());
                    members.setBirthday(accountAPI.getBirthday());
                    members.setAccount(account1);
                    result = memberService.addMember(members);
                    if (result == 0) {
                        throw new ErrorException("Thêm thành viên không thành công!");
                    } else {
                        AccountAPI accountAPI1 = new AccountAPI();
                        Members members1 = memberService.getMemberById(result);
                        accountAPI1.setId(account1.getId());
                        accountAPI1.setIdMember(members1.getId());
                        accountAPI1.setRoleName(account1.getIdRole().getRoleName());
                        accountAPI1.setIdRole(account1.getIdRole().getId());
                        accountAPI1.setBirthday(members1.getBirthday());
                        accountAPI1.setPhonenumber(members1.getPhoneNumber());
                        accountAPI1.setActivationCode(account1.getActivationCode());
                        accountAPI1.setActivationDate(account1.getActivationDate());
                        accountAPI1.setAddress(members1.getAddress());
                        accountAPI1.setDetail(account1.getDetail());
                        accountAPI1.setEmail(account1.getEmail());
                        accountAPI1.setBirthday(members1.getBirthday());
                        accountAPI1.setFullName(members1.getFullName());
                        accountAPI1.setPassword(account1.getPassword());
                        AccountJSON accountJSON = new AccountJSON();
                        accountJSON.setAccount(accountAPI1);
                        return new ResponseEntity<AccountJSON>(accountJSON, HttpStatus.CREATED);
                    }
                }
            } else {
                throw new ErrorException("Thành viên đã tồn tại!");
            }

        }
    }

    @RequestMapping(value = "/api/accounts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountJSON> updateAccount(@PathVariable("id") int id, @RequestBody AccountAPI accountAPI) throws ErrorException {
        Account accountById = accountService.getAccountById(id);
        Account account = new Account();

        if (accountById == null) {
            throw new ErrorException(" Id tài khoản không tồn tại!");
        } else if (accountService.checkEmail(id, accountAPI.getEmail())) {
            throw new ErrorException("Email đã tồn tại!");
        } else {
            dateCommon = new DateCommon();
            Members members = new Members();
            members.setId(accountAPI.getIdMember());
            members.setFullName(accountAPI.getFullName());
            members.setDetail(accountAPI.getDetail());
            members.setAddress(accountAPI.getAddress());
            members.setPhoneNumber(accountAPI.getPhonenumber());
            if (accountAPI.getBirthday() != null) {
                members.setBirthday(accountAPI.getBirthday());
            } else {
                String birthday = accountAPI.getYear() + "-" + accountAPI.getMonth() + "-" + accountAPI.getDate();
                members.setBirthday(dateCommon.convertStringToDateSql(birthday));
            }
            memberService.updateMember(members);

            account.setId(id);
            account.setUpdateDate(dateCommon.getDateNow());
            account.setDetail(accountAPI.getDetail());
            account.setEmail(accountAPI.getEmail());
            accountService.updateAccount(account);

            AccountAPI accountAPI1 = new AccountAPI();
            Account account1 = accountService.getAccountById(id);
            accountAPI1.setUsername(account1.getUsername());
            accountAPI1.setIdMember(members.getId());
            accountAPI1.setRoleName(account1.getIdRole().getRoleName());
            accountAPI1.setIdRole(account1.getIdRole().getId());
            accountAPI1.setBirthday(members.getBirthday());
            accountAPI1.setPhonenumber(members.getPhoneNumber());
            accountAPI1.setActivationCode(account1.getActivationCode());
            accountAPI1.setActivationDate(account1.getActivationDate());
            accountAPI1.setAddress(members.getAddress());
            accountAPI1.setDetail(account1.getDetail());
            accountAPI1.setEmail(account1.getEmail());
            accountAPI1.setBirthday(members.getBirthday());
            accountAPI1.setFullName(members.getFullName());
            accountAPI1.setPassword(account1.getPassword());
            accountAPI1.setId(account1.getId());
            AccountJSON accountJSON = new AccountJSON();
            accountJSON.setAccount(accountAPI1);

            return new ResponseEntity<AccountJSON>(accountJSON, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/accounts/changerole/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountJSON> changeRole(@PathVariable("id") int id, @RequestBody AccountAPI accountAPI) throws ErrorException {
        Account accountById = accountService.getAccountById(id);
        accountById.setIdRole(roleService.getRoleById(accountAPI.getIdRole()));
        accountService.changeRole(accountById);

        AccountAPI accountAPI1 = new AccountAPI();
        Account account1 = accountService.getAccountById(id);
        Members members = memberService.getMemberById(id);
        accountAPI1.setIdMember(members.getId());
        accountAPI1.setRoleName(account1.getIdRole().getRoleName());
        accountAPI1.setIdRole(account1.getIdRole().getId());
        accountAPI1.setBirthday(members.getBirthday());
        accountAPI1.setPhonenumber(members.getPhoneNumber());
        accountAPI1.setActivationCode(account1.getActivationCode());
        accountAPI1.setActivationDate(account1.getActivationDate());
        accountAPI1.setAddress(members.getAddress());
        accountAPI1.setDetail(account1.getDetail());
        accountAPI1.setEmail(account1.getEmail());
        accountAPI1.setBirthday(members.getBirthday());
        accountAPI1.setFullName(members.getFullName());
        accountAPI1.setPassword(account1.getPassword());
        accountAPI1.setId(account1.getId());
        AccountJSON accountJSON = new AccountJSON();
        accountJSON.setAccount(accountAPI1);

        return new ResponseEntity<AccountJSON>(accountJSON, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/register/", method = RequestMethod.POST)
    public ResponseEntity<AccountJSON> register(@RequestBody RegisterAPI registerAPI) throws ErrorException {
        Account account = new Account();
        account.setUsername(registerAPI.getUsername());
        account.setPassword(registerAPI.getPassword());
        account.setEmail(registerAPI.getEmail());
        if (accountService.isAccountByUsername(account)) {
            throw new ErrorException("Username này đã tồn tại!");
        } else if (accountService.isAccountByEmail(account)) {
            throw new ErrorException("Email này đã tồn tại!");
        } else {
            account.setIdRole(roleService.getRoleByRoleName("Member"));
            account.setStatus(0);
            int result = accountService.addAccount(account);
            Account account1 = accountService.getAccountById(result);
            if (result == 0) {
                throw new ErrorException("Thêm tài khoản không thành công!");
            } else {
                ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
                MailService mailService = (MailService) context.getBean("mailMail");
                mailService.sendEmailRegister(registerAPI.getEmail());
                AccountAPI accountAPI1 = new AccountAPI();
                accountAPI1.setId(account1.getId());
                accountAPI1.setUsername(account1.getUsername());
                accountAPI1.setRoleName(account1.getIdRole().getRoleName());
                accountAPI1.setIdRole(account1.getIdRole().getId());
                accountAPI1.setActivationCode(account1.getActivationCode());
                accountAPI1.setActivationDate(account1.getActivationDate());
                accountAPI1.setDetail(account1.getDetail());
                accountAPI1.setEmail(account1.getEmail());
                accountAPI1.setPassword(account1.getPassword());
                AccountJSON accountJSON = new AccountJSON();
                accountJSON.setAccount(accountAPI1);
                return new ResponseEntity<AccountJSON>(accountJSON, HttpStatus.CREATED);
            }
        }
    }

    @RequestMapping(value = "/api/activation/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<ResultRespone> activationAccount(@PathVariable("email") String email) throws ErrorException {
        Account account = accountService.getAccountByEmail(email);
        if (account == null) {
            throw new ErrorException("Email không tồn tại!");
        } else {
            if (account.getStatus() != 0) {
                throw new ErrorException("Tài khoản đã được kích hoạt!\n Bạn không cần kích hoạt lại!");
            } else {
                DateCommon dateCommon = new DateCommon();
                account.setStatus(1);
                account.setActivationDate(dateCommon.getDateNow());
                accountService.updateAccount(account);
                account = accountService.getAccountByEmail(email);
                if (account.getStatus() != 0) {
                    return new ResponseEntity<ResultRespone>(new ResultRespone("Tài khoản kích hoạt thành công!"), HttpStatus.OK);
                } else {
                    throw new ErrorException("Tài khoản kích hoạt không thành công!\n Bạn vui lòng kích hoạt lại theo link bên dưới!" + "\nhttp://localhost:8082/activation/" + email);
                }
            }
        }
    }

    @RequestMapping(value = "/api/logout", method = RequestMethod.POST)
    public void logout(@RequestBody AuthTokenInfo authTokenInfo) throws ErrorException {
        OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(authTokenInfo.getAccess_token());
        OAuth2RefreshToken auth2RefreshToken = tokenStore.readRefreshToken(authTokenInfo.getRefresh_token());
        if (auth2AccessToken == null || auth2RefreshToken == null) {
            throw new ErrorException("Access token hoặc refresh token không đúng!");
        } else {
            tokenStore.removeAccessToken(auth2AccessToken);
            tokenStore.removeRefreshToken(auth2RefreshToken);
            throw new ErrorException("Đăng xuất thành công!");
        }
    }

    @RequestMapping(value = "/api/updatepassword/{email:.+}", method = RequestMethod.POST)
    public ResponseEntity<ResultRespone> updatePassword(@PathVariable("email") String email, @RequestBody AccountAPI accountAPI) throws ErrorException {
        Account account = accountService.getAccountByEmail(email);
        if (account == null) {
            throw new ErrorException("Email không tồn tại!");
        } else {
            if (account.getStatus() != 0) {
                throw new ErrorException("Link này đã hết hạn!");
            } else {
                if (account.getActivationCode().equals(accountAPI.getActivationCode())) {
                    DateCommon dateCommon = new DateCommon();
                    account.setPassword(accountAPI.getPassword());
                    account.setStatus(1);
                    account.setActivationCode(null);
                    account.setUpdateDate(dateCommon.getDateNow());
                    accountService.updatePassword(account);
                    return new ResponseEntity<ResultRespone>(new ResultRespone("Mật khẩu đã được thay đổi!"), HttpStatus.OK);
                } else {
                    throw new ErrorException("Mã kích hoạt không đúng!");
                }
            }
        }

    }

    @RequestMapping(value = "/api/lockaccount/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResultRespone> lockAccount(@PathVariable("id") int id) throws ErrorException {
        Account account = accountService.getAccountById(id);
        if (account == null) {
            throw new ErrorException("ID không tồn tại!");
        } else {
            if (account.getStatus() != 0) {
                DateCommon dateCommon = new DateCommon();
                account.setId(id);
                account.setStatus(2);
                account.setDetail("Tài khoản bị khóa!");
                accountService.lockAccount(account);
                account = accountService.getAccountById(id);
                if (account.getStatus() == 3) {
                    return new ResponseEntity<ResultRespone>(new ResultRespone("Khóa tài khoản thành công!"), HttpStatus.OK);
                } else {
                    throw new ErrorException("Khóa tài khoản không thành công!");
                }
            } else {
                throw new ErrorException("Tài khoản không hiệu lực!");
            }
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
