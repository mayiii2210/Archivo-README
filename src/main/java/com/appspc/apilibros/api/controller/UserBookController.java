package com.appspc.apilibros.api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appspc.apilibros.api.dto.UserBookDto;
import com.appspc.apilibros.api.service.UserBookService;
import com.appspc.apilibros.data.models.UserBook;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user_book")
public class UserBookController {
    
    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping("/{id}")
    @Operation(summary="Obtener libros de un usuario por ID", description="Usado para obtener todos los libros de un usuario")
    public List<UserBook> getUserBook(@PathVariable Integer id) {
        return userBookService.findByUserBooks(id);
    }

    @PostMapping("/")
	@Operation(summary="Crear registro de libro por usuario", description="Usado para crear un registro de libro por usuario")
	public void createUserBook(@RequestBody UserBookDto entity) {
		userBookService.save(entity);
	}

    @PutMapping("/")
	@Operation(summary = "Actualizar usuario por ID", description = "Usado para actualizar un usuario")
	public UserBook updateUserBook(@RequestBody UserBook entity) {
		userBookService.update(entity);
		return entity;
	}
    
}
