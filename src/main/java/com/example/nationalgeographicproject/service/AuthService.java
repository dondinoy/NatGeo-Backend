package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.LoginRequestDTO;
import com.example.nationalgeographicproject.dto.LoginResponseDTO;
import com.example.nationalgeographicproject.dto.UserRequestDto;
import com.example.nationalgeographicproject.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    UserResponseDto register(UserRequestDto dto);

    LoginResponseDTO login(LoginRequestDTO dto);
}
