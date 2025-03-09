package com.ronald.springboot.cruddemo.dao;

import com.ronald.springboot.cruddemo.dto.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    public static final String GET_ALL_EMPLOYEES = "SELECT * FROM employee";
    public static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";
    public static final String GET_EMPLOYEE_COUNT = "SELECT COUNT(ep.id) FROM employee ep WHERE id = ?";
    public static final String INSERT_EMPLOYEE = "INSERT INTO employee (first_name, last_name, email) VALUES (?, ?, ?)";
    public static final String UPDATE_EMPLOYEE = "UPDATE employee SET first_name=?,last_name=?,email=? WHERE id = ?";
    public static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Employee> findAll() {

        return jdbcTemplate.query(GET_ALL_EMPLOYEES, (rs, rowNum) -> mapRowToEmployee(rs));
    }

    @Override
    public Optional<Employee> findById(int id) {
        Employee employee =  jdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID, (rs, rowNum) -> mapRowToEmployee(rs),id);

        return Optional.ofNullable(employee);
    }


    @Override
    public int saveOrUpdate(Employee employee) {
        Integer count = jdbcTemplate.queryForObject(GET_EMPLOYEE_COUNT,Integer.class,employee.getId());
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            if(Integer.valueOf(0).equals(count)){
                jdbcTemplate.update(
                        connection -> {
                            PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE, new String[]{"id"});
                            ps.setString(1, employee.getFirstName());
                            ps.setString(2, employee.getLastName());
                            ps.setString(3, employee.getEmail());
                            return ps;
                        },
                        keyHolder
                );
            } else {
                jdbcTemplate.update(UPDATE_EMPLOYEE,employee.getFirstName(),employee.getLastName(),employee.getEmail(),employee.getId());
            }
        }
        catch (DataAccessException e){
            System.out.println(e.getMessage());
        }

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue()  : -1;
    }

    @Override
    public boolean deleteById(int id) {
        int rowsAffected = jdbcTemplate.update(DELETE_EMPLOYEE, id);
        return rowsAffected > 0;
    }


    private Employee mapRowToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setEmail(rs.getString("email"));
        return employee;
    }

}
