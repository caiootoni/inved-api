package com.inved.service;

import com.inved.dto.CreateUserDto;
import com.inved.dto.LoginUserDto;
import com.inved.dto.RecoveryJwtTokenDto;
import com.inved.infra.security.JwtTokenService;
import com.inved.infra.security.SecurityConfig;
import com.inved.infra.security.UserDetailsImpl;
import com.inved.model.Role;
import com.inved.model.User;
import com.inved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfiguration;

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public void createUser(CreateUserDto createUserDto) {
        User newUser = new User(
                createUserDto.email(),
                securityConfiguration.passwordEncoder().encode(createUserDto.password()),
                List.of(new Role(null, createUserDto.role()))
        );

        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
    }
}
