package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.BaseEntity;
import org.andsav.family_budget_manager.model.enums.MeansflowType;
import org.andsav.family_budget_manager.util.date_convertor.LocalDateTimeAttributeConverter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Andrii_Savka
 *
 */

@Entity
@Table(name = "MEANS_FLOWS")
public class Meansflow extends BaseEntity {

    @Column(nullable = false)
    protected Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetId")
    protected Budget budget;

    @Column(nullable = false)
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    protected User byUser;

    @Column(nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    protected LocalDateTime operationDateTime;

    @Enumerated(EnumType.STRING)
    protected MeansflowType goodsType;


    public Meansflow() {}

    public Meansflow(Integer id, Integer amount, Budget budget, String description, User byUser,
            LocalDateTime operationDate, MeansflowType goodsType) {
        super(id);
        this.amount = amount;
        this.budget = budget;
        this.description = description;
        this.byUser = byUser;
        this.operationDateTime = operationDate;
        this.goodsType = goodsType;
    }

    public Meansflow(Integer amount, Budget budget, String description, User byUser,
            LocalDateTime operationDate, MeansflowType goodsType) {
        this(null, amount, budget, description, byUser, operationDate, goodsType);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getByUser() {
        return byUser;
    }

    public void setByUser(User byUser) {
        this.byUser = byUser;
    }

    public LocalDateTime getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(LocalDateTime operationDateTime) {
        this.operationDateTime = operationDateTime;
    }

    public MeansflowType getType() {
        return goodsType;
    }

    public void setGoodsType(MeansflowType goodsType) {
        this.goodsType = goodsType;
    }


    @Override
    public String toString() {
        return "MeansFlow [" + super.toString() + "amount=" + amount + ", description="
                + description + ", operationDate=" + operationDateTime + ", goodsType=" + goodsType
                + "]";
    }



}
