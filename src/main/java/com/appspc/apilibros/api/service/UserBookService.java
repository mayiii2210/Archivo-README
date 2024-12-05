package com.appspc.apilibros.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.appspc.apilibros.api.dto.UserBookDto;
import com.appspc.apilibros.data.models.UserBook;
import com.appspc.apilibros.data.repositories.BookRepository;
import com.appspc.apilibros.data.repositories.UserBookRepository;

@Service
public class UserBookService {

    private final UserBookRepository userBookRepository;
    private final BookRepository bookRepository;

    public UserBookService(UserBookRepository userBookRepository, BookRepository bookRepository) {
        this.userBookRepository = userBookRepository;
        this.bookRepository = bookRepository;
    }
    
    public void save(UserBookDto userBookDto) {
        var userBooks = new UserBook();
        userBooks.setUser_id(userBookDto.getUser_id());
        userBooks.setBook_id(userBookDto.getBook_id());
        userBooks.setProgress(userBookDto.getProgress());
        userBooks.setRating(userBookDto.getRating());
        userBooks.setIsFav(userBookDto.getIsFav());
    
        var bookName = bookRepository.findBookNameById(userBookDto.getBook_id());
        if (bookName != null) {
            userBooks.setBookName(bookName);
        }
    
        userBookRepository.save(userBooks);
    }
    
    
    
    public List<UserBook> findByUserBooks(Integer user_id) {
        List<UserBookDto> userBookDto = new ArrayList<>();
        List<UserBook> userBookList = userBookRepository.findByUserBook(user_id);
        for (UserBook userBook : userBookList) {
            userBookDto.add(
                new UserBookDto(
                    userBook.getId(),
                    userBook.getUser_id(),
                    userBook.getBook_id(),
                    userBook.getBookName(),
                    userBook.getProgress(),
                    userBook.getRating(),
                    userBook.getIsFav()
                )
            );
        }
        return userBookList;
    }

    public void update(UserBook entity) {

        var userBook = new UserBook();

        userBook.setId(entity.getId());
        var oldUser = userBookRepository.findById(entity.getId());

        userBook.setUser_id(oldUser.getUser_id());
    
        userBook.setBook_id(oldUser.getBook_id());

        userBook.setBookName(oldUser.getBookName());

        if(entity.getProgress() != oldUser.getProgress()) {
            userBook.setProgress(entity.getProgress());
        } else {
            userBook.setProgress(oldUser.getProgress());
        }
        
        if(entity.getRating() != oldUser.getRating()) {
            userBook.setRating(entity.getRating());
        } else {
            userBook.setRating(oldUser.getRating());
        }

        if(entity.getIsFav() != oldUser.getIsFav()) {
            userBook.setIsFav(entity.getIsFav());
        } else {
            userBook.setIsFav(oldUser.getIsFav());
        }

        userBookRepository.update(userBook);
    }
}
