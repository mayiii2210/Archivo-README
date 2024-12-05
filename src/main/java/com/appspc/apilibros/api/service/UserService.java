package com.appspc.apilibros.api.service;

import org.springframework.stereotype.Service;
import com.appspc.apilibros.api.dto.RolDto;
import com.appspc.apilibros.api.dto.UserClaveDto;
import com.appspc.apilibros.api.dto.UserDto;
import com.appspc.apilibros.data.models.Clave;
import com.appspc.apilibros.data.models.Rol;
import com.appspc.apilibros.data.models.User;
import com.appspc.apilibros.data.repositories.ClaveRepository;
import com.appspc.apilibros.data.repositories.UserRepository;
import com.appspc.apilibros.data.repositories.UserRolRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRolRepository userRolRepository;
    private final ClaveRepository claveRepository;

    public UserService(UserRepository userRepository, UserRolRepository userRolRepository,
    ClaveRepository claveRepository) {
        this.userRepository = userRepository;
        this.userRolRepository = userRolRepository;
        this.claveRepository = claveRepository;
    }

    public UserDto findById(Integer id) {
        var user = userRepository.findById(id);
        var rolUser = userRolRepository.findByUserId(id);
        RolDto rol = new RolDto();
        rol.setId(rolUser.getId());
        rol.setName(rolUser.getName());
        rol.setIsActive(rolUser.getIsActive());
    
        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getLastname(),
            user.getDoc_type(),
            user.getDoc(),
            user.getEmail(),
            user.getTel(),
            user.getBirth(),
            user.getImage(),
            user.getBio(),
            user.getAlias(),
            user.getIsActive(),
            rol
        );
    }

    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            var rolUser = userRolRepository.findByUserId(user.getId());
            RolDto rol = new RolDto();
                rol.setId(rolUser.getId());
                rol.setName(rolUser.getName());
                rol.setIsActive(rolUser.getIsActive());
    
            users.add(new UserDto(
                user.getId(),
                user.getUsername(),
                user.getLastname(),
                user.getDoc_type(),
                user.getDoc(),
                user.getEmail(),
                user.getTel(),
                user.getBirth(),
                user.getImage(),
                user.getBio(),
                user.getAlias(),
                user.getIsActive(),
                rol
            ));
        }
        return users;
    }

    public void save(UserClaveDto userClaveDto) {
        var user = new User();
        user.setUsername(userClaveDto.getUsername());
        user.setLastname(userClaveDto.getLastname());
        user.setDoc_type(userClaveDto.getDoc_type());
        user.setDoc(userClaveDto.getDoc());
        user.setEmail(userClaveDto.getEmail());
        user.setTel(userClaveDto.getTel());
        user.setBirth(userClaveDto.getBirth());
        user.setImage(userClaveDto.getImage());
        user.setBio(userClaveDto.getBio());
        user.setAlias(userClaveDto.getAlias());
        user.setIsActive(true);

        var rol = new Rol();
        rol.setId(userClaveDto.getRolDto().getId());
        rol.setName(userClaveDto.getRolDto().getName());
        rol.setIsActive(true);
        user.setRol(rol);   

        var clave = new Clave();
        clave.setId(userClaveDto.getClaveDto().getId());
        clave.setHashClave(userClaveDto.getClaveDto().getHashClave());
        clave.setIsActive(true);
        clave.setModification(userClaveDto.getClaveDto().getModification());
        user.setClave(clave);

        if(userRepository.save(user) > 0) {
            var users = userRepository.findByDoc(userClaveDto.getDoc());
            userRolRepository.save(users.getId(), rol.getId());
            claveRepository.save(users.getId(), clave.getHashClave());
        }
    }

    public void update(UserDto userDto) {

        var user = new User();

        user.setId(userDto.getId());
        var oldUser = userRepository.findById(userDto.getId());

        if(userDto.getUsername() != oldUser.getUsername()) {
            user.setUsername(userDto.getUsername());
        } else {
            user.setUsername(oldUser.getUsername());
        }

        if(userDto.getLastname() != oldUser.getLastname()) {
            user.setLastname(userDto.getLastname());
        } else {
            user.setLastname(oldUser.getLastname());
        }

        if(userDto.getDoc_type() != oldUser.getDoc_type()) {
            user.setDoc_type(userDto.getDoc_type());
        } else {
            user.setDoc_type(oldUser.getDoc_type());
        }

        user.setDoc(oldUser.getDoc());
        user.setEmail(oldUser.getEmail());
        

        if(userDto.getTel() != oldUser.getTel()) {
            user.setTel(userDto.getTel());
        } else {
            user.setTel(oldUser.getTel());
        }

        if(userDto.getBirth() != oldUser.getBirth()) {
            user.setBirth(userDto.getBirth());
        } else {
            user.setBirth(oldUser.getBirth());
        }

        if(userDto.getImage() != oldUser.getImage()) {
            user.setImage(userDto.getImage());
        } else {
            user.setImage(oldUser.getImage());
        }

        if(userDto.getBio() != oldUser.getBio()) {
            user.setBio(userDto.getBio());
        } else {
            user.setBio(oldUser.getBio());
        }

        if(userDto.getAlias() != oldUser.getAlias()) {
            user.setAlias(userDto.getAlias());
        } else {
            user.setAlias(oldUser.getAlias());
        }
        
        user.setIsActive(oldUser.getIsActive());

        userRepository.update(user);
    }
      
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}