package com.example.demo.repositories;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM korisnik WHERE ime LIKE (%:firstName%)")
    List<UserEntity> findAllByFirstName(@Param("firstName") String firstName);

}
