package com.example.ukeesstest.repo;

import com.example.ukeesstest.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
