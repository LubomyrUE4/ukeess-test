package com.example.ukeesstest.dao.employee;

import com.example.ukeesstest.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;

    public List<Employee> findAll(String searchText) {
        return employeeDao.findAll(searchText);
    }

    public List<Employee> findAll(Long page, Long size, String search) {
        return employeeDao.findAll(page, size, search);
    }

    public Employee findById(Long id) {
        return employeeDao.findById(id).orElse(null);
    }

    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    public Employee update(Employee employee) {
        return employeeDao.update(employee);
    }

    public Employee deleteById(Long id) {
        Employee employeeToDelete = employeeDao.findById(id).orElse(null);
        employeeDao.deleteById(id);
        return employeeToDelete;
    }
}
