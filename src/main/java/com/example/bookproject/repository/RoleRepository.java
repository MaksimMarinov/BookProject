package com.example.bookproject.repository;

import com.example.bookproject.model.entities.UserRoleEntity;
import com.example.bookproject.model.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {



    UserRoleEntity findByRole(RoleNameEnum role);
}
