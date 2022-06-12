package com.example.bookproject.service.impl;

import com.example.bookproject.model.entities.UserRoleEntity;
import com.example.bookproject.model.enums.RoleNameEnum;
import com.example.bookproject.repository.RoleRepository;
import com.example.bookproject.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final RoleRepository roleRepository;

    public UserRoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void initRoles() {

        if(roleRepository.count()==0){
            var adminRole = new UserRoleEntity();
            adminRole.setRole(RoleNameEnum.ADMIN);
            var userRole = new UserRoleEntity();
            userRole.setRole(RoleNameEnum.USER);

            roleRepository.saveAll(List.of(adminRole, userRole));
        }



    }

    @Override
    public UserRoleEntity findRoleByName(RoleNameEnum role) {


        return roleRepository.findByRole(role);
    }
}
