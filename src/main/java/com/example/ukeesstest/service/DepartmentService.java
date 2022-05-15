package com.example.ukeesstest.service;

import com.example.ukeesstest.domain.Department;
import com.example.ukeesstest.domain.Employee;
import com.example.ukeesstest.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    public Department findById(Long id) {
        return departmentRepo.findById(id).orElse(null);
    }

    public Department save(Department department) {
        return departmentRepo.save(department);
    }

    public Department deleteById(Long id) {
        Department departmentToDelete = departmentRepo.findById(id).orElse(null);
        departmentRepo.deleteById(id);
        return departmentToDelete;
    }
}
