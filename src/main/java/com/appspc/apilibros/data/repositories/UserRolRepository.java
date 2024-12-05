package com.appspc.apilibros.data.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appspc.apilibros.api.dto.RolDto;

@Repository
public class UserRolRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRolRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;   
    }

    public void save(Integer user_id, Integer rol_id) {
        var sql = "INSERT INTO \"user_rol\" (\"user_id\", \"rol_id\", \"isActive\", \"modification\") VALUES ('"+user_id+"', '"+rol_id+"', '1', current_timestamp)";
        try {
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Integer user_id, Integer rol_id, Boolean isActive) {
        var isActiveRol = isActive ? "1" : "0";
        var sql = "UPDATE \"user_rol\" SET \"isActive\"='"+isActiveRol+"', \"modification\"=current_timestamp WHERE \"user_id\"='"+user_id+"' AND \"rol_id\"='"+rol_id+"'";
        System.out.println("SQL: " + sql);
        try {
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public RolDto findByUserId(Integer id) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM rol WHERE id = ?", (rs,rowNum)->     { 
                RolDto rol = new RolDto();
                rol.setId(rs.getInt("id"));
                rol.setName(rs.getString("name"));
                rol.setIsActive(rs.getBoolean("isActive"));
                return rol;
            }, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
