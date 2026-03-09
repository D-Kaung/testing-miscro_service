package org.akh.microservice.user.repository;

import org.akh.microservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
