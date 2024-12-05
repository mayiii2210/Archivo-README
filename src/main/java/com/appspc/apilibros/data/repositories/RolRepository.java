package com.appspc.apilibros.data.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.appspc.apilibros.data.interfaces.IRepository;
import com.appspc.apilibros.data.models.Rol;

@Repository
public class RolRepository implements IRepository<Rol,Integer> {
    
    private final JdbcTemplate jdbcTemplate;

    public RolRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;   
    }

    public Rol findById (Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"rol\" WHERE id = ?", (rs,rowNum)->{
                Rol rol = new Rol();
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

    public Rol findByUserId (Integer id) {
        try {
            return jdbcTemplate.queryForObject
            ("SELECT rol.* FROM \"user_rol\" JOIN \"user\" on \"user_rol\".\"user_id\" = \"user\".\"id\" JOIN \"rol\" on \"user_rol\".\"rol_id\" = \"rol\".\"id\"  WHERE \"user\".\"id\" = \"user_id\"", (rs,rowNum)->{
                Rol rol = new Rol();
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

    public List<Rol> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM \"rol\"", (rs,rowNum)->{
                Rol rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setName(rs.getString("name"));
                rol.setIsActive(rs.getBoolean("isActive"));
                return rol;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int save(Rol entity) {
        try {
            var ret = jdbcTemplate.update("INSERT INTO \"rol\" (\"name\", \"isActive\") VALUES (?, '1')", entity.getName());
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int update(Rol entity) {

        var isActive = entity.getIsActive() ? "1" : "0";
        var sql = "UPDATE \"rol\" SET \"name\" = '" + entity.getName() + "', \"isActive\" = '" + isActive + "' WHERE \"id\" = " + entity.getId();
        System.out.println("SQL: " + sql);
        try {
            var ret = jdbcTemplate.update(sql);
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int delete(Integer id) {
        try {
            var ret = jdbcTemplate.update("UPDATE \"rol\" SET \"isActive\"='0' WHERE id = "+ id +"");
            return ret;
        } catch (Exception e) {
            System.out.println("Error en delete: " + e.getMessage());
            throw e;
        }
    }

}
