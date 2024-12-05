package com.appspc.apilibros.api.controller;

import com.appspc.apilibros.api.dto.ClaveDto;
import com.appspc.apilibros.api.service.ClaveService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/clave")
public class ClaveController {
    
    private final ClaveService claveService;

    public ClaveController(ClaveService claveService) {
        this.claveService = claveService;
    }

	@GetMapping("/{id}")
	@Operation(summary="Obtener clave por ID", description="Usado para obtener una clave por ID")
	public ClaveDto getclave(@Parameter(required = true) @PathVariable Integer id) {

		return claveService.findByUser_id(id);
	}
	
	@PutMapping("/")
	@Operation(summary="Actualizar clave por ID", description="Usado para actualizar una clave")
	public ClaveDto updateclave(@RequestBody ClaveDto entity) {
		claveService.update(entity);
		return entity;
	}
}
