package app.pizza.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import app.pizza.persistence.entity.OrderEntity;

public interface OrderRepository extends ListCrudRepository<OrderEntity,Integer>{
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
}
