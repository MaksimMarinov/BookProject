package com.example.bookproject.service.impl;


import com.example.bookproject.model.entities.UserEntity;
import com.example.bookproject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {

        // The purpose of this method is to map our user representation (UserEntity)
        // to the user representation in the spring sercurity world (UserDetails).
        // The only thing that spring will provide to us is the user name.
        // The user name will come from the HTML login form.

        UserEntity userEntity =
                userRepository.findByUsername(username).
                        orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));

        return mapToUserDetails(userEntity);
    }

    private static AppUser mapToUserDetails(UserEntity userEntity) {

        List<GrantedAuthority> auhtorities =
                userEntity.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());

        // User is the spring implementation of UserDetails interface.
        return new AppUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                auhtorities
        );
    }
}
