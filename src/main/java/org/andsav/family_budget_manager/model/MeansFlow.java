package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.BaseEntity;
import org.andsav.family_budget_manager.model.enums.MeansflowType;

import java.time.LocalDateTime;

/**
 * 
 * @author Andrii_Savka
 *
 */
public class MeansFlow extends BaseEntity {

    private Integer amount;

    private Budget budget;

    private String description;

    private User byUser;

    private LocalDateTime operationDateTime;

    private MeansflowType goodsType;


    public MeansFlow() {}

    public MeansFlow(Integer id, Integer amount, Budget budget, String description, User byUser,
            LocalDateTime operationDate, MeansflowType goodsType) {
        super(id);
        this.amount = amount;
        this.budget = budget;
        this.description = description;
        this.byUser = byUser;
        this.operationDateTime = operationDate;
        this.goodsType = goodsType;
    }

    public MeansFlow(Integer amount, Budget budget, String description, User byUser,
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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((goodsType == null) ? 0 : goodsType.hashCode());
        result = prime * result + ((operationDateTime == null) ? 0 : operationDateTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MeansFlow other = (MeansFlow) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (goodsType != other.goodsType)
            return false;
        if (operationDateTime == null) {
            if (other.operationDateTime != null)
                return false;
        } else if (!operationDateTime.equals(other.operationDateTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MeansFlow [" + super.toString() + "amount=" + amount + ", description="
                + description + ", operationDate=" + operationDateTime + ", goodsType=" + goodsType
                + "]";
    }



}
