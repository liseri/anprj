package com.liseri.anprj.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 企业信息                                                                        
 * 
 */
@ApiModel(description = ""
    + "企业信息                                                                   "
    + "")
@Entity
@Table(name = "an_enterprise")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Enterprise implements Serializable {

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
    @Size(max = 64)
    @Column(name = "address", length = 64, nullable = false)
    private String address;

    @Size(max = 6)
    @Column(name = "postcode", length = 6)
    private String postcode;

    @Size(max = 16)
    @Column(name = "phone", length = 16)
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public Enterprise login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public Enterprise name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Enterprise address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public Enterprise postcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public Enterprise phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Enterprise enterprise = (Enterprise) o;
        if(enterprise.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, enterprise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Enterprise{" +
            "id=" + id +
            ", login='" + login + "'" +
            ", name='" + name + "'" +
            ", address='" + address + "'" +
            ", postcode='" + postcode + "'" +
            ", phone='" + phone + "'" +
            '}';
    }
}
