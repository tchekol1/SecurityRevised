package com.example.Security403;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
   User findByUsername(String username);
}
