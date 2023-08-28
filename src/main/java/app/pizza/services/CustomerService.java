package app.pizza.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.pizza.persistence.entity.CustomerEntity;
import app.pizza.persistence.repository.CustomerRepository;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity findByPhoneNumber(String phoneNumber){
        return this.customerRepository.findByPhone(phoneNumber);
    }
}
