package com.example.mongoDbAPI.model;

import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document("expense")
public class Expense implements Comparable <Expense>{
    @Id
    private String id ;
    @ApiModelProperty(notes = "Expense name", name = "exenseName",required = true, value = "Food Expense")
    @Field(name = "name")
    @Indexed(unique = true)
    private String expenseName;
    @ApiModelProperty(notes = "Expense category must be one of the following  ENTERTAIMENT, GROCERIRES, RESTAURANT", name = "expenseCategory",required = true, value = "GROCERIRES")
    @Field(name = "category")
    private ExpenseCategory expenseCategory;
    @ApiModelProperty(notes = "Must be Greater than 0", name = "amount",required = true, value = "1")
    @Field(name = "amount")
    private BigDecimal expenseAmount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (!id.equals(expense.id)||!expenseName.equals(expense.expenseName)) return false;
        return expenseName.equals(expense.expenseName)&& id.equals(expense.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + expenseName.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    @Override
    public int compareTo(Expense o) {
        return this.getExpenseAmount().compareTo(o.getExpenseAmount());
    }
}
