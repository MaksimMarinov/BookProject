package com.example.bookproject.init;


import com.example.bookproject.service.UserRoleService;
import com.example.bookproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDb implements CommandLineRunner {

private final UserService userService;
private final UserRoleService userRoleService;

    public InitDb(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {

        userRoleService.initRoles();
        userService.initAdmin();
    }


}
