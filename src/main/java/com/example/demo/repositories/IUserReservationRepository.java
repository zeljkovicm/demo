package com.example.demo.repositories;

import com.example.demo.entity.UserWithReservationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserReservationRepository extends JpaRepository<UserWithReservationsEntity, Integer> {

    UserWithReservationsEntity findByEmail(String email);

}
