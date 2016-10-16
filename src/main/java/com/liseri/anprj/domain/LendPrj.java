package com.liseri.anprj.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.liseri.anprj.domain.enumeration.REPAYMENTTYPE;

/**
 * 理财项目
 *
 */
@ApiModel(description = ""
    + "理财项目                                                                   "
    + "")
@Entity
@Table(name = "an_lend")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LendPrj implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @NotNull
    @Min(value = 100)
    @Column(name = "start_amount", nullable = false)
    private Long startAmount;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "rate", precision=10, scale=2, nullable = false)
    private BigDecimal rate;

    @NotNull
    @Size(max = 16)
    @Column(name = "duration_unit", length = 16, nullable = false)
    private String durationUnit;

    @NotNull
    @Min(value = 1)
    @Column(name = "duration_num", nullable = false)
    private Integer durationNum;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "return_type", nullable = false)
    private REPAYMENTTYPE returnType;

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

    public String getName() {
        return name;
    }

    public LendPrj name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartAmount() {
        return startAmount;
    }

    public LendPrj startAmount(Long startAmount) {
        this.startAmount = startAmount;
        return this;
    }

    public void setStartAmount(Long startAmount) {
        this.startAmount = startAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public LendPrj rate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDurationUnit() {
        return durationUnit;
    }

    public LendPrj durationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
        return this;
    }

    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }

    public Integer getDurationNum() {
        return durationNum;
    }

    public LendPrj durationNum(Integer durationNum) {
        this.durationNum = durationNum;
        return this;
    }

    public void setDurationNum(Integer durationNum) {
        this.durationNum = durationNum;
    }

    public REPAYMENTTYPE getReturnType() {
        return returnType;
    }

    public LendPrj returnType(REPAYMENTTYPE returnType) {
        this.returnType = returnType;
        return this;
    }

    public void setReturnType(REPAYMENTTYPE returnType) {
        this.returnType = returnType;
    }

    public Boolean isActivated() {
        return activated;
    }

    public LendPrj activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public LocalDate getActivateDate() {
        return activateDate;
    }

    public LendPrj activateDate(LocalDate activateDate) {
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
        LendPrj lendPrj = (LendPrj) o;
        if(lendPrj.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, lendPrj.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LendPrj{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", startAmount='" + startAmount + "'" +
            ", rate='" + rate + "'" +
            ", durationUnit='" + durationUnit + "'" +
            ", durationNum='" + durationNum + "'" +
            ", returnType='" + returnType + "'" +
            ", activated='" + activated + "'" +
            ", activateDate='" + activateDate + "'" +
            '}';
    }
}
