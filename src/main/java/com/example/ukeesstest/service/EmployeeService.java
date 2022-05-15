package com.example.ukeesstest.service;

import com.example.ukeesstest.domain.Department;
import com.example.ukeesstest.domain.Employee;
import com.example.ukeesstest.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public List<Employee> findAll(String searchText) {
        List<Employee> result = searchText == null ? employeeRepo.findAll()
                : employeeRepo.findAll().stream().filter(s -> s.getEmpName().startsWith(searchText)).collect(Collectors.toList());
        return result;
    }

    public Employee findById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee deleteById(Long id) {
        Employee employeeToDelete = employeeRepo.findById(id).orElse(null);
        employeeRepo.deleteById(id);
        return employeeToDelete;
    }
}
