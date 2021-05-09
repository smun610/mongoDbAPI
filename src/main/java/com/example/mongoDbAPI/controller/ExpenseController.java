package com.example.mongoDbAPI.controller;

import com.example.mongoDbAPI.model.Expense;
import com.example.mongoDbAPI.service.ExpenseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.*;
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
    @ApiOperation(value = "Post multiple expenses", response = Expense.class, tags = "Post multiple expenses")
    @PostMapping ("/list")
    public ResponseEntity addExpense(@RequestBody final List<Expense> expense) {
        expense.forEach(e->expenseService.addExpense(e));
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @ApiOperation(value = "Delete single expense by ID", response = Expense.class, tags = "Delete single  expense by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(@PathVariable final String id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @ApiOperation(value = "Delete list of expense by ID", response = Expense.class, tags = "Delete list of expense by ID")
    @DeleteMapping("/list")
    public ResponseEntity deleteExpense( @RequestBody final List<Expense> expense) {
        expense.forEach(e-> expenseService.deleteExpense(e.getId()));
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Get all Expenses ", response = Expense.class, tags = "Get All Expense")
    @GetMapping
    public ResponseEntity findAllExpense() {
        return ResponseEntity.ok(expenseService.findAllExpense());

    }
    @ApiOperation(value = "Get specific Expenses by name", response = Expense.class, tags = "Get specific Expense by name")
    @GetMapping("/{name}")
    public ResponseEntity  findExpenseByName(@PathVariable final String name) {
        return ResponseEntity.ok(expenseService.findExpenseByName(name));


    }
    @ApiOperation(value = "Update Expense amount and category", response = Expense.class, tags = "Update Expense")
    @PutMapping
    public ResponseEntity updateExpense(@RequestBody final Expense expense) {

        expenseService.updateExpense(expense);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
