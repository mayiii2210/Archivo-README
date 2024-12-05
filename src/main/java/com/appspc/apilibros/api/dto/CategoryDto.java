package com.appspc.apilibros.api.dto;

public class CategoryDto {

    private Integer id;
    private String nameCategory;
    private Boolean isActive;

    public CategoryDto() {
    }

    public CategoryDto(Integer id, String nameCategory, Boolean isActive) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.isActive = isActive;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }


    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
