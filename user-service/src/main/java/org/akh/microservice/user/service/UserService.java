package org.akh.microservice.user.service;

import org.akh.microservice.user.DTO.UserCreateDTO;


public interface UserService {

    UserCreateDTO createUser(UserCreateDTO userCreateDTO);

}
