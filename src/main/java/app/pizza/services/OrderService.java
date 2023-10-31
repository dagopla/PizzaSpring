package app.pizza.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.pizza.persistence.entity.OrderEntity;
import app.pizza.persistence.projection.OrderSummary;
import app.pizza.persistence.repository.CustomerRepository;
import app.pizza.persistence.repository.OrderRepository;
import app.pizza.services.dto.RandomOrderDto;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }
    public List<OrderEntity> getByDate(){
        LocalDateTime date=LocalDateTime.now().minusDays(1);
        return this.orderRepository.findAllByDateAfter(date);
    }
    @Secured("ROLE_CUSTOMER")
    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrders(idCustomer);
    }
    public OrderSummary getOrderSummary(int orderId){
        return this.orderRepository.findSummary(orderId);
    }
    @Transactional
    public Boolean createRamdonOrder(RandomOrderDto randomOrderDto){
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
       

    }
}
