package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.LoginRequestDTO;
import com.example.nationalgeographicproject.dto.LoginResponseDTO;
import com.example.nationalgeographicproject.dto.UserRequestDto;
import com.example.nationalgeographicproject.dto.UserResponseDto;
import com.example.nationalgeographicproject.entity.User;
import com.example.nationalgeographicproject.error.AuthenticationException;
import com.example.nationalgeographicproject.error.UserAlreadyExistsException;
import com.example.nationalgeographicproject.repository.RoleRepository;import com.example.nationalgeographicproject.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    //props:
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JWTService jwtService;



    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        var user = getUserEntityOrThrow(dto.username());

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
                    throw new AuthenticationException("Username or password don't match");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList()
        );

        var jwt = jwtService.jwtToken(authentication);
        return new LoginResponseDTO(jwt);
    }


    @Override
    public UserResponseDto register(UserRequestDto dto) {

        //check that the user does not exist email/username:
        userRepository.findUserByUsernameIgnoreCaseOrEmailIgnoreCase(dto.getUsername(), dto.getEmail()).ifPresent((u) -> {
            throw new UserAlreadyExistsException(u.getUsername(), u.getEmail());
        });

//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
//
//        // Check for validation violations
//        if (!violations.isEmpty()) {
//            // Handle validation errors
//            throw new IllegalArgumentException("Validation failed for UserRequestDto: " + violations);
//        }

        var user = modelMapper.map(dto, User.class);
        System.out.println("1. service. username:"+user.getUsername());
        //encrypt password
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        var role = roleRepository.findRoleByNameIgnoreCase("ROLE_USER").orElseThrow();
        user.setRoles(Set.of(role));
        System.out.println("2. service.user email:"+user.getEmail());
        System.out.println("3. service.user password:"+user.getPassword());
        var saved = userRepository.save(user);
        System.out.println("4. service:"+user);

        return modelMapper.map(saved, UserResponseDto.class);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //fetch our entity from database:
        User user = getUserEntityOrThrow(username);

        //map the roles for Spring:
        //map our Set<Role> to Set<spring.Role>
        var roles =
                user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toSet());

        //map the user to Spring User:
        //return new Spring User:
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

    private User getUserEntityOrThrow(String username) {
        return userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
    }
}
