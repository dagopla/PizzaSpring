package app.pizza.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.pizza.persistence.entity.CustomerEntity;
import app.pizza.services.CustomerService;


@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getCostumerByPhone(@PathVariable String phone){
        if(phone==null ) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok().body(this.customerService.findByPhoneNumber(phone));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }
}
