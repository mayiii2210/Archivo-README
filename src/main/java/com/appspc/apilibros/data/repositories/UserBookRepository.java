package com.appspc.apilibros.data.repositories;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appspc.apilibros.api.dto.BookDto;
import com.appspc.apilibros.data.models.UserBook;

@Repository
public class UserBookRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;   
    }

    public UserBook findById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM user_book WHERE id = "+id+"", (rs,rowNum)->{
                UserBook userBook = new UserBook();
                userBook.setId(rs.getInt("id"));
                userBook.setUser_id(rs.getInt("user_id"));
                userBook.setBook_id(rs.getInt("book_id"));
                userBook.setBookName(rs.getString("bookname"));
                userBook.setProgress(rs.getInt("progress"));
                userBook.setRating(rs.getInt("rating"));
                userBook.setIsFav(rs.getBoolean("isFav"));
                return userBook;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
        
    public void save(UserBook entity) {
        try {
            var isFavBook = entity.getIsFav() ? "1" : "0";
            var sql = "INSERT INTO \"user_book\" (\"user_id\", \"book_id\", \"bookname\", \"progress\", \"rating\", \"isFav\") VALUES ("+ entity.getUser_id() +", "+ entity.getBook_id() +", '"+ entity.getBookName() +"', '"+ entity.getProgress() +"', '"+ entity.getRating() +"', '"+ isFavBook +"')";
            System.out.println("SQL: " + sql);
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }  

    public void update(UserBook entity) {
        try {
            var isFavBook = entity.getIsFav() ? "1" : "0";
            var sql = "UPDATE \"user_book\" SET \"isFav\"='"+isFavBook+"', \"progress\"='"+entity.getProgress()+"', \"rating\"='"+entity.getRating()+"' WHERE \"id\"='"+entity.getId()+"'";
        System.out.println("SQL: " + sql);
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<UserBook> findByUserBook(Integer user_id) {
        try{
            var sql = "SELECT user_book.*, \"books\".\"bookname\" FROM \"user\" JOIN \"user_book\" ON \"user\".\"id\" = \"user_book\".\"user_id\" JOIN \"books\" ON \"user_book\".\"book_id\" = \"books\".\"id\" WHERE \"user\".\"id\" = "+user_id+"";
            return jdbcTemplate.query(sql, (rs,rowNum) -> {
                UserBook userBook = new UserBook();
                userBook.setId(rs.getInt("id"));
                userBook.setUser_id(rs.getInt("user_id"));
                userBook.setBook_id(rs.getInt("book_id"));
                userBook.setBookName(rs.getString("bookname"));
                userBook.setProgress(rs.getInt("progress"));
                userBook.setRating(rs.getInt("rating"));
                userBook.setIsFav(rs.getBoolean("isFav"));
                return userBook;
                });
            }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public BookDto findByBookId(Integer id) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id = ?", (rs,rowNum)->{ 
                BookDto book = new BookDto();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("bookname"));
                return book;
            }, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Integer findBookNameById(Integer book_id) {
        String sql = "SELECT bookname FROM books WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, book_id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontró ningún libro con ID: " + book_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
                return book_id;
    }
}
