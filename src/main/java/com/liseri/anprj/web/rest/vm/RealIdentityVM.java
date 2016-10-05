package com.liseri.anprj.web.rest.vm;

import com.liseri.anprj.domain.enumeration.GenderType;

/**
 * Created by Administrator on 2016/10/5.
 */
public class RealIdentityVM {
    private final String login;
    private final String name;
    private final GenderType genderType;
    private final String identityCard;
    private final String picPath;
    public RealIdentityVM(String login, String name, GenderType genderType, String identityCard, String picPath) {
        this.login = login;
        this.name = name;
        this.genderType = genderType;
        this.identityCard = identityCard;
        this.picPath = picPath;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getIdentityCard() {
        return identityCard;
    }
}
