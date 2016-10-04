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

/**
 * 预约提款申请                                                                      
 * 
 */
@ApiModel(description = ""
    + "预约提款申请                                                                 "
    + "")
@Entity
@Table(name = "an_drawapply")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DrawApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "login", length = 16, nullable = false)
    private String login;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "order_amount", precision=10, scale=2, nullable = false)
    private BigDecimal orderAmount;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "actual_amount", precision=10, scale=2, nullable = false)
    private BigDecimal actualAmount;

    @NotNull
    @Size(max = 32)
    @Column(name = "order_draw_date", length = 32, nullable = false)
    private String orderDrawDate;

    @NotNull
    @Column(name = "canceled", nullable = false)
    private Boolean canceled;

    @NotNull
    @Column(name = "completed", nullable = false)
    private Boolean completed;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Column(name = "canceled_date")
    private LocalDate canceledDate;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public DrawApply login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public DrawApply orderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
        return this;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public DrawApply actualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
        return this;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getOrderDrawDate() {
        return orderDrawDate;
    }

    public DrawApply orderDrawDate(String orderDrawDate) {
        this.orderDrawDate = orderDrawDate;
        return this;
    }

    public void setOrderDrawDate(String orderDrawDate) {
        this.orderDrawDate = orderDrawDate;
    }

    public Boolean isCanceled() {
        return canceled;
    }

    public DrawApply canceled(Boolean canceled) {
        this.canceled = canceled;
        return this;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public DrawApply completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public DrawApply applyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public LocalDate getCanceledDate() {
        return canceledDate;
    }

    public DrawApply canceledDate(LocalDate canceledDate) {
        this.canceledDate = canceledDate;
        return this;
    }

    public void setCanceledDate(LocalDate canceledDate) {
        this.canceledDate = canceledDate;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public DrawApply completedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
        return this;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DrawApply drawApply = (DrawApply) o;
        if(drawApply.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, drawApply.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DrawApply{" +
            "id=" + id +
            ", login='" + login + "'" +
            ", orderAmount='" + orderAmount + "'" +
            ", actualAmount='" + actualAmount + "'" +
            ", orderDrawDate='" + orderDrawDate + "'" +
            ", canceled='" + canceled + "'" +
            ", completed='" + completed + "'" +
            ", applyDate='" + applyDate + "'" +
            ", canceledDate='" + canceledDate + "'" +
            ", completedDate='" + completedDate + "'" +
            '}';
    }
}
