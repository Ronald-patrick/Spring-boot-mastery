package com.ronald.springboot.cruddemo.service;

import com.ronald.springboot.cruddemo.dto.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    String saveOrUpdate(Employee employee);

    String deleteById(int id);
}
