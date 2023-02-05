package com.example.demo.services;

import com.example.demo.models.PaymentModel;

public interface IPaymentService {

    public PaymentModel SavePayment(PaymentModel payment);
    public void DeletePayment(Integer paymentId);
}
