package com.example.ukeesstest.controller;

import com.example.ukeesstest.dao.department.DepartmentService;
import com.example.ukeesstest.dao.employee.EmployeeService;
import com.example.ukeesstest.domain.Department;
import com.example.ukeesstest.exception.DefaultException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Api(description = "Department Controller")
@CrossOrigin
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    @ApiOperation("Create Department")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.save(department), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("Get All Departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Department By ID")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation("Update Department")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.update(department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Department By ID")
    public ResponseEntity<Department> deleteDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.deleteById(id), HttpStatus.OK);
    }

}

