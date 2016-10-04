package com.liseri.anprj.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 身份证实名绑定                                                                     
 * 
 */
@ApiModel(description = ""
    + "身份证实名绑定                                                                "
    + "")
@Entity
@Table(name = "an_realname")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RealIdentity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "login", length = 16, nullable = false)
    private String login;

    @NotNull
    @Size(max = 16)
    @Column(name = "name", length = 16, nullable = false)
    private String name;

    @NotNull
    @Size(max = 18)
    @Column(name = "identity_number", length = 18, nullable = false)
    private String identityNumber;

    @NotNull
    @Size(max = 255)
    @Column(name = "identity_pic_path", length = 255, nullable = false)
    private String identityPicPath;

    @NotNull
    @Column(name = "activated", nullable = false)
    private Boolean activated;

    @Column(name = "activate_date")
    private LocalDate activateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public RealIdentity login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public RealIdentity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public RealIdentity identityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
        return this;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getIdentityPicPath() {
        return identityPicPath;
    }

    public RealIdentity identityPicPath(String identityPicPath) {
        this.identityPicPath = identityPicPath;
        return this;
    }

    public void setIdentityPicPath(String identityPicPath) {
        this.identityPicPath = identityPicPath;
    }

    public Boolean isActivated() {
        return activated;
    }

    public RealIdentity activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public LocalDate getActivateDate() {
        return activateDate;
    }

    public RealIdentity activateDate(LocalDate activateDate) {
        this.activateDate = activateDate;
        return this;
    }

    public void setActivateDate(LocalDate activateDate) {
        this.activateDate = activateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RealIdentity realIdentity = (RealIdentity) o;
        if(realIdentity.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, realIdentity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RealIdentity{" +
            "id=" + id +
            ", login='" + login + "'" +
            ", name='" + name + "'" +
            ", identityNumber='" + identityNumber + "'" +
            ", identityPicPath='" + identityPicPath + "'" +
            ", activated='" + activated + "'" +
            ", activateDate='" + activateDate + "'" +
            '}';
    }
}
