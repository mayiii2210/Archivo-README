package com.appspc.apilibros.data.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appspc.apilibros.api.dto.ClaveDto;
import com.appspc.apilibros.data.models.Clave;

@Repository
public class ClaveRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClaveRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public Clave findByUser_id(Integer user_id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM clave WHERE id = ?", (rs,rowNum)->{
                Clave clave = new Clave();
                clave.setId(rs.getInt("id"));
                clave.setUser_id(rs.getInt("user_id"));
                clave.setHashClave(rs.getString("hashClave"));
                clave.setIsActive(rs.getBoolean("isActive"));
                clave.setModification(rs.getTimestamp("modification"));
                return clave;
            }, user_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public void save (Integer user_id, String hashClave) {
        var sql = "INSERT INTO \"clave\" (\"user_id\", \"hashClave\", \"isActive\", \"modification\") VALUES ('"+user_id+"', '"+hashClave+"', '1', current_timestamp)";
        try {
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int update(Integer user_id, String hashClave, Boolean isActive) {
        var activo =  isActive ? 1 : 0;
        var sql = "UPDATE \"clave\" SET \"hashClave\" = '"+hashClave+"', \"isActive\" = '"+ activo +"', \"modification\" = current_timestamp WHERE \"user_id\"='"+user_id+"'";
        System.out.println("SQL: " + sql);
        try {
            var ret = jdbcTemplate.update(sql);
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ClaveDto findByUserClave(Integer id) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM clave WHERE id = ?", (rs,rowNum)->{ 
                ClaveDto clave = new ClaveDto();
                clave.setId(rs.getInt("id"));
                clave.setUser_id(rs.getInt("user_id"));
                clave.setHashClave(rs.getString("hashClave"));
                clave.setIsActive(rs.getBoolean("isActive"));
                clave.setModification(rs.getTimestamp("modification"));
                return clave;
            }, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
