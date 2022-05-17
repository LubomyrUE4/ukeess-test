package com.example.ukeesstest.dao.employee;

import com.example.ukeesstest.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao{
    List<Employee> findAll(Long page, Long size, String search);
    List<Employee> findAll(String search);
    Employee save(Employee employee);
    Long deleteById(Long id);
    Optional<Employee> findById(Long id);
    Employee update(Employee employee);
}
