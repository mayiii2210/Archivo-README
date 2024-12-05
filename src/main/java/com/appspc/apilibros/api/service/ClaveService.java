package com.appspc.apilibros.api.service;

import org.springframework.stereotype.Service;

import com.appspc.apilibros.api.dto.ClaveDto;
import com.appspc.apilibros.data.models.Clave;
import com.appspc.apilibros.data.repositories.ClaveRepository;

@Service
public class ClaveService {

    private  final ClaveRepository claveRepository;

    public ClaveService(ClaveRepository claveRepository) {
        this.claveRepository = claveRepository;
    }

    public ClaveDto findByUser_id(Integer user_id) {
        var clave = claveRepository.findByUser_id(user_id);
        return new ClaveDto(
            clave.getId(),
            clave.getUser_id(),
            clave.getHashClave(),
            clave.getIsActive(),
            clave.getModification()
        );
    }

    public void update(ClaveDto claveDto) {

        var clave = new Clave();

        clave.setId(claveDto.getId());
        var oldUser = claveRepository.findByUser_id(claveDto.getUser_id());

        clave.setUser_id(oldUser.getUser_id());

        if(claveDto.getHashClave() != oldUser.getHashClave()) {
            clave.setHashClave(claveDto.getHashClave());
        } else {
            clave.setHashClave(oldUser.getHashClave());
        }
        
        clave.setIsActive(oldUser.getIsActive());

        if(claveDto.getModification() != oldUser.getModification()) {
            clave.setModification(claveDto.getModification());
        } else {
            clave.setModification(oldUser.getModification());
        }

        claveRepository.update(clave.getUser_id(), clave.getHashClave(), clave.getIsActive());
    }
}
