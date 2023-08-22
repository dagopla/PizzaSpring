package app.pizza.persistence.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import app.pizza.persistence.entity.PizzaEntity;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndNameContainingIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndPriceBetween(double min,double max);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNoContainingIgnoreCase(String description);
    
}
