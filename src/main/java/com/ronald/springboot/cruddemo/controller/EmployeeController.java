package com.ronald.springboot.cruddemo.controller;

import com.ronald.springboot.cruddemo.dto.Employee;
import com.ronald.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id){
        return employeeService.findById(id);
    }

    @PostMapping("/employees")
    public String save(@RequestBody Employee employee){
        employee.setId(0);
        return employeeService.saveOrUpdate(employee);
    }

    @PatchMapping("/employees")
    public String update(@RequestBody Employee employee){
        return employeeService.saveOrUpdate(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String delete(@PathVariable int id){
        return employeeService.deleteById(id);
    }


}
