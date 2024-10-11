package com.token.connexion.mappers;

import com.token.connexion.entities.UserEntity;
import com.token.connexion.models.UserSignUp;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity toEntity(UserSignUp userDto);

  UserSignUp toDto(UserEntity userEntity);

  void updateUserFromDto(UserSignUp userDto, @MappingTarget UserEntity userEntity);
}
