package com.liseri.anprj.web.rest.vm;

import com.liseri.anprj.domain.LendApply;
import com.liseri.anprj.domain.enumeration.LendApplyStatu;
import com.liseri.anprj.domain.enumeration.LoanApplyStatu;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Administrator on 2016/10/6.
 */
public class LendApplyVM {

    private Long id;

    @NotNull
    private Long lendPrjId;

    @NotNull
    @Min(value = 100)
    private Long amount;

    public LendApplyVM() {}

    public Long getLendPrjId() {
        return lendPrjId;
    }

    public void setLendPrjId(Long lendPrjId) {
        this.lendPrjId = lendPrjId;
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

    public LendApply newLendApply(String applyer) {
        return new LendApply()
            .login(applyer)
            .lendPrjId(this.lendPrjId)
            .amount(this.amount)
            .lendStatu(LendApplyStatu.APPLYED)
            .applyDate(LocalDate.now());
    }

    public void updateLendApply(LendApply oldLendApply) {
        oldLendApply.lendPrjId(this.lendPrjId)
            .amount(this.amount);
    }

}
