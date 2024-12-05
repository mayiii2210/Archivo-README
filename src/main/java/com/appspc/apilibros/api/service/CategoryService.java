package com.appspc.apilibros.api.service;

import org.springframework.stereotype.Service;
import com.appspc.apilibros.api.dto.CategoryDto;
import com.appspc.apilibros.data.models.Category;
import com.appspc.apilibros.data.repositories.CategoryRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

       
    public CategoryDto findById(Integer id) {
        var category = categoryRepository.findById(id);
        return new CategoryDto(
            category.getId(),
            category.getNameCategory(),
            category.getIsActive()
        );
    }

 
    public List<CategoryDto> findAll() {
        List<CategoryDto> Category = new ArrayList<>();
        List<Category> CategoryList = categoryRepository.findAll();
        for (Category category : CategoryList) {
            Category.add(
                new CategoryDto(
                    category.getId(),
                    category.getNameCategory(),
                    category.getIsActive()
                )
            );
        }
        return Category;
    }

    public void save(CategoryDto categoryDto) {
        var category = new Category();
        category.setId(categoryDto.getId());
        category.setNameCategory(categoryDto.getNameCategory());
        category.setIsActive(categoryDto.getIsActive());
        categoryRepository.save(category);
    }

    public void update(CategoryDto categoryDto) {
        var category = new Category();

        category.setId(categoryDto.getId());
        var oldUser = categoryRepository.findById(categoryDto.getId());

        if(categoryDto.getNameCategory() != oldUser.getNameCategory()) {
            category.setNameCategory(categoryDto.getNameCategory());
        } else {
            category.setNameCategory(oldUser.getNameCategory());
        }

        category.setIsActive(oldUser.getIsActive());

        categoryRepository.update(category);
    }

    public void delete(Integer id) {
        categoryRepository.delete(id);
    }
 }
