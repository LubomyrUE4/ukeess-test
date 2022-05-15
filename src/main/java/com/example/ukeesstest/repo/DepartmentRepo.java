package com.example.ukeesstest.repo;

import com.example.ukeesstest.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
