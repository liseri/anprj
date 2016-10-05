package com.liseri.anprj.web.rest.vm;

import com.liseri.anprj.domain.enumeration.GenderType;

/**
 * Created by Administrator on 2016/10/5.
 */
public class RealIdentityVM {
    private String login;
    private String name;
    private GenderType gender;
    private String card;

    public RealIdentityVM() {}

    public RealIdentityVM(String login, String name, GenderType gender, String card) {
        this.login = login;
        this.name = name;
        this.gender = gender;
        this.card = card;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public GenderType getGender() {
        return gender;
    }

    public String getCard() {
        return card;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
