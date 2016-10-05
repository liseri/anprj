package com.liseri.anprj.web.rest.vm;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2016/10/6.
 */
public class LoanApplyVM {
    @NotNull
    @Size(max = 16)
    private String login;

    @NotNull
    private Long loanPrjId;

    @NotNull
    @Min(value = 100)
    private Long amount;

    public LoanApplyVM() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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
}
