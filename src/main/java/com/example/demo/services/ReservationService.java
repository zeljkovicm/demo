package com.example.demo.services;

import com.example.demo.entity.ReservationEntity;
import com.example.demo.models.ReservationModel;
import com.example.demo.models.ReservationWithTreatmentModel;
import com.example.demo.repositories.IReservationRepository;
import com.example.demo.repositories.IReservationTreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
public class ReservationService implements IReservationService{

    private IReservationRepository reservationRepository;
    private IReservationTreatmentRepository reservationTreatmentRepository;
    private ModelMapper mapper;

    public ReservationService(IReservationRepository reservationRepository, IReservationTreatmentRepository reservationTreatmentRepository, ModelMapper mapper)
    {
        this.reservationRepository = reservationRepository;  // objekat repozitorijuma komuncira sa bazom
        this.reservationTreatmentRepository = reservationTreatmentRepository;
        this.mapper = mapper;
    }

    @Override public Optional<ReservationModel> GetReservation(Integer reservationId) {

        var reservationFromDb = reservationRepository.findById(reservationId);

        if (reservationFromDb == null)
        {
            return null;
        }

        var reservationModel =  mapper.map(reservationFromDb, ReservationModel.class);
        return Optional.of(reservationModel);
    }

    @Override
    public Optional<ReservationWithTreatmentModel> GetReservationAndTreatment(Integer reservationId) {

        var reservationWithTreatmentFromDb = reservationTreatmentRepository.findById(reservationId);

        if (reservationWithTreatmentFromDb == null)
        {
            return null;
        }

        var reservationWithTreatmentModel =  mapper.map(reservationWithTreatmentFromDb, ReservationWithTreatmentModel.class);
        return Optional.of(reservationWithTreatmentModel);
    }

    @Override
    public ReservationModel CreateReservation(ReservationModel reservation) throws Exception{

        // Prvo proverava da li postoji rezervacija za taj datum
        var reservationFromDb = reservationRepository.findByDate(reservation.getTreatmentId(), reservation.getDate());

        // Ako postoji izbacuje izuzetak
        if (reservationFromDb != null)
        {
            throw new Exception("There is already a reservation for that date!");
        }

        var createdReservation = reservationRepository.save(mapper.map(reservation, ReservationEntity.class));
        return mapper.map( createdReservation, ReservationModel.class );
    }

    //Prilikom editovanja rezervacije moguce je promeniti samo datum i napomenu
    @Override
    public ReservationModel EditReservation(ReservationModel reservation) throws Exception {

        //Prvo proveravamo da li je tretman vec rezrevisan za taj datum, a da nije u pitanju ova rezervacija
        var reservationBooked = reservationRepository.findAnotherByDate(reservation.getTreatmentId(), reservation.getDate(), reservation.getReservationId());

        // Ako je tretman vec rezervisan za taj dan
        if ( reservationBooked != null )
        {
            throw new Exception("There is already a reservation for that date!");
        }

        var reservationFromDb = reservationRepository.findById( reservation.getReservationId());

        if (reservationFromDb !=null)
        {

            reservation.setUserId(reservationFromDb.get().getUserId());
            reservation.setAmount(reservationFromDb.get().getAmount());

            // U slucaju da ne menjamo datum
            if( reservation.getDate() == null ){
                reservation.setDate(reservationFromDb.get().getDate());
            }

            // U slucaju da ne menjamo napomenu
            if( reservation.getMessage() == null ){
                reservation.setMessage(reservationFromDb.get().getMessage());
            }

            reservation.setTimeOfCreation(reservationFromDb.get().getTimeOfCreation());
        }

        var editedReservation = reservationRepository.save(mapper.map(reservation, ReservationEntity.class));
        return mapper.map( editedReservation, ReservationModel.class );
    }

    @Override
    public void DeleteReservation(Integer reservationId) {
        reservationRepository.deleteById(reservationId);
    }

}
