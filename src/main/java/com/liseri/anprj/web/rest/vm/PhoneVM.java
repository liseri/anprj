package com.liseri.anprj.web.rest.vm;

/**
 * Created by Administrator on 2016/10/5.
 */
public class PhoneVM {
    private String login;
    private String phone;
    private String key;

    public PhoneVM() {}

    public PhoneVM(String login, String phone, String key) {
        this.login = login;
        this.phone = phone;
        this.key = key;
    }

    public String getLogin() {
        return login;
    }

    public String getPhone() {
        return phone;
    }

    public String getKey() {
        return key;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
