package com.example.bookproject.service.impl;


import com.example.bookproject.model.entities.UserEntity;
import com.example.bookproject.model.entities.UserRoleEntity;
import com.example.bookproject.model.enums.RoleNameEnum;
import com.example.bookproject.model.service.UserServiceModel;
import com.example.bookproject.repository.UserRepository;
import com.example.bookproject.service.UserRoleService;
import com.example.bookproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;


    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
    }


    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserRoleEntity role = userRoleService.findRoleByName(RoleNameEnum.USER);

        UserEntity newUser = new UserEntity();


        newUser.
                setEmail(userServiceModel.getEmail()).
                setPassword(passwordEncoder.encode(userServiceModel.getPassword())).
                setRoles(List.of(role)).
                setUsername(userServiceModel.getUsername()).
                setFirstName(userServiceModel.getFirstName()).
                setLastName(userServiceModel.getLastName());

        userRepository.save(newUser);

    }

    @Override
    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public void initAdmin() {

        if(userRepository.count()==0){
            var adminRole = userRoleService.findRoleByName(RoleNameEnum.ADMIN);
            var userRole = userRoleService.findRoleByName(RoleNameEnum.USER);
            var admin = new UserEntity();

            admin
                    .setEmail("admin@abv.bg")
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("1234"))
                    .setRoles(List.of(adminRole, userRole))
                    .setFirstName("Admin")
                    .setLastName("Adminov");

            userRepository.save(admin);
        }


    }
}
