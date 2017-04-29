package com.web.entity.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.entity.api.AccountAPI;

import java.util.List;

/**
 * Created by duyle on 20/02/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountJSON {

    AccountAPI account;
    List<AccountAPI> accounts;

    public AccountAPI getAccount() {
        return account;
    }

    public void setAccount(AccountAPI account) {
        this.account = account;
    }

    public List<AccountAPI> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountAPI> accounts) {
        this.accounts = accounts;
    }
}
