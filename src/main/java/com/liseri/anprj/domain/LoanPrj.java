package com.liseri.anprj.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import com.liseri.anprj.domain.enumeration.LOANPRJTYPE;

import com.liseri.anprj.domain.enumeration.REPAYMENTTYPE;

/**
 * 贷款项目
 *
 */
@ApiModel(description = ""
    + "贷款项目                                                                   "
    + "")
@Entity
@Table(name = "an_loan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoanPrj implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LOANPRJTYPE loanType;

    @NotNull
    @Min(value = 100)
    @Column(name = "max_amount", nullable = false)
    private Long maxAmount;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "1")
    @Column(name = "rate", precision=10, scale=2, nullable = false)
    private BigDecimal rate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "duration_unit", nullable = false)
    private ChronoUnit durationUnit;

    @NotNull
    @Min(value = 1)
    @Column(name = "duration_num", nullable = false)
    private Integer durationNum;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "replay_type", nullable = false)
    private REPAYMENTTYPE replayType;

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

    public LoanPrj name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LOANPRJTYPE getLoanType() {
        return loanType;
    }

    public LoanPrj loanType(LOANPRJTYPE loanType) {
        this.loanType = loanType;
        return this;
    }

    public void setLoanType(LOANPRJTYPE loanType) {
        this.loanType = loanType;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public LoanPrj maxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
        return this;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public LoanPrj rate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public ChronoUnit getDurationUnit() {
        return durationUnit;
    }

    public LoanPrj durationUnit(ChronoUnit durationUnit) {
        this.durationUnit = durationUnit;
        return this;
    }

    public void setDurationUnit(ChronoUnit durationUnit) {
        this.durationUnit = durationUnit;
    }

    public Integer getDurationNum() {
        return durationNum;
    }

    public LoanPrj durationNum(Integer durationNum) {
        this.durationNum = durationNum;
        return this;
    }

    public void setDurationNum(Integer durationNum) {
        this.durationNum = durationNum;
    }

    public REPAYMENTTYPE getReplayType() {
        return replayType;
    }

    public LoanPrj replayType(REPAYMENTTYPE replayType) {
        this.replayType = replayType;
        return this;
    }

    public void setReplayType(REPAYMENTTYPE replayType) {
        this.replayType = replayType;
    }

    public Boolean isActivated() {
        return activated;
    }

    public LoanPrj activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public LocalDate getActivateDate() {
        return activateDate;
    }

    public LoanPrj activateDate(LocalDate activateDate) {
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
        LoanPrj loanPrj = (LoanPrj) o;
        if(loanPrj.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, loanPrj.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LoanPrj{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", loanType='" + loanType + "'" +
            ", maxAmount='" + maxAmount + "'" +
            ", rate='" + rate + "'" +
            ", durationUnit='" + durationUnit + "'" +
            ", durationNum='" + durationNum + "'" +
            ", replayType='" + replayType + "'" +
            ", activated='" + activated + "'" +
            ", activateDate='" + activateDate + "'" +
            '}';
    }
}
