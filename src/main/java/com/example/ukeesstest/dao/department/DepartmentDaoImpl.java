package com.example.ukeesstest.dao.department;

import com.example.ukeesstest.domain.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private final JdbcTemplate jdbcTemplate;

    public DepartmentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Department> rowMapper = (rs, rowNum) -> {
        Department department = new Department();
        department.setDpID(rs.getLong("dpID"));
        department.setDpName(rs.getString("dpName"));
        return department;
    };

    @Override
    public List<Department> findAll() {
        String sql = "SELECT dpID, dpName FROM tbl_departments";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Department save(Department department) {
        String sql = "INSERT INTO tbl_departments(dpName) VALUES(?)";
        int insert = jdbcTemplate.update(sql, department.getDpName());
        if(insert == 1) {
            return department;
        } return null;
    }

    @Override
    public Long deleteById(Long id) {
        String sql = "DELETE FROM tbl_departments WHERE dpID = ?";
        jdbcTemplate.update(sql,id);
        return id;
    }

    @Override
    public Optional<Department> findById(Long id) {
        String sql = "SELECT dpID,dpName FROM tbl_departments WHERE dpID = ?";
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public Department update(Department department) {
        String sql = "UPDATE tbl_departments SET dpName = ? WHERE dpID = ?";
        int update = jdbcTemplate.update(sql,department.getDpName(), department.getDpID());
        if(update == 1) {
            return department;
        } return null;
    }
}
