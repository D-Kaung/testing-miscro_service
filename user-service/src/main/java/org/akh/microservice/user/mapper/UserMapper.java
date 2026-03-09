package org.akh.microservice.user.mapper;

import org.akh.microservice.user.DTO.UserCreateDTO;
import org.akh.microservice.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserCreateDTO toUserCreateDTO(User user);

}
