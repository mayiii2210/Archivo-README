package com.appspc.apilibros.data.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.appspc.apilibros.data.interfaces.IRepository;
import com.appspc.apilibros.data.models.Rol;
import com.appspc.apilibros.data.models.User;

@Repository
public class UserRepository implements IRepository<User,Integer> {
    
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;   
    }

    public User findById (Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT \"user\".*, \"user_rol\".\"rol_id\", \"rol\".\"name\", \"rol\".\"isActive\" FROM \"user\" JOIN \"user_rol\" ON \"user_rol\".\"user_id\" = \"user\".\"id\" JOIN \"rol\" on \"rol\".\"id\" = \"user_rol\".\"rol_id\" JOIN \"clave\" ON \"clave\".\"user_id\" = \"user\".\"id\" WHERE \"user\".\"id\" = "+id+ "", (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setLastname(rs.getString("lastname"));
                user.setDoc_type(rs.getString("doc_type"));
                user.setDoc(rs.getString("doc"));
                user.setEmail(rs.getString("email"));
                user.setTel(rs.getString("tel"));
                user.setBirth(rs.getDate("birth"));
                user.setImage(rs.getString("image"));
                user.setBio(rs.getString("bio"));
                user.setAlias(rs.getString("alias"));
                user.setIsActive(rs.getBoolean("isActive"));
                Rol rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setName(rs.getString("name"));
                rol.setIsActive(rs.getBoolean("isActive"));
                user.setRol(rol);
                return user;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public User findByDoc (String doc) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE doc = ?", (rs,rowNum)->{
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setLastname(rs.getString("lastname"));
                user.setDoc_type(rs.getString("doc_type"));
                user.setDoc(rs.getString("doc"));
                user.setEmail(rs.getString("email"));
                user.setTel(rs.getString("tel"));
                user.setBirth(rs.getDate("birth"));
                user.setImage(rs.getString("image"));
                user.setBio(rs.getString("bio"));
                user.setAlias(rs.getString("alias"));
                user.setIsActive(rs.getBoolean("isActive"));
                return user;
            }, doc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public User findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE email = ?", (rs,rowNum)->{
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setLastname(rs.getString("lastname"));
                user.setDoc_type(rs.getString("doc_type"));
                user.setDoc(rs.getString("doc"));
                user.setEmail(rs.getString("email"));
                user.setTel(rs.getString("tel"));
                user.setBirth(rs.getDate("birth"));
                user.setImage(rs.getString("image"));
                user.setBio(rs.getString("bio"));
                user.setAlias(rs.getString("alias"));
                user.setIsActive(rs.getBoolean("isActive"));
                return user;
            }, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<User> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM \"user\"", (rs,rowNum)->{
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setLastname(rs.getString("lastname"));
                user.setDoc_type(rs.getString("doc_type"));
                user.setDoc(rs.getString("doc"));
                user.setEmail(rs.getString("email"));
                user.setTel(rs.getString("tel"));
                user.setBirth(rs.getDate("birth"));
                user.setImage(rs.getString("image"));
                user.setBio(rs.getString("bio"));
                user.setAlias(rs.getString("alias"));
                user.setIsActive(rs.getBoolean("isActive"));
                return user;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int save(User entity) {
        try {
            var ret = jdbcTemplate.update("INSERT INTO \"user\" (\"username\", \"lastname\", \"doc_type\", \"doc\", \"email\", \"tel\", \"birth\", \"image\", \"bio\", \"alias\", \"isActive\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '1')", 
                entity.getUsername(),
                entity.getLastname(),
                entity.getDoc_type(),
                entity.getDoc(),
                entity.getEmail(),
                entity.getTel(),
                entity.getBirth(),
                entity.getImage(),
                entity.getBio(),
                entity.getAlias()
            );
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int update(User entity) {
        try {
            var activo = entity.getIsActive() ? "1" : "0";
            var sql = "UPDATE \"user\" SET \"username\" = '"+entity.getUsername()+"', \"lastname\" = '"+entity.getLastname()+"', \"doc_type\" = '"+entity.getDoc_type()+"', \"tel\" = '"+entity.getTel()+"', \"bio\" = '"+entity.getBio()+"', \"alias\" = '"+entity.getAlias()+"', \"image\" = '"+entity.getImage()+"',\"isActive\"='"+ activo +"' WHERE \"id\" = "+entity.getId()+"";

            System.out.println(sql);

            var ret = jdbcTemplate.update(sql);
            return ret;
        } catch (Exception e) {
            System.out.println("Error en update: " + e.getMessage());
            throw e;
        }
    }

    public int delete(Integer id) {
        try {
            var ret = jdbcTemplate.update("UPDATE \"user\" SET \"isActive\"='0' WHERE id = "+ id +"");
            return ret;
        } catch (Exception e) {
            System.out.println("Error en delete: " + e.getMessage());
            throw e;
        }
    }

    public User findByUserBook (Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT \"user\".\"id\" JOIN \"user_book\" on \"user_book\".\"user_id\" =  \"user\".\"id\" WHERE \"user\".\"id\" = "+id+ "", (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getInt("id"));
                return user;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
