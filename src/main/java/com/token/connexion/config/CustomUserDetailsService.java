package com.token.connexion.config;

import com.token.connexion.entities.UserEntity;
import com.token.connexion.exceptions.UserNotFoundException;
import com.token.connexion.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
    UserEntity user =
        userRepository
            .findByEmail(username)
            .orElseThrow(() -> new UserNotFoundException("l'utilisateur n'existe pas"));
    return new CustomUserDetails(user);
  }
}
