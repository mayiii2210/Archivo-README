package com.appspc.apilibros.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.appspc.apilibros.api.service.BookService;
import com.appspc.apilibros.api.dto.BookDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    @Operation(summary = "Obtener todos los libros", description = "Usado para obtener todos los libros")
    public List<BookDto> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener libro por ID", description = "Usado para obtener un libro por su ID")
    public BookDto getBook(@Parameter(description = "ID del libro", required = true) @PathVariable Integer id) {
        return bookService.findById(id);
    }

    @PutMapping("/")
    @Operation(summary = "Actualizar libro por ID", description = "Usado para actualizar un libro existente")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        bookService.update(bookDto);
        return bookDto;
    }

    @PostMapping("/")
    @Operation(summary = "Crear libro", description = "Usado para crear un nuevo libro")
    public void createBook(@RequestBody BookDto bookDto) {
        bookService.save(bookDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar libro por ID", description="Usado para eliminar un libro por ID")
    public void deleteBook(@PathVariable Integer id)
    {
        bookService.delete(id);
    }
}