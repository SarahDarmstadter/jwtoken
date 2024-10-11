package com.token.connexion.service;

import com.token.connexion.config.JsonWebToken.JwtService;
import com.token.connexion.entities.UserEntity;
import com.token.connexion.mappers.UserMapper;
import com.token.connexion.models.UserLogin;
import com.token.connexion.models.UserSignUp;
import com.token.connexion.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final AuthenticationManager authManager;
  private final JwtService jwtService;
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  public UserEntity register(UserSignUp user) {
    UserEntity userEntity = userMapper.toEntity(user);
    userEntity.setPassword(encoder.encode(user.getPassword()));
    userRepository.save(userEntity);
    return userEntity;
  }

  public String verify(UserLogin userLogin) {
    Authentication authentication =
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(userLogin.getEmail());
    } else {
      return "fail";
    }
  }
}
