package com.example.mongoDbAPI.service;

import com.example.mongoDbAPI.model.Expense;
import com.example.mongoDbAPI.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(final Expense expense) {
        expenseRepository.insert(expense);
    }

    public void deleteExpense(String id) {
        expenseRepository.findById(id).orElseThrow(()->new RuntimeException(" ID does not exist "+id));

        expenseRepository.deleteById(id);
    }

    public List<Expense> findAllExpense() {

         List <Expense> sortedExpense = expenseRepository.findAll();
         Collections.sort(sortedExpense);

         return sortedExpense;

    }

    public Expense findExpenseByName(final String name) {
        return expenseRepository.findByExpenseName(name).
                orElseThrow(()->new RuntimeException(" The Expense doese not exist " + name));

    }

    public void updateExpense(final Expense expense) {
        Expense toUpdate = expenseRepository.findById(expense.getId()).
                orElseThrow(() -> new RuntimeException("Id not found " + expense.getId()));

        toUpdate.setExpenseAmount(expense.getExpenseAmount());
        toUpdate.setExpenseCategory(expense.getExpenseCategory());

        expenseRepository.save(toUpdate);
    }
}
