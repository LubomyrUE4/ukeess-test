package com.example.ukeesstest.dao.department;

import com.example.ukeesstest.domain.Department;
import com.example.ukeesstest.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {
    List<Department> findAll();
    Department save(Department department);
    Long deleteById(Long id);
    Optional<Department> findById(Long id);
    Department update(Department department);

}
