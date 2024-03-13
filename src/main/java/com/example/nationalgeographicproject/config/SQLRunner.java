package com.example.nationalgeographicproject.config;


import com.example.nationalgeographicproject.entity.Role;
import com.example.nationalgeographicproject.entity.User;
import com.example.nationalgeographicproject.repository.RoleRepository;
import com.example.nationalgeographicproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class SQLRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
//    @Transactional / @Primary
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            var adminRole = roleRepository.save(new Role(1L, "ROLE_ADMIN"));
            var userRole = roleRepository.save(new Role(2L, "ROLE_USER"));

            userRepository.save(
                    new User(
                            1L,
                            "admin",
                            "dondinoy@gmail.com",
                            passwordEncoder.encode("Passw0rd1!"),
                            Set.of(adminRole)
                    )
            );

            userRepository.save(
                    new User(
                            2L,
                            "user",
                            "moe@gmail.com",
                            passwordEncoder.encode("Passw0rd1!"),
                            Set.of(userRole)
                    )
            );
        }
    }
}
