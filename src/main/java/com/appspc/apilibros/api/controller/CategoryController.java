package com.appspc.apilibros.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.appspc.apilibros.api.service.CategoryService;
import com.appspc.apilibros.api.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/category")
//@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    @Operation(summary = "Obtener todas las categorías", description = "Usado para obtener todas las categorías")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID", description = "Usado para obtener una categoría por su ID")
    public CategoryDto getCategory(
            @Parameter(description = "ID de la categoría", required = true) @PathVariable Integer id) {
        return categoryService.findById(id);
    }

    @PutMapping("/")
    @Operation(summary = "Actualizar categoría", description = "Usado para actualizar una categoría existente")
    public CategoryDto updateCategory(@RequestBody CategoryDto entity) {
        categoryService.update(entity);
        return entity;
    }

    @PostMapping("/")
    @Operation(summary = "Crear categoría", description = "Usado para crear una nueva categoría")
    public void createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar categoría por ID", description="Usado para eliminar una categoría por ID")
    public void deleteCategory(@PathVariable Integer id)
	{
		categoryService.delete(id);
	}

}