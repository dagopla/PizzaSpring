package app.pizza.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import app.pizza.persistence.entity.CustomerEntity;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity,Integer>  {
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    public CustomerEntity findByPhone(@Param("phone") String phone);	
    
}
