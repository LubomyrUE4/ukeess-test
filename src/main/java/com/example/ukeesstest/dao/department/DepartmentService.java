package com.example.ukeesstest.dao.department;

import com.example.ukeesstest.dao.employee.EmployeeDao;
import com.example.ukeesstest.domain.Department;
import com.example.ukeesstest.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentDao departmentDao;

    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    public Department findById(Long id) {
        return departmentDao.findById(id).orElse(null);
    }

    public Department save(Department department) {
        return departmentDao.save(department);
    }

    public Department deleteById(Long id) {
        Department departmentToDelete = departmentDao.findById(id).orElse(null);
        departmentDao.deleteById(id);
        return departmentToDelete;
    }

    public Department update(Department department) {
        return departmentDao.update(department);
    }
}
