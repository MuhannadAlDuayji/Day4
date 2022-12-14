package com.example.day4.controller;

import com.example.day4.model.Employee;
import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    ArrayList<Employee> employees = new ArrayList<>();
    static final Faker fakerData = new Faker();

    static final Random randomNumber = new Random();

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees(){

        return ResponseEntity.ok(employees);
    }
    @GetMapping("/employee/{index}")
    public ResponseEntity<?> getEmployees(@PathVariable int index){
        return ResponseEntity.ok(employees.get(index));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployees(@RequestBody @Valid Employee employee, Errors errors){

        if (errors.hasErrors())
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors.getFieldError().getDefaultMessage());
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("employee created.");
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateEmployee(@PathVariable int index, @RequestBody @Valid Employee employee){

        employees.set(index,employee);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(("Employee updated"));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteEmployee(){
        employees = null;
        employees = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(("All data deleted"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int index){
        employees.remove(index);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Employee deleted!");
    }



    @GetMapping("/generate/employees")
    public ResponseEntity<?> generateEmployees(){

        for (int i = 0; i < 20; i++) {
            employees.add(new Employee(i+1,randomFullName(),randomAge(),false, randomNumber.nextInt(50)+1960,randomNumber.nextInt(31)));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Employees generated");
    }
    private String randomFullName(){
        return fakerData.name().firstName() +" "+fakerData.name().lastName();
    }

    private String randomPassword(){return fakerData.random().hex();}

    private  int randomAge(){
        return randomNumber.nextInt(75)+25;
    }



}
