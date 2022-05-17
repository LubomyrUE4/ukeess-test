package com.example.ukeesstest.controller;

import com.example.ukeesstest.dao.employee.EmployeeService;
import com.example.ukeesstest.domain.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Api(description = "Employee Controller")
@CrossOrigin
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    @ApiOperation("Create Employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("Get All Employees")
    public ResponseEntity<List<Employee>> findPaginated(@RequestParam(name = "search", required = false) String searchText,
                                                        @RequestParam(name = "page", required = false) Long page,
                                                        @RequestParam(name = "size", required = false) Long size) {

        if(page == null && size == null) {
            return new ResponseEntity<>(employeeService.findAll(searchText), HttpStatus.OK);
        }

        return new ResponseEntity<>(employeeService.findAll(page, size, searchText), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Employee By ID")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation("Update Employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Employee By ID")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.deleteById(id), HttpStatus.OK);
    }

}
