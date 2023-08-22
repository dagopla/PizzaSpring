package app.pizza.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.pizza.persistence.entity.OrderEntity;
import app.pizza.persistence.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }
    public List<OrderEntity> getByDate(){
        LocalDateTime date=LocalDateTime.now().minusDays(1);
        return this.orderRepository.findAllByDateAfter(date);
    }
}
