package com.appspc.apilibros.data.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import com.appspc.apilibros.data.models.Category;
import com.appspc.apilibros.data.interfaces.IRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public class CategoryRepository implements IRepository<Category, Integer> {
    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Category findById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"category\" WHERE \"category\".\"id\" = "+id+ "", (rs, rowNum) -> {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setIsActive(rs.getBoolean("isActive"));
                return category;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Category> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM \"category\"", (rs, rowNum) -> {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setIsActive(rs.getBoolean("isActive"));
                return category;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Category findByBookId (Integer book_id) {
        try {
            return jdbcTemplate.queryForObject
            ("SELECT category.* FROM \"cat_book\" JOIN \"books\" on \"cat_book\".\"book_id\" = \"books\".\"id\" JOIN \"category\" on \"cat_book\".\"category_id\" = \"category\".\"id\"  WHERE \"books\".\"id\" = \"book_id\"", (rs,rowNum)->{
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setIsActive(rs.getBoolean("isActive"));
                return category;
            }, book_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int save(Category entity) {
        try {
            var sql = "INSERT INTO \"category\" (\"nameCategory\", \"isActive\") VALUES ('" + entity.getNameCategory() + "','1')";
            var ret = jdbcTemplate.update(sql);
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int update(Category entity) {
        var activo =  entity.getIsActive() ? 1 : 0;
        var sql = "UPDATE \"category\" SET \"nameCategory\" = '" + entity.getNameCategory() + "', \"isActive\" = '"+activo+"' WHERE id = '"+entity.getId()+"'";
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
            var ret = jdbcTemplate.update(("UPDATE \"category\" SET \"isActive\"='0' WHERE id = "+id+""));
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
