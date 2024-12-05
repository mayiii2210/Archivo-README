package com.appspc.apilibros.api.service;

import org.springframework.stereotype.Service;

import com.appspc.apilibros.api.dto.RolDto;
import com.appspc.apilibros.data.models.Rol;
import com.appspc.apilibros.data.repositories.RolRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolService {
    
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public RolDto findById(Integer id) {
        var rol = rolRepository.findById(id);
        return new RolDto(
            rol.getId(),
            rol.getName(),
            rol.getIsActive()
        );
    }

    public List<RolDto> findAll() {
        List<RolDto> rols = new ArrayList<>();
        List<Rol> rolList = rolRepository.findAll();
        for (Rol rol : rolList) {
            rols.add(
                new RolDto(
                    rol.getId(),
                    rol.getName(),
                    rol.getIsActive()
                )
            );
        }

        return rols;
    }

    public void save(RolDto RolDto) {
        var rol = new Rol();
        rol.setName(RolDto.getName());
        rol.setIsActive(true);;
        rolRepository.save(rol);
    }

    public void update(RolDto rolDto) {
        var rol = new Rol();

        rol.setId(rolDto.getId());
        var oldUser = rolRepository.findById(rolDto.getId());

        if(rolDto.getName() != oldUser.getName()) {
            rol.setName(rolDto.getName());
        } else {
            rol.setName(oldUser.getName());
        }

        rol.setIsActive(oldUser.getIsActive());

        rolRepository.update(rol);
    }

    public void delete(Integer id) {
        rolRepository.delete(id);
    }
}

