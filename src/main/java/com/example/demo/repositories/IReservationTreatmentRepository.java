package com.example.demo.repositories;

import com.example.demo.entity.ReservationWithTreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IReservationTreatmentRepository extends JpaRepository<ReservationWithTreatmentEntity, Integer> {

}
