package com.example.demo.services;

import com.example.demo.entity.PaymentEntity;
import com.example.demo.models.PaymentModel;
import com.example.demo.repositories.IPaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service()
public class PaymentService implements IPaymentService{


    private IPaymentRepository paymentRepository;
    private ModelMapper mapper;

    public PaymentService(IPaymentRepository paymentRepository, ModelMapper mapper)
    {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }

    @Override
    public PaymentModel SavePayment(PaymentModel payment) {
        var createdPayment = paymentRepository.save(mapper.map(payment, PaymentEntity.class));
        return mapper.map( createdPayment, PaymentModel.class );
    }


    @Override
    public void DeletePayment(Integer paymentId) {
        paymentRepository.deleteById(paymentId);
    }

}
