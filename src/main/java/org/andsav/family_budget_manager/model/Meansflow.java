package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.BaseEntity;
import org.andsav.family_budget_manager.model.enums.MeansflowType;
import org.andsav.family_budget_manager.util.date_convertor.LocalDateTimeAttributeConverter;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Andrii_Savka
 *
 */

@Entity
@Table(name = "MEANSFLOWS")
@NamedQueries({
        @NamedQuery(name = Meansflow.DELETE, query = "DELETE FROM Meansflow m WHERE m.id=:id"),
        @NamedQuery(name = Meansflow.BY_BUDGET_ID_SORTED,
                query = "SELECT m FROM Meansflow m WHERE m.budget.id=:id ORDER BY m.id"),
        @NamedQuery(name = Meansflow.BY_BUDGET_ID_SORTED_BETWEEN_DATES_SORTED,
        query = "SELECT m FROM Meansflow m WHERE m.budget.id=:id AND"
                + " m.operationDateTime BETWEEN :startDate AND :endDate ORDER BY m.operationDateTime")})
public class Meansflow extends BaseEntity {

    public static final String DELETE = "Meansflow.delete";
    public static final String BY_BUDGET_ID_SORTED = "Meansflow.getByBudgetIdSorted";
    public static final String BY_BUDGET_ID_SORTED_BETWEEN_DATES_SORTED ="Meansflow.getByBudgetIdBetweenDatesSorted";

    @Column(nullable = false, name = "amount")
    @NotEmpty
    protected Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    protected Budget budget;

    @Column(nullable = false)
    @NotEmpty
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User byUser;

    @Column(nullable = false, name = "operation_date_time")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    protected LocalDateTime operationDateTime;

    @Enumerated(EnumType.STRING)
    @NotEmpty
    @Column(nullable = false, name = "goods_type")
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
