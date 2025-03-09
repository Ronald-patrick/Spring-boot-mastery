package com.ronald.springboot.cruddemo.service.Impl;

import com.ronald.springboot.cruddemo.dao.EmployeeDAO;
import com.ronald.springboot.cruddemo.dto.Employee;
import com.ronald.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employeeOpt = employeeDAO.findById(id);
        if (employeeOpt.isPresent()) {
            return employeeOpt.get();
        } else {
            return null;
        }
    }

    @Override
    public String saveOrUpdate(Employee employee) {
        int id =  employeeDAO.saveOrUpdate(employee);

        System.out.println(id);

        if(id != -1){
            return "Employee with id : " + id + " Saved";
        }
        else {
            return "Employee Save failed";
        }
    }

    @Override
    public String deleteById(int id) {
        boolean isDeleted = employeeDAO.deleteById(id);
        if(isDeleted){
            return "Employee with id : " + id + " Deleted";
        }
        else{
            return "Employee Delete failed";
        }
    }


}
