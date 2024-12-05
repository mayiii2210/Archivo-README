package com.appspc.apilibros.data.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.appspc.apilibros.data.models.Book;
import com.appspc.apilibros.data.models.Category;
import com.appspc.apilibros.data.interfaces.IRepository;
@Repository

public class BookRepository implements IRepository<Book,Integer> {
     private final JdbcTemplate jdbcTemplate;

     public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;   
    }
    public Book findById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT  \"books\".*, \"cat_book\".\"category_id\", \"category\".\"nameCategory\", \"category\".\"isActive\" FROM \"books\" JOIN \"cat_book\" ON \"cat_book\".\"book_id\" = \"books\".\"id\" JOIN \"category\" ON \"category\".\"id\" = \"cat_book\".\"category_id\" WHERE \"books\".\"id\" = "+id+ "", (rs,rowNum) -> {

                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setGender(rs.getString("gender"));
                book.setUrl_image(rs.getString("url_image"));
                book.setUrl_pdf(rs.getString("url_pdf"));
                book.setUrl_audio(rs.getString("url_audio"));
                book.setDescription(rs.getString("description"));
                book.setIsActive(rs.getBoolean("isActive"));

                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setIsActive(rs.getBoolean("isActive"));
                book.setCategory(category);
                return book;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Book findByBookName (String bookName) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"books\" WHERE bookname = ?", (rs,rowNum) -> {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setGender(rs.getString("gender"));
                book.setUrl_image(rs.getString("url_image"));
                book.setUrl_pdf(rs.getString("url_pdf"));
                book.setUrl_audio(rs.getString("url_audio"));
                book.setDescription(rs.getString("description"));
                book.setIsActive(rs.getBoolean("isActive"));
                return book;
            }, bookName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Book> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM \"books\"", (rs,rowNum)->{
               Book book = new Book();
               book.setId(rs.getInt("id"));
               book.setBookName(rs.getString("bookname"));
               book.setAuthor(rs.getString("author"));
               book.setGender(rs.getString("gender"));
               book.setUrl_image(rs.getString("url_image"));
               book.setUrl_pdf(rs.getString("url_pdf"));
               book.setUrl_audio(rs.getString("url_audio"));
               book.setDescription(rs.getString("description"));
               book.setIsActive(rs.getBoolean("isActive"));
               return book;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int save(Book entity) {
        try {
            var ret = jdbcTemplate.update("INSERT INTO \"books\" (\"bookname\", \"gender\", \"author\", \"url_image\", \"url_pdf\",\"url_audio\", \"description\", \"isActive\") VALUES (?, ?, ?, ?, ?, ?, ?, '1')",
                    entity.getBookName(),
                    entity.getGender(),
                    entity.getAuthor(),
                    entity.getUrl_image(),
                    entity.getUrl_pdf(),
                    entity.getUrl_audio(),
                    entity.getDescription()
                );
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public int update(Book entity) {
        var activo = entity.getIsActive() ? "1" : "0";
        var sql = "UPDATE \"books\" SET \"bookname\" = '" + entity.getBookName() + "', \"author\" = '" + entity.getAuthor()+ "', \"gender\" = '"+ entity.getGender()+ "', \"url_image\" = '"  + entity.getUrl_image()+"', \"url_pdf\" = '"+entity.getUrl_pdf()+"', \"url_audio\" = '"+ entity.getUrl_audio()+"', \"description\" = '"+ entity.getDescription()+"', \"isActive\"='"+activo+"'  WHERE \"id\" = " + entity.getId();
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
            var ret = jdbcTemplate.update("UPDATE \"books\" SET \"isActive\"='0' WHERE id = "+ id +"");
            return ret;
        } catch (Exception e) {
            System.out.println("Error en delete: " + e.getMessage());
            throw e;
        }
    }

    public Book findByUserBook (Integer user_id) {
        try {
            return jdbcTemplate.queryForObject
            ("SELECT \"books\".\"id\", \"books\".\"bookname\" FROM \"user_book\" JOIN \"user\" on \"user_book\".\"user_id\" = \"user\".\"id\" JOIN \"books\" on \"user_book\".\"book_id\" = \"books\".\"id\"  WHERE \"user\".\"id\" = \"user_id\"", (rs,rowNum)->{
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("bookname"));
                book.setIsActive(rs.getBoolean("isActive"));
                return book;
            }, user_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public String findBookNameById(int id) {
        String sql = "SELECT bookname FROM books WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getString("bookname"), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
    