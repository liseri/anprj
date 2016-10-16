package com.liseri.anprj.web.rest.vm;

import com.liseri.anprj.domain.LoanApply;
import com.liseri.anprj.domain.enumeration.LoanApplyStatu;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Created by Administrator on 2016/10/6.
 */
public class LoanApplyVM {

    private Long id;

    @NotNull
    private Long loanPrjId;

    @NotNull
    @Min(value = 100)
    private Long amount;

    public LoanApplyVM() {}

    public Long getLoanPrjId() {
        return loanPrjId;
    }

    public void setLoanPrjId(Long loanPrjId) {
        this.loanPrjId = loanPrjId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanApply newLoanApply(String applyer) {
        return new LoanApply()
            .login(applyer)
            .loanPrjId(this.loanPrjId)
            .amount(this.amount)
            .applyStatu(LoanApplyStatu.APPLYED)
            .applyDate(LocalDate.now());
    }

    public void updateLoanApply(LoanApply oldLoanApply) {
        oldLoanApply.loanPrjId(this.loanPrjId)
            .amount(this.amount);
    }
}
