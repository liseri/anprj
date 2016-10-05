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

import com.liseri.anprj.domain.enumeration.LoanApplyStatu;

/**
 * 贷款申请
 *
 */
@ApiModel(description = ""
    + "贷款申请                                                                   "
    + "")
@Entity
@Table(name = "an_loanapply")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoanApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "login", length = 16, nullable = false)
    private String login;

    @NotNull
    @Column(name = "loan_prj_id", nullable = false)
    private Long loanPrjId;

    @NotNull
    @Min(value = 100)
    @Column(name = "amount", nullable = false)
    private Long amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "apply_statu", nullable = false)
    private LoanApplyStatu applyStatu;

    @NotNull
    @Column(name = "apply_date", nullable = false)
    private LocalDate applyDate;

    @Column(name = "audit_date")
    private LocalDate auditDate;

    @Column(name = "loan_date")
    private LocalDate loanDate;

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

    public LoanApply login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getLoanPrjId() {
        return loanPrjId;
    }

    public LoanApply loanPrjId(Long loanPrjId) {
        this.loanPrjId = loanPrjId;
        return this;
    }

    public void setLoanPrjId(Long loanPrjId) {
        this.loanPrjId = loanPrjId;
    }

    public Long getAmount() {
        return amount;
    }

    public LoanApply amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LoanApplyStatu getApplyStatu() {
        return applyStatu;
    }

    public LoanApply applyStatu(LoanApplyStatu applyStatu) {
        this.applyStatu = applyStatu;
        return this;
    }

    public void setApplyStatu(LoanApplyStatu applyStatu) {
        this.applyStatu = applyStatu;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public LoanApply applyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public LocalDate getAuditDate() {
        return auditDate;
    }

    public LoanApply auditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
        return this;
    }

    public void setAuditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LoanApply loanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        return this;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public LoanApply completeDate(LocalDate completeDate) {
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
        LoanApply loanApply = (LoanApply) o;
        if(loanApply.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, loanApply.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LoanApply{" +
            "id=" + id +
            ", login='" + login + "'" +
            ", loanPrjId='" + loanPrjId + "'" +
            ", amount='" + amount + "'" +
            ", applyStatu='" + applyStatu + "'" +
            ", applyDate='" + applyDate + "'" +
            ", auditDate='" + auditDate + "'" +
            ", loanDate='" + loanDate + "'" +
            ", completeDate='" + completeDate + "'" +
            '}';
    }
}
