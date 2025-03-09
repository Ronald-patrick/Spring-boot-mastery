package com.ronald.springboot.cruddemo.dao;

import com.ronald.springboot.cruddemo.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    List<Employee> findAll();

    Optional<Employee> findById(int id);

    int saveOrUpdate(Employee employee);

    boolean deleteById(int id);
}
