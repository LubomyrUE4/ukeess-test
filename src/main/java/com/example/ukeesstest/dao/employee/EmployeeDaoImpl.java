package com.example.ukeesstest.dao.employee;

import com.example.ukeesstest.domain.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setEmpID(rs.getLong("empID"));
        employee.setEmpName(rs.getString("empName"));
        employee.setEmpActive(rs.getBoolean("empActive"));
        employee.setEmp_dpID(rs.getLong("emp_dpID"));
        return employee;
    };

    @Override
    public List<Employee> findAll(Long page, Long size, String search) {
        String sql = "";
        if(search == null) {
            sql = "SELECT * FROM tbl_employees " +
                    "LIMIT " + size + " " +
                    "OFFSET " + page*size;
        } else {
            sql = "SELECT * FROM tbl_employees " + "WHERE lower(empName) LIKE lower(\'" + search + "%\')" +
                    "LIMIT " + size + " " +
                    "OFFSET " + page*size;
        }

        List<Employee> employees = jdbcTemplate.query(sql, rowMapper);
        return employees;
    }

    @Override
    public List<Employee> findAll(String search) {
        String sql = "";
        if(search == null) {
            sql = "SELECT empID, empName, empActive, emp_dpID FROM tbl_employees";
        } else {
            sql = "SELECT empID, empName, empActive, emp_dpID FROM tbl_employees WHERE lower(empName) LIKE lower(\'" + search + "%\')";
        }

        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Employee save(Employee employee) {
        String sql = "INSERT INTO tbl_employees(empName,empActive,emp_dpID) VALUES(?, ?,?,?)";
        int insert = jdbcTemplate.update(sql, employee.getEmpName(), employee.getEmpActive(), employee.getEmp_dpID());
        if(insert == 1) {
            return employee;
        } return null;
    }

    @Override
    public Long deleteById(Long id) {
        String sql = "DELETE FROM tbl_employees WHERE empID = ?";
        jdbcTemplate.update(sql,id);
        return id;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        String sql = "SELECT empID,empName,empActive,emp_dpID FROM tbl_employees WHERE empID = ?";
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public Employee update(Employee employee) {
        String sql = "UPDATE tbl_employees SET empName = ?, empActive = ?, emp_dpID = ? WHERE empID = ?";
        int update = jdbcTemplate.update(sql,employee.getEmpName(),employee.getEmpActive(),employee.getEmp_dpID(),
                employee.getEmpID());
        if(update == 1) {
            return employee;
        } return null;
    }

}
