package com.token.connexion.contollers;

import com.token.connexion.entities.UserEntity;
import com.token.connexion.models.UserLogin;
import com.token.connexion.models.UserSignUp;
import com.token.connexion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public UserEntity signup(@RequestBody UserSignUp userSignUp) {
    return userService.register(userSignUp);
  }

  @PostMapping("/login")
  public String login(@RequestBody UserLogin userLogin) {
    return userService.verify(userLogin);
  }
}
