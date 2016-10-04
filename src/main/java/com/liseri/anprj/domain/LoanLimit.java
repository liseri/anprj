package com.liseri.anprj.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.liseri.anprj.domain.enumeration.LOANPRJTYPE;

/**
 * 贷款额度设置                                                                      
 * 
 */
@ApiModel(description = ""
    + "贷款额度设置                                                                 "
    + "")
@Entity
@Table(name = "an_loanlimit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoanLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LOANPRJTYPE loanType;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "min_limit", precision=10, scale=2, nullable = false)
    private BigDecimal minLimit;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "max_limit", precision=10, scale=2, nullable = false)
    private BigDecimal maxLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LOANPRJTYPE getLoanType() {
        return loanType;
    }

    public LoanLimit loanType(LOANPRJTYPE loanType) {
        this.loanType = loanType;
        return this;
    }

    public void setLoanType(LOANPRJTYPE loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getMinLimit() {
        return minLimit;
    }

    public LoanLimit minLimit(BigDecimal minLimit) {
        this.minLimit = minLimit;
        return this;
    }

    public void setMinLimit(BigDecimal minLimit) {
        this.minLimit = minLimit;
    }

    public BigDecimal getMaxLimit() {
        return maxLimit;
    }

    public LoanLimit maxLimit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
        return this;
    }

    public void setMaxLimit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoanLimit loanLimit = (LoanLimit) o;
        if(loanLimit.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, loanLimit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LoanLimit{" +
            "id=" + id +
            ", loanType='" + loanType + "'" +
            ", minLimit='" + minLimit + "'" +
            ", maxLimit='" + maxLimit + "'" +
            '}';
    }
}
