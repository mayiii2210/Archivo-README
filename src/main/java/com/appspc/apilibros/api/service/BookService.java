package com.appspc.apilibros.api.service;

import org.springframework.stereotype.Service;
import com.appspc.apilibros.data.models.Book;
import com.appspc.apilibros.data.models.Category;
import com.appspc.apilibros.data.repositories.BookCategoryRepository;
import com.appspc.apilibros.data.repositories.BookRepository;
import java.util.ArrayList;
import java.util.List;
import com.appspc.apilibros.api.dto.BookDto;
import com.appspc.apilibros.api.dto.CategoryDto;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;

    public BookService(BookRepository bookRepository, BookCategoryRepository bookCategoryRepository) {
        this.bookRepository = bookRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public BookDto findById(Integer id) {
        var book = bookRepository.findById(id);
        var bookCategory = bookCategoryRepository.findByBookId(id);
        CategoryDto category = new CategoryDto();
        category.setId(bookCategory.getId());
        category.setNameCategory(bookCategory.getNameCategory());
        category.setIsActive(bookCategory.getIsActive());

        return new BookDto(
            book.getId(),
            book.getBookName(),
            book.getAuthor(),
            book.getGender(),
            book.getUrl_image(),
            book.getUrl_pdf(),
            book.getUrl_audio(),
            book.getDescription(),
            book.getIsActive(),
            category
        );
    }

    public List<BookDto> findAll() {
        List<BookDto> books = new ArrayList<>();
        List<Book> bookList = bookRepository.findAll();
        for (Book book : bookList) {
            var bookCategory = bookCategoryRepository.findByBookId(book.getId());
            CategoryDto category = new CategoryDto();
            category.setId(bookCategory.getId());
            category.setNameCategory(bookCategory.getNameCategory());
            category.setIsActive(bookCategory.getIsActive());

            books.add(new BookDto(
                book.getId(),
                book.getBookName(),
                book.getAuthor(),
                book.getGender(),
                book.getUrl_image(),
                book.getUrl_pdf(),
                book.getUrl_audio(),
                book.getDescription(),
                book.getIsActive(),
                category
            ));
        }
        return books;
    }

    public void save(BookDto bookDto) {
        var book = new Book();
        book.setBookName(bookDto.getBookName());
        book.setAuthor(bookDto.getAuthor());
        book.setGender(bookDto.getGender());
        book.setUrl_image(bookDto.getUrl_image());
        book.setUrl_pdf(bookDto.getUrl_pdf());
        book.setUrl_audio(bookDto.getUrl_audio());
        book.setDescription(bookDto.getDescription());
        book.setIsActive(true);

        var category = new Category();
        category.setId(bookDto.getCategoryDto().getId());
        category.setNameCategory(bookDto.getCategoryDto().getNameCategory());
        category.setIsActive(true);
        book.setCategory(category);

        if (bookRepository.save(book) > 0) {
            var savedBook = bookRepository.findByBookName(bookDto.getBookName());
            bookCategoryRepository.save(savedBook.getId(), category.getId());
        }
    }

    public void update(BookDto bookDto) {
        var book = new Book();
        book.setId(bookDto.getId());
        var oldBook = bookRepository.findById(bookDto.getId());

        if (bookDto.getBookName() != oldBook.getBookName()) {
            book.setBookName(bookDto.getBookName());
        } else {
            book.setBookName(oldBook.getBookName());
        }

        if (bookDto.getAuthor() != oldBook.getAuthor()) {
            book.setAuthor(bookDto.getAuthor());
        } else {
            book.setAuthor(oldBook.getAuthor());
        }

        if (bookDto.getGender() != oldBook.getGender()) {
            book.setGender(bookDto.getGender());
        } else {
            book.setGender(oldBook.getGender());
        }

        book.setUrl_image(oldBook.getUrl_image());
        book.setUrl_pdf(oldBook.getUrl_pdf());
        book.setUrl_audio(oldBook.getUrl_audio());

        if (bookDto.getDescription() != oldBook.getDescription()) {
            book.setDescription(bookDto.getDescription());
        } else {
            book.setDescription(oldBook.getDescription());
        }

        book.setIsActive(oldBook.getIsActive());

        bookRepository.update(book);
    }

    public void delete(Integer id) {
        bookRepository.delete(id);
    }
}