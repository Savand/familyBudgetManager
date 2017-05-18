package org.andsav.family_budget_manager.model;

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

import org.andsav.family_budget_manager.model.abstractentity.BaseEntity;
import org.andsav.family_budget_manager.model.enums.FundsFlowType;
import org.andsav.family_budget_manager.util.date_convertor.LocalDateTimeAttributeConverter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Andrii_Savka
 *
 */

@Entity
@Table(name = "FUNDSFLOWS")
@NamedQueries({
        @NamedQuery(name = FundsFlow.DELETE, query = "DELETE FROM FundsFlow m WHERE m.id=:id"),
        @NamedQuery(name = FundsFlow.BY_BUDGET_ID,
                query = "SELECT m FROM FundsFlow m WHERE m.budget.id=:id ORDER BY m.id"),
        @NamedQuery(name = FundsFlow.BY_BUDGET_ID_BETWEEN_DATES,
        query = "SELECT m FROM FundsFlow m WHERE m.budget.id=:id AND"
                + " m.operationDateTime BETWEEN :startDate AND :endDate ORDER BY m.operationDateTime")})
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
    @NotBlank
    @Column(nullable = false, name = "fundsflow_type")
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

    public FundsFlowType getType() {
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
