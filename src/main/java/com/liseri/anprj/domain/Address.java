package com.liseri.anprj.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 地址绑定                                                                        
 * 
 */
@ApiModel(description = ""
    + "地址绑定                                                                   "
    + "")
@Entity
@Table(name = "an_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "login", length = 16, nullable = false)
    private String login;

    @NotNull
    @Size(max = 64)
    @Column(name = "address", length = 64, nullable = false)
    private String address;

    @NotNull
    @Size(max = 16)
    @Column(name = "name", length = 16, nullable = false)
    private String name;

    @NotNull
    @Size(max = 16)
    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @Size(max = 6)
    @Column(name = "postcode", length = 6)
    private String postcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public Address login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAddress() {
        return address;
    }

    public Address address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public Address phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public Address postcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        if(address.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + id +
            ", login='" + login + "'" +
            ", address='" + address + "'" +
            ", name='" + name + "'" +
            ", phone='" + phone + "'" +
            ", postcode='" + postcode + "'" +
            '}';
    }
}
