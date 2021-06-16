package org.example.superheroe.repository;

import org.example.superheroe.model.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDBRepository extends JpaRepository<UserDB,Long> {
    Optional<UserDB> findByUsername(String username);
}
