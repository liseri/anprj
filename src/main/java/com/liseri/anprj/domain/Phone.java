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
 * 电话绑定
 *
 */
@ApiModel(description = ""
    + "电话绑定                                                                   "
    + "")
@Entity
@Table(name = "an_phone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Phone implements Serializable {

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
    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @NotNull
    @Size(max = 8)
    @Column(name = "key", length = 8, nullable = false)
    private String key;

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

    public Phone login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public Phone phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKey() {
        return key;
    }

    public Phone key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean isActivated() {
        return activated;
    }

    public Phone activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public LocalDate getActivateDate() {
        return activateDate;
    }

    public Phone activateDate(LocalDate activateDate) {
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
        Phone phone = (Phone) o;
        if(phone.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Phone{" +
            "id=" + id +
            ", login='" + login + "'" +
            ", phone='" + phone + "'" +
            ", activated='" + activated + "'" +
            ", activateDate='" + activateDate + "'" +
            '}';
    }

}
