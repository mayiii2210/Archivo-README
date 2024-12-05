package com.appspc.apilibros.data.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appspc.apilibros.api.dto.CategoryDto;

@Repository
public class BookCategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookCategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Integer book_id, Integer category_id) {
        var sql = "INSERT INTO \"cat_book\" (\"category_id\", \"book_id\") VALUES ('"+category_id+"', '"+book_id+"')";
        try {
            jdbcTemplate.update(sql);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Integer book_id, Integer category_id, Boolean isActive) {
        var isActiveCategory = isActive ? "1" : "0";
        var sql = "UPDATE \"cat_book\" SET \"isActive\"='"+isActiveCategory+"', WHERE \"book_id\"='"+book_id+"' AND \"category_id\"='"+category_id+"'";
        System.out.println("SQL: " + sql);
        try {
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public CategoryDto findByBookId(Integer id) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM category WHERE id = ?", (rs,rowNum)->{ 
                CategoryDto category = new CategoryDto();
                category.setId(rs.getInt("id"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setIsActive(rs.getBoolean("isActive"));
                return category;
            }, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
