package com.example.demo.services;

import com.example.demo.models.ReservationModel;
import com.example.demo.models.ReservationWithTreatmentModel;

import java.util.Optional;

public interface IReservationService {

    public Optional<ReservationModel> GetReservation(Integer reservationId);
    public Optional<ReservationWithTreatmentModel> GetReservationAndTreatment(Integer reservationId) ;
    public ReservationModel CreateReservation(ReservationModel reservation) throws Exception;
    public ReservationModel EditReservation(ReservationModel reservation) throws Exception;
    public void DeleteReservation(Integer reservationId);
}
