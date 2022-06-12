package com.example.bookproject.service;

import com.example.bookproject.model.entities.UserRoleEntity;
import com.example.bookproject.model.enums.RoleNameEnum;

public interface UserRoleService {

    void initRoles();

    UserRoleEntity findRoleByName(RoleNameEnum role);

}
