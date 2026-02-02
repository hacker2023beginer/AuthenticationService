package com.study.authentication.service;

import com.study.authentication.dto.CredentialsDto;
import com.study.authentication.dto.LoginResponseDto;
import com.study.authentication.entity.Credentials;
import com.study.authentication.repository.CredentialsRepository;
import com.study.authentication.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(CredentialsRepository credentialsRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(String login, String password) {
        Credentials credentials = credentialsRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Invalid login or password"));

        if (!passwordEncoder.matches(password, credentials.getPasswordHash())) {
            throw new RuntimeException("Invalid login or password");
        }

        String access = jwtService.generateAccessToken(credentials.getUserId(), credentials.getRole().name());
        String refresh = jwtService.generateRefreshToken(credentials.getUserId());

        return new LoginResponseDto(access, refresh);
    }

    public void saveCredentials(CredentialsDto dto) {
        String hash = passwordEncoder.encode(dto.getPassword());

        Credentials credentials = new Credentials(
                dto.getUserId(),
                dto.getLogin(),
                hash,
                dto.getRole()
        );

        credentialsRepository.save(credentials);
    }
}
