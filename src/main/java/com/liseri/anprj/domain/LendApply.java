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

import com.liseri.anprj.domain.enumeration.LendApplyStatu;

/**
 * 理财申请
 *
 */
@ApiModel(description = ""
    + "理财申请                                                                   "
    + "")
@Entity
@Table(name = "an_lendapply")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LendApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "login", length = 16, nullable = false)
    private String login;

    @NotNull
    @Column(name = "lend_prj_id", nullable = false)
    private Long lendPrjId;

    @NotNull
    @Min(value = 100)
    @Column(name = "amount", nullable = false)
    private Long amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lend_statu", nullable = false)
    private LendApplyStatu lendStatu;

    @NotNull
    @Column(name = "apply_date", nullable = false)
    private LocalDate applyDate;

    @Column(name = "audit_date")
    private LocalDate auditDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "complete_date")
    private LocalDate completeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public LendApply login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getLendPrjId() {
        return lendPrjId;
    }

    public LendApply lendPrjId(Long lendPrjId) {
        this.lendPrjId = lendPrjId;
        return this;
    }

    public void setLendPrjId(Long lendPrjId) {
        this.lendPrjId = lendPrjId;
    }

    public Long getAmount() {
        return amount;
    }

    public LendApply amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LendApplyStatu getLendStatu() {
        return lendStatu;
    }

    public LendApply lendStatu(LendApplyStatu lendStatu) {
        this.lendStatu = lendStatu;
        return this;
    }

    public void setLendStatu(LendApplyStatu lendStatu) {
        this.lendStatu = lendStatu;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public LendApply applyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public LocalDate getAuditDate() {
        return auditDate;
    }

    public LendApply auditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
        return this;
    }

    public void setAuditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LendApply startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public LendApply completeDate(LocalDate completeDate) {
        this.completeDate = completeDate;
        return this;
    }

    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LendApply lendApply = (LendApply) o;
        if(lendApply.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, lendApply.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LendApply{" +
            "id=" + id +
            ", login='" + login + "'" +
            ", lendPrjId='" + lendPrjId + "'" +
            ", amount='" + amount + "'" +
            ", lendStatu='" + lendStatu + "'" +
            ", applyDate='" + applyDate + "'" +
            ", startDate='" + startDate + "'" +
            ", completeDate='" + completeDate + "'" +
            '}';
    }
}
