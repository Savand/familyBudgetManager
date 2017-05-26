package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.BaseEntity;
import org.andsav.family_budget_manager.model.enums.FundsFlowType;
import org.andsav.family_budget_manager.util.date_convertor.LocalDateTimeAttributeConverter;
import org.hibernate.validator.constraints.NotBlank;

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
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Andrii_Savka
 *
 */

@Entity
@Table(name = "FUNDSFLOWS")
@NamedQueries({
        @NamedQuery(name = FundsFlow.DELETE, query = "DELETE FROM FundsFlow f WHERE f.id=:id AND f.budget.id=:budgetId"),
        @NamedQuery(name = FundsFlow.BY_BUDGET_ID,
                query = "SELECT f FROM FundsFlow f WHERE f.budget.id=:id ORDER BY f.id"),
        @NamedQuery(name = FundsFlow.BY_BUDGET_ID_BETWEEN_DATES,
        query = "SELECT f FROM FundsFlow f WHERE f.budget.id=:id AND"
                + " f.operationDateTime BETWEEN :startDate AND :endDate ORDER BY f.operationDateTime")})
public class FundsFlow extends BaseEntity {

    public static final String DELETE = "FundsFlow.delete";
    public static final String BY_BUDGET_ID = "FundsFlow.getByBudgetIdSorted";
    public static final String BY_BUDGET_ID_BETWEEN_DATES ="FundsFlow.getByBudgetIdBetweenDatesSorted";

    @Column(nullable = false, name = "amount")
    @NotNull
    protected Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @Column(nullable = false)
    @NotBlank
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User byUser;

    @Column(nullable = false, name = "operation_date_time")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @NotNull
    private LocalDateTime operationDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "funds_flow_type")
    @NotNull
    private FundsFlowType fundsFlowType;


    public FundsFlow() {}

    public FundsFlow(Integer id, Integer amount, Budget budget, String description, User byUser,
            LocalDateTime operationDate, FundsFlowType fundsFlowType) {
        super(id);
        this.amount = amount;
        this.budget = budget;
        this.description = description;
        this.byUser = byUser;
        this.operationDateTime = operationDate;
        this.fundsFlowType = fundsFlowType;
    }

    public FundsFlow(Integer amount, Budget budget, String description, User byUser,
            LocalDateTime operationDate, FundsFlowType fundsFlowType) {
        this(null, amount, budget, description, byUser, operationDate, fundsFlowType);
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

    public FundsFlowType getFundsFlowType() {
        return fundsFlowType;
    }

    public void setFundsFlowType(FundsFlowType fundsFlowType) {
        this.fundsFlowType = fundsFlowType;
    }


    @Override
    public String toString() {
        return "FundsFlow [" + super.toString() + "amount=" + amount + ", description="
                + description + ", operationDate=" + operationDateTime + ", fundsFlowType=" + fundsFlowType
                + "]";
    }



}
