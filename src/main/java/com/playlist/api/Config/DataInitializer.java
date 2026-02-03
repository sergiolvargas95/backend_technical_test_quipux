package com.playlist.api.Config;

import com.playlist.api.models.Role;
import com.playlist.api.models.UserEntity;
import com.playlist.api.repositories.RoleRepository;
import com.playlist.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        Role adminRole = roleRepository.save(
                new Role(null, "ROLE_ADMIN"));

        Role userRole = roleRepository.save(
                new Role(null, "ROLE_USER"));

        UserEntity admin = UserEntity.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles(Set.of(adminRole))
                .build();

        userRepository.save(admin);

        UserEntity user = UserEntity.builder()
                .username("myNewUser")
                .password(encoder.encode("password123"))
                .roles(Set.of(userRole))
                .build();

        userRepository.save(user);
    }
}