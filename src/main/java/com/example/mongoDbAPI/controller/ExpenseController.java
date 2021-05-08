package com.example.mongoDbAPI.controller;

import com.example.mongoDbAPI.model.Expense;
import com.example.mongoDbAPI.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity addExpense(@RequestBody final Expense expense) {

        expenseService.addExpense(expense);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping ("/list")
    public ResponseEntity addExpense(@RequestBody final List<Expense> expense) {
        expense.forEach(e->expenseService.addExpense(e));
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(@PathVariable final String id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/list")
    public ResponseEntity deleteExpense( @RequestBody final List<Expense> expense) {
        expense.forEach(e-> expenseService.deleteExpense(e.getId()));
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping
    public ResponseEntity findAllExpense() {
        return ResponseEntity.ok(expenseService.findAllExpense());

    }

    @GetMapping("/{name}")
    public ResponseEntity  findExpenseByName(@PathVariable final String name) {
        return ResponseEntity.ok(expenseService.findExpenseByName(name));


    }

    @PutMapping
    public ResponseEntity updateExpense(@RequestBody final Expense expense) {

        expenseService.updateExpense(expense);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
