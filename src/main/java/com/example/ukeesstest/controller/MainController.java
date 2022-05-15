package com.example.ukeesstest.controller;

import com.example.ukeesstest.domain.Department;
import com.example.ukeesstest.domain.Employee;
import com.example.ukeesstest.service.DepartmentService;
import com.example.ukeesstest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class MainController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(name = "search", required = false) String searchText) {
        return new ResponseEntity<>(employeeService.findAll(searchText), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        log.info(employee.getEmpId() + " ");
        if(employeeService.findById(employee.getEmpId()) != null) {

            return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.deleteById(id), HttpStatus.OK);
    }



    @PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.save(department), HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/departments")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        if(employeeService.findById(department.getDpID()) != null) {
            return new ResponseEntity<>(departmentService.save(department), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Department> deleteDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.deleteById(id), HttpStatus.OK);
    }

}
