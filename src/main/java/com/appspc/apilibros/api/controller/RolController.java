package com.appspc.apilibros.api.controller;

import java.util.List;

import com.appspc.apilibros.api.dto.RolDto;
import com.appspc.apilibros.api.service.RolService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = "*")
public class RolController {
    
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/")
	@Operation(summary="Obtener rol", description="Usado para obtener un rol")
	public List<RolDto> getAllRol() {

		return rolService.findAll();
	}

	@GetMapping("/{id}")
	@Operation(summary="Obtener rol por ID", description="Usado para obtener un rol por ID")
	public RolDto getrol(@Parameter(description = "Id del rol", required = true)@PathVariable Integer id) {

		return rolService.findById(id);
	}
	
	@PutMapping("/")
	@Operation(summary="Actualizar rol por ID", description="Usado para actualizar un rol")
	public RolDto updaterol(@RequestBody RolDto entity) {
		rolService.update(entity);
		return entity;
	}
	
	@PostMapping("/")
	@Operation(summary="Crear rol", description="Usado para crear un rol")
	public void createrol(@RequestBody RolDto rolDto) {
		rolService.save(rolDto);
	}

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar rol por ID", description="Usado para eliminar un rol por ID")
    public void deleterol(@PathVariable Integer id) {
		rolService.delete(id);
	}
}

