package com.study.authentication.service;

import com.study.authentication.dto.LoginResponseDto;
import com.study.authentication.entity.Credentials;
import com.study.authentication.entity.Role;
import com.study.authentication.entity.User;
import com.study.authentication.repository.CredentialsRepository;
import com.study.authentication.repository.UserRepository;
import com.study.authentication.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;
    private final JwtService jwtService;
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, CredentialsRepository credentialsRepository, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public void registerUser(String login, String password, Role role) {
        User user = new User(role, true);
        userRepository.save(user);

        String hashed = passwordEncoder.encode(password);
        Credentials credentials = new Credentials(login, hashed, user);
        credentialsRepository.save(credentials);
    }

    public LoginResponseDto login(String login, String password){
        Credentials credentials = credentialsRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Invalid login"));
        if (!passwordEncoder.matches(password, credentials.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }
        User user = credentials.getUser();
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getRole().name());
        String refreshToken = jwtService.generateRefreshToken(user.getId());
        return new LoginResponseDto(accessToken, refreshToken);
    }
}
