package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Payment;
import com.sommelier.wine4you.repository.PaymentRepository;
import com.sommelier.wine4you.service.PaymentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment create(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Payment getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Payment", "Id", String.valueOf(id))
        );
    }

    @Override
    public List<Payment> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        repository.deleteById(id);
        return repository.existsById(id);
    }

    @Override
    public Payment update(Long id, Payment payment) {
        payment.setId(id);
        return repository.save(payment);
    }
}
