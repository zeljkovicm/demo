package com.example.demo.controllers;

import com.example.demo.models.ReservationModel;
import com.example.demo.models.ReservationWithTreatmentModel;
import com.example.demo.services.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final IReservationService reservationService;


    public ReservationController( IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("get/reservation/by/id")
    @CrossOrigin("*")
    public Optional<ReservationModel> GetReservation(Integer reservationId) {
        return reservationService.GetReservation(reservationId);
    }

    @GetMapping("get/reservation/treatment/by/id")
    @CrossOrigin("*")
    public Optional<ReservationWithTreatmentModel> GetReservationAndTreatmentById(Integer reservationId) {
        return reservationService.GetReservationAndTreatment(reservationId);
    }

    @PostMapping("create")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody @Valid ReservationModel model, BindingResult result) {

        if (result.hasErrors())
        {
            return new ResponseEntity<String>("Reservation unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try
        {
            return new ResponseEntity<ReservationModel>(reservationService.CreateReservation(model) , HttpStatus.OK);

        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("edit")
    @CrossOrigin("*")
    public ResponseEntity<?> Edit(@RequestBody @Valid ReservationModel model, BindingResult result) {

        if (result.hasErrors())
        {
            return new ResponseEntity<String>("Editing reservation unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try
        {
            return new ResponseEntity<ReservationModel>(reservationService.EditReservation(model), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    @CrossOrigin("*")
    public void Delete(@RequestBody ReservationModel model) {
        reservationService.DeleteReservation(model.getReservationId());
    }

}
