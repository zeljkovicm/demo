package com.example.demo.repositories;

import com.example.demo.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;


public interface IReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM rezervacija WHERE tretmanid =:treatmentId AND datum =:date" )
    ReservationEntity findByDate(@Param("treatmentId") Integer treatmentId, @Param("date") LocalDate date);

    @Query(nativeQuery = true, value = "SELECT * FROM rezervacija WHERE tretmanid =:treatmentId AND datum =:date AND rezervacijaid !=:reservationId" )
    ReservationEntity findAnotherByDate(@Param("treatmentId") Integer treatmentId, @Param("date") LocalDate date, @Param("reservationId") Integer reservationId);

}
