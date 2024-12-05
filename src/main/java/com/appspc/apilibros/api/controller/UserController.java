package com.appspc.apilibros.api.controller;

import java.util.List;

import com.appspc.apilibros.api.dto.UserClaveDto;
import com.appspc.apilibros.api.dto.UserDto;
import com.appspc.apilibros.api.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	@Operation(summary="Obtener Usuario", description="Usado para obtener todos los usuario")
	public List<UserDto> getAllUser() {

		return userService.findAll();
	}

	@GetMapping("/{id}")
	@Operation(summary="Obtener usuario por ID", description="Usado para obtener un usuario por ID")
	public UserDto getUser(@Parameter(description = "Id del usuario", required = true)@PathVariable Integer id) {

		return userService.findById(id);
	}
	
	@PutMapping("/")
	@Operation(summary = "Actualizar usuario por ID", description = "Usado para actualizar un usuario")
	public UserDto updateUser(@RequestBody UserDto entity) {
		userService.update(entity);
		return entity;
	}
	
	@PostMapping("/")
	@Operation(summary="Crear usuario", description="Usado para crear un usuario")
	public void createUser(@RequestBody UserClaveDto userClaveDto) {
		userService.save(userClaveDto);
	}

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar usuario por ID", description="Usado para eliminar un usuario por ID")
    public void deleteUser(@PathVariable Integer id)
	{
		userService.delete(id);
	}
}