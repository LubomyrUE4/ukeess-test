package com.example.ukeesstest.dao.authUser;

import com.example.ukeesstest.domain.AuthUser;
import com.example.ukeesstest.domain.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthUserDaoImpl implements AuthUserDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthUserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<AuthUser> rowMapper = (rs, rowNum) -> {
        AuthUser authUser = new AuthUser();
        authUser.setUserID(rs.getLong("userID"));
        authUser.setUserName(rs.getString("userName"));
        authUser.setUserPassword(rs.getString("userPassword"));
        return authUser;
    };

    @Override
    public List<AuthUser> findAll() {
        String sql = "SELECT userID, userName, userPassword FROM tbl_users";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public AuthUser save(AuthUser authUser) {
        String sql = "INSERT INTO tbl_users(userName, userPassword) VALUES(?, ?)";
        int insert = jdbcTemplate.update(sql, authUser.getUsername(), authUser.getUserPassword());
        if(insert == 1) {
            return authUser;
        } return null;
    }

    @Override
    public Long deleteById(Long id) {
        String sql = "DELETE FROM tbl_users WHERE userID = ?";
        jdbcTemplate.update(sql,id);
        return id;
    }

    @Override
    public Optional<AuthUser> findById(Long id) {
        String sql = "SELECT userID,userName,userPassword FROM tbl_users WHERE userID = ?";
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public AuthUser update(AuthUser authUser) {
        String sql = "UPDATE tbl_users SET userName = ? WHERE userID = ?";
        int update = jdbcTemplate.update(sql, authUser.getUsername(), authUser.getUserID());
        if(update == 1) {
            return authUser;
        } return null;
    }

    @Override
    public Optional<AuthUser> findByUsername(String userName) {
        String sql = "SELECT userID,userName,userPassword FROM tbl_users WHERE userName = ?";
        return jdbcTemplate.query(sql, rowMapper, userName)
                .stream()
                .findFirst();
    }
}
