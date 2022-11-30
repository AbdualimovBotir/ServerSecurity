package com.example.springsecuritiy.Repository;

import com.example.springsecuritiy.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {
boolean existsByUsername(String username);
}
