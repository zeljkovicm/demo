package com.example.demo.controllers;

import com.example.demo.models.PaymentModel;
import com.example.demo.services.IPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private final IPaymentService paymentService;


    public PaymentController( IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("save")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody @Valid PaymentModel model, BindingResult result) {
        if (result.hasErrors())
        {
            return new ResponseEntity<String>("Payment unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try
        {
            return new ResponseEntity<PaymentModel>(paymentService.SavePayment(model), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>("Payment unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("delete")
    @CrossOrigin("*")
    public void Delete(@RequestBody PaymentModel model) {
        paymentService.DeletePayment(model.getPaymentId());
    }

}
