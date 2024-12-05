package com.appspc.apilibros.data.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appspc.apilibros.data.interfaces.IRepository;
import com.appspc.apilibros.data.models.Logs;

@Repository
public class LogsRepository implements IRepository<Logs,Integer> {

    private final JdbcTemplate jdbcTemplate;

    public LogsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Logs findById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM logs WHERE id = ?", (rs, rowNum) -> {
                Logs logs = new Logs();
                logs.setId(rs.getInt("id"));
                logs.setError_stack(rs.getString("error_stack"));
                logs.setTimestamp(rs.getDate("timestamp"));
                logs.setDescription(rs.getString("description"));
                return logs;
            }, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Logs> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM logs", (rs, rowNum) -> {
                Logs logs = new Logs();
                logs.setId(rs.getInt("id"));
                logs.setError_stack(rs.getString("error_stack"));
                logs.setTimestamp(rs.getDate("timestamp"));
                logs.setDescription(rs.getString("description"));
                return logs;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int save(Logs logs) {
        try {
            var ret = jdbcTemplate.update("INSERT INTO logs (error_stack, timestamp, description) VALUES (?, ?, ?)",
                logs.getError_stack(),
                new Date(),
                logs.getDescription()
            );
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int update(Logs logs) {
        try {
            var ret = jdbcTemplate.update("UPDATE logs SET error_stack = ?, timestamp = ?, description = ? WHERE id = ?",
                logs.getError_stack(),
                logs.getTimestamp(),
                logs.getDescription(),
                logs.getId()
            );
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int delete(Integer id) {
        try {
            var ret = jdbcTemplate.update("DELETE FROM logs WHERE id = ?", id);
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
